
package acme.features.any.codeAudit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.CodeAuditType;
import acme.entities.projects.Project;

@Service
public class AnyCodeAuditShowService extends AbstractService<Any, CodeAudit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyCodeAuditRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		CodeAudit object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findCodeAuditById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final CodeAudit object) {
		assert object != null;
		Collection<Project> projects;
		SelectChoices choices;
		SelectChoices choicesType;
		Dataset dataset;

		projects = this.repository.findAllPublishedProjects();
		choices = SelectChoices.from(projects, "code", object.getProject());
		choicesType = SelectChoices.from(CodeAuditType.class, object.getType());
		dataset = super.unbind(object, "auditor", "code", "execution", "type", "correctiveActions", "mark", "link");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("types", choicesType);
		dataset.put("auditor", object.getAuditor().getUserAccount().getUsername());

		super.getResponse().addData(dataset);
	}
}
