
package acme.features.auditor.auditRecord;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.features.auditor.codeAudit.AuditorCodeAuditRepository;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordCreateService extends AbstractService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository	repository;

	@Autowired
	private AuditorCodeAuditRepository		codeAuditRepository;

	// AbstractService interface ------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		CodeAudit codeAudit;

		id = super.getRequest().getData("codeAuditId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);
		status = codeAudit != null && !codeAudit.isPublished() && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;

		object = new AuditRecord();
		object.setPublished(false);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		int id;
		CodeAudit codeAudit;

		id = super.getRequest().getData("codeAuditId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);

		super.bind(object, "code", "auditPeriodStart", "auditPeriodEnd", "mark", "link", "isPublished");
		object.setCodeAudit(codeAudit);
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			AuditRecord existing;

			existing = this.repository.findOneAuditRecordByCode(object.getCode());
			super.state(existing == null, "code", "auditor.audit-record.form.error.duplicated");
		}

		super.state(!object.getCodeAudit().isPublished(), "*", "auditor.audit-record.form.error.codeAuditPublished");

		LocalDateTime startDateTime = LocalDateTime.ofInstant(object.getAuditPeriodStart().toInstant(), ZoneId.systemDefault());
		LocalDateTime endDateTime = LocalDateTime.ofInstant(object.getAuditPeriodEnd().toInstant(), ZoneId.systemDefault());

		super.state(startDateTime.isBefore(endDateTime), "auditPeriodEnd", "auditor.audit-record.form.error.end-date");
		super.state(Duration.between(startDateTime, endDateTime).toHours() >= 1, "auditPeriodEnd", "auditor.audit-record.form.error.duration");
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;

		CodeAudit codeAudit;
		List<Mark> marks;

		codeAudit = object.getCodeAudit();
		marks = this.repository.findAllAuditRecordsByCodeAuditId(codeAudit.getId()).stream().map(ar -> ar.getMark()).toList();
		Map<Mark, Integer> frequencies = new HashMap<>();
		for (Mark mark : marks)
			frequencies.put(mark, frequencies.getOrDefault(mark, 0) + 1);

		List<Mark> modes = new ArrayList<>();
		int maxFrequency = 0;
		for (Map.Entry<Mark, Integer> entry : frequencies.entrySet()) {
			int frequency = entry.getValue();
			if (frequency > maxFrequency) {
				maxFrequency = frequency;
				modes.clear();
				modes.add(entry.getKey());
			} else if (frequency == maxFrequency)
				modes.add(entry.getKey());
		}
		modes.sort(Comparator.naturalOrder());
		codeAudit.setMark(modes.get(modes.size() - 1));

		this.repository.save(object);
		this.codeAuditRepository.save(codeAudit);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;

		SelectChoices choices;
		Dataset dataset;

		choices = SelectChoices.from(Mark.class, object.getMark());
		dataset = super.unbind(object, "code", "auditPeriodStart", "auditPeriodEnd", "mark", "link");
		dataset.put("mark", choices);
		dataset.put("codeAuditId", super.getRequest().getData("codeAuditId", int.class));

		super.getResponse().addData(dataset);
	}
}
