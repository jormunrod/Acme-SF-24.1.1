
package acme.features.manager.assignment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Assignment;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerAssignmentCreateService extends AbstractService<Manager, Assignment> {

	@Autowired
	protected ManagerAssignmentRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Project project;
		int projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);
		status = project != null && !project.isPublished() //
			&& super.getRequest().getPrincipal().hasRole(project.getManager());

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
		this.repository.save(object);
	}

	@Override
	public void unbind(final Assignment object) {
		assert object != null;

		SelectChoices choices;
		Dataset dataset;
		Project project;
		Principal principal;
		int id;
		int projectId;
		Collection<UserStory> userStories;
		Collection<UserStory> userStoriesAssigned;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);

		principal = super.getRequest().getPrincipal();
		id = principal.getActiveRoleId();
		userStories = this.repository.findAllUserStoriesByManagerId(id);
		userStoriesAssigned = this.repository.findAllUserStoriesByProjectId(projectId);

		userStories.removeAll(userStoriesAssigned);

		dataset = super.unbind(object, "project", "userStory");

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
