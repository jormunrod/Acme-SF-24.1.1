
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
public class ManagerUserStoryPublishService extends AbstractService<Manager, UserStory> {

	@Autowired
	protected ManagerUserStoryRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project project;

		id = super.getRequest().getData("id", int.class);
		project = this.repository.findOneProjectByUserStoryId(id);
		status = project != null && !project.isPublished() && super.getRequest().getPrincipal().hasRole(project.getManager());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		UserStory object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneUserStoryById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final UserStory object) {
		assert object != null;

		super.bind(object, "title", "description", "estimatedHours", "acceptanceCriteria", "priority", "link", "isPublished");
	}

	@Override
	public void validate(final UserStory object) {
		assert object != null;
		boolean status;
		if (!super.getBuffer().getErrors().hasErrors("title")) {
			UserStory existing;
			existing = this.repository.findOneUserStoryByTitle(object.getTitle());
			if (existing != null)
				status = existing.getId() == object.getId();
			else
				status = false;
			super.state(existing == null || status, "title", "manager.user-story.form.error.duplicateTitle");
		}

		super.state(!object.getProject().isPublished(), "link", "manager.user-story.form.error.projectPublished");
	}

	@Override
	public void perform(final UserStory object) {
		assert object != null;
		object.setPublished(true);
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

		super.getResponse().addData(dataset);
	}
}
