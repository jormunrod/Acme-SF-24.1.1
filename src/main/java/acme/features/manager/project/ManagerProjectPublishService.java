
package acme.features.manager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.roles.Manager;

@Service
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {

	@Autowired
	protected ManagerProjectRepository repository;


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
		Manager manager;
		int id;
		id = super.getRequest().getPrincipal().getActiveRoleId();
		manager = this.repository.findOneManagerById(id);
		super.bind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");
		object.setManager(manager);
	}
	@Override
	public void validate(final Project object) {
		assert object != null;
		boolean duplicatedCode;
		Integer userStories;
		Integer publishedUserStories;
		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Project existing;
			existing = this.repository.findOneProjectByCode(object.getCode());
			if (existing != null)
				duplicatedCode = existing.getId() == object.getId();
			else
				duplicatedCode = false;
			super.state(existing == null || duplicatedCode, "code", "manager.project.form.error.duplicateCode");
		}
		if (!super.getBuffer().getErrors().hasErrors("hasFatalErrors"))
			super.state(!object.isHasFatalErrors(), "hasFatalErrors", "manager.project.form.error.fatalErrors");
		userStories = this.repository.findAllUserStoriesByProjectId(object.getId()).size();
		publishedUserStories = this.repository.findAllPublishedUserStoriesByProjectId(object.getId());
		super.state(userStories > 0, "userStory", "manager.project.form.error.hasUserStories");
		super.state(userStories.equals(publishedUserStories), "publishedUserStory", "manager.project.form.error.hasAllUserStoriesPublished");
	}
	@Override
	public void perform(final Project object) {
		assert object != null;
		object.setPublished(true);
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
