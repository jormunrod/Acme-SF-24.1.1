
package acme.features.auditor.codeAudit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.CodeAuditType;
import acme.entities.audits.Mark;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditPublishService extends AbstractService<Auditor, CodeAudit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorCodeAuditRepository repository;

	// AbstractService interface ---------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		CodeAudit codeAudit;

		id = super.getRequest().getData("id", int.class);
		codeAudit = this.repository.findOneCodeAuditById(id);
		status = codeAudit != null && super.getRequest().getPrincipal().hasRole(codeAudit.getAuditor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		CodeAudit object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCodeAuditById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final CodeAudit object) {
		assert object != null;

		super.bind(object, "code", "execution", "type", "correctiveActions", "mark", "link");
	}

	@Override
	public void validate(final CodeAudit object) {
		assert object != null;

		boolean status;
		Collection<AuditRecord> auditRecords;
		Mark mark;

		auditRecords = this.repository.findAllAuditRecordsByCodeAuditId(object.getId());

		status = !auditRecords.isEmpty();
		super.state(status, "*", "auditor.code-audit.form.err.no-audit-records");

		status = auditRecords.stream().allMatch(ar -> ar.isPublished());
		super.state(status, "*", "auditor.code-audit.form.err.audit-records-not-published");

		if (!auditRecords.isEmpty() && status) {
			mark = object.getMark();
			status = !(mark.equals(Mark.F) || mark.equals(Mark.F_MINUS));
			super.state(status, "mark", "auditor.code-audit.form.err.fail-mark");
		}
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			CodeAudit existing;

			existing = this.repository.findOneCodeAuditByCode(object.getCode());
			if (existing != null && existing.getId() != object.getId())
				super.state(false, "code", "auditor.code-audit.form.err.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("execution")) {
			LocalDateTime executionDateTime = LocalDateTime.ofInstant(object.getExecution().toInstant(), ZoneId.systemDefault());

			LocalDateTime minDateTime = LocalDateTime.of(1999, 12, 31, 23, 59);
			super.state(executionDateTime.isAfter(minDateTime), "execution", "auditor.code-audit.form.error.date-before-2000");
		}

		super.state(!object.isPublished(), "*", "auditor.code-audit.form.err.isPublished");
	}

	@Override
	public void perform(final CodeAudit object) {
		assert object != null;
		CodeAudit codeAudit;
		codeAudit = object;

		codeAudit.setPublished(true);

		this.repository.save(codeAudit);
	}

	@Override
	public void unbind(final CodeAudit object) {
		assert object != null;
		Collection<Project> projects;
		SelectChoices choices;
		SelectChoices choicesType;
		Dataset dataset;

		projects = this.repository.findPublishedProjects();
		choices = SelectChoices.from(projects, "code", object.getProject());
		choicesType = SelectChoices.from(CodeAuditType.class, object.getType());
		dataset = super.unbind(object, "code", "execution", "type", "correctiveActions", "mark", "link", "isPublished");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("types", choicesType);

		super.getResponse().addData(dataset);
	}
}
