
package acme.features.manager.assignment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Assignment;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerAssignmentDeleteService extends AbstractService<Manager, Assignment> {

	@Autowired
	protected ManagerAssignmentRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);
		status = project != null && !project.isPublished() && super.getRequest().getPrincipal().hasRole(project.getManager());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Assignment object;
		Project project;
		int id;

		id = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(id);
		object = new Assignment();
		object.setProject(project);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Assignment object) {
		assert object != null;
		Project project;
		UserStory userStory;
		int id;
		int userStoryId;

		id = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(id);

		userStoryId = super.getRequest().getData("userStories", int.class);
		userStory = this.repository.findOneUserStoryById(userStoryId);

		object.setProject(project);
		object.setUserStory(userStory);
	}

	@Override
	public void validate(final Assignment object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("userStories")) {
			super.state(object.getUserStory() != null, "userStories", "manager.assignment.form.error.userStory");

			Project project;
			Manager manager;
			int projectId;
			int userStoryId;

			projectId = super.getRequest().getData("projectId", int.class);
			project = this.repository.findOneProjectById(projectId);
			if (object.getUserStory() != null) {
				userStoryId = object.getUserStory().getId();
				manager = this.repository.findOneManagerByUserStoryId(userStoryId);
				super.state(project.getManager() == manager, "*", "manager.assignment.form.error.manager");
			}
		}
	}

	@Override
	public void perform(final Assignment object) {
		assert object != null;
		Assignment assignment;
		int projectId;
		int userStoryId;

		projectId = super.getRequest().getData("projectId", int.class);
		userStoryId = super.getRequest().getData("userStories", int.class);

		assignment = this.repository.findOneAssignmentByProjectAndUserStoryId(projectId, userStoryId);
		this.repository.delete(assignment);
	}

	@Override
	public void unbind(final Assignment object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "project", "userStory");

		SelectChoices choices;
		Project project;
		int projectId;
		Collection<UserStory> userStories;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);

		userStories = this.repository.findAllUserStoriesByProjectId(projectId);

		dataset.put("project-title", project.getTitle());
		dataset.put("project-description", project.getAbstractText());
		dataset.put("project-cost", project.getCost());
		dataset.put("projectId", projectId);
		dataset.put("project", project);

		choices = SelectChoices.from(userStories, "title", object.getUserStory());
		dataset.put("userStory", choices.getSelected().getKey());
		dataset.put("userStories", choices);
		super.getResponse().addData(dataset);
	}
}
