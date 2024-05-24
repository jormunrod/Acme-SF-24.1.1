
package acme.features.auditor.codeAudit;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.CodeAuditType;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditCreateService extends AbstractService<Auditor, CodeAudit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorCodeAuditRepository repository;

	// AbstractService interface --------------------------


	@Override
	public void authorise() {
		boolean status;
		int auditorId;
		Auditor auditor;

		auditorId = super.getRequest().getPrincipal().getActiveRoleId();
		auditor = this.repository.findOneAuditorById(auditorId);

		status = super.getRequest().getPrincipal().hasRole(auditor);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		CodeAudit object;
		Auditor auditor;

		auditor = this.repository.findOneAuditorById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new CodeAudit();
		object.setPublished(false);
		object.setAuditor(auditor);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final CodeAudit object) {
		assert object != null;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);
		super.bind(object, "code", "execution", "type", "correctiveActions", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final CodeAudit object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			CodeAudit existing;

			existing = this.repository.findOneCodeAuditByCode(object.getCode());
			super.state(existing == null, "code", "auditor.code-audit.form.err.duplicated");
		} else if (!super.getBuffer().getErrors().hasErrors("execution")) {
			LocalDateTime executionDateTime = LocalDateTime.ofInstant(object.getExecution().toInstant(), ZoneId.systemDefault());

			LocalDateTime minDateTime = LocalDateTime.of(2000, 01, 01, 00, 00);
			super.state(executionDateTime.isAfter(minDateTime), "execution", "auditor.code-audit.form.error.date-before-2000");
		}

		super.state(!object.isPublished(), "*", "auditor.code-audit.form.err.isPublished");
	}

	@Override
	public void perform(final CodeAudit object) {
		assert object != null;

		this.repository.save(object);
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
