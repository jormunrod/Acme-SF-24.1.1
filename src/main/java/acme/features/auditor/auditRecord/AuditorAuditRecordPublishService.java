
package acme.features.auditor.auditRecord;

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
public class AuditorAuditRecordPublishService extends AbstractService<Auditor, AuditRecord> {

	@Autowired
	protected AuditorAuditRecordRepository	repository;

	@Autowired
	protected AuditorCodeAuditRepository	codeAuditRepository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		CodeAudit codeAudit;

		id = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditByAuditRecordId(id);
		status = codeAudit != null && !codeAudit.isPublished() && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		AuditRecord object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneAuditRecordById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "code", "auditPeriodStart", "auditPeriodEnd", "mark", "link", "isPublished");
	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;

		super.state(!object.getCodeAudit().isPublished(), "*", "auditor.audit-record.form.error.published");
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;
		object.setPublished(true);

		this.repository.save(object);

		CodeAudit codeAudit;
		List<Mark> marks;

		codeAudit = object.getCodeAudit();
		marks = this.repository.findAllAuditRecordsByCodeAuditId(codeAudit.getId()).stream().filter(ar -> ar.isPublished()).map(ar -> ar.getMark()).toList();
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

		super.getResponse().addData(dataset);
	}
}
