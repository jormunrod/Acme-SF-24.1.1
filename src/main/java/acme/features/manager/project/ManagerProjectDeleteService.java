
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Assignment;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	@Autowired
	private ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project project;
		//Manager manager;
		id = super.getRequest().getData("id", int.class);
		project = this.repository.findOneProjectById(id);
		//manager = project == null ? null : project.getManager();
		status = project != null && !project.isPublished() && super.getRequest().getPrincipal().hasRole(project.getManager());
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
		int id;
		Manager manager;
		id = super.getRequest().getData("id", int.class);
		manager = this.repository.findOneManagerById(id);
		super.bind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");
		object.setManager(manager);
	}
	@Override
	public void validate(final Project object) {
		assert object != null;
	}
	@Override
	public void perform(final Project object) {
		assert object != null;

		Collection<Assignment> assignments;

		assignments = this.repository.findAllAssignmentsByProjectId(object.getId());
		this.repository.deleteAll(assignments);

		this.repository.delete(object);
	}
	@Override
	public void unbind(final Project object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");
		super.getResponse().addData(dataset);
	}
}
