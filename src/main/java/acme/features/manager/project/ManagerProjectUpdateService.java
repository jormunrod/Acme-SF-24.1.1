
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectUpdateService extends AbstractService<Manager, Project> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project project;
		Manager manager;

		id = super.getRequest().getData("id", int.class);
		project = this.repository.findOneProjectById(id);
		manager = project == null ? null : project.getManager();
		status = project != null && !project.isPublished() && super.getRequest().getPrincipal().hasRole(manager);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProjectById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;

		super.bind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;
		boolean status;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Project existing;
			existing = this.repository.findOneProjectByCode(object.getCode());
			if (existing != null)
				status = existing.getId() == object.getId();
			else
				status = false;
			super.state(existing == null || status, "code", "manager.project.form.error.duplicateCode");
		}

		if (!super.getBuffer().getErrors().hasErrors("cost")) {
			super.state(object.getCost().getAmount() >= 1.00, "cost", "manager.project.form.error.negativeCost");
			super.state(object.getCost().getAmount() <= 1000000.00, "cost", "manager.project.form.error.expensiveCost");
		}
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");

		super.getResponse().addData(dataset);
	}
}
