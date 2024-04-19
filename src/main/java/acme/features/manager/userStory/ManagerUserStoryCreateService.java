
package acme.features.manager.userStory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.entities.projects.UserStoryPriority;
import acme.roles.Manager;

@Service
public class ManagerUserStoryCreateService extends AbstractService<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project project;

		id = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(id);
		status = project != null && !project.isPublished() && super.getRequest().getPrincipal().hasRole(project.getManager());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStory object;
		int id;
		Manager manager;

		id = super.getRequest().getPrincipal().getActiveRoleId();
		manager = this.repository.findOneManagerById(id);

		object = new UserStory();
		object.setPublished(false);
		object.setManager(manager);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserStory object) {
		assert object != null;

		int id;
		Project project;

		id = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(id);

		super.bind(object, "title", "description", "estimatedHours", "acceptanceCriteria", "priority", "link", "isPublished");
		object.setProject(project);
	}

	@Override
	public void validate(final UserStory object) {
		assert object != null;
		//TODO: Implement all validation rules
	}

	@Override
	public void perform(final UserStory object) {
		assert object != null;
		int projectId;
		int managerId;
		Project project;
		Manager manager;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);

		managerId = super.getRequest().getPrincipal().getActiveRoleId();
		manager = this.repository.findOneManagerById(managerId);

		object.setProject(project);
		object.setManager(manager);

		this.repository.save(object);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		SelectChoices choices;
		Dataset dataset;

		choices = SelectChoices.from(UserStoryPriority.class, object.getPriority());
		dataset = super.unbind(object, "title", "description", "estimatedHours", "acceptanceCriteria", "priority", "link", "isPublished");
		dataset.put("priority", choices);
		dataset.put("projectId", super.getRequest().getData("projectId", int.class));

		super.getResponse().addData(dataset);
	}
}
