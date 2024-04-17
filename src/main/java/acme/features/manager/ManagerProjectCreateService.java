
package acme.features.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectCreateService extends AbstractService<Manager, Project> {

	@Autowired
	protected ManagerProjectRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Project object;
		Manager manager;
		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();
		manager = this.repository.findOneManagerById(id);
		object = new Project();
		object.setPublished(false);
		object.setHasFatalErrors(false);
		object.setManager(manager);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;
		Manager manager;
		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();
		manager = this.repository.findOneManagerById(id);

		super.bind(object, "code", "title", "abstractText", "cost", "link");
		object.setManager(manager);
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Project existing;

			existing = this.repository.findOneProjectByCode(object.getCode());
			super.state(existing == null, "code", "manager.project.form.error.duplicateCode");
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
