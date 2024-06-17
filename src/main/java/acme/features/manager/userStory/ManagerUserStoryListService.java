
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryListService extends AbstractService<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project project;

		id = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(id);
		status = project != null && super.getRequest().getPrincipal().hasRole(project.getManager());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<UserStory> objects;
		int id;

		id = super.getRequest().getData("projectId", int.class);
		objects = this.repository.findAllUserStoryByProjectId(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		Dataset dataset;
		String published = object.isPublished() ? "✔️" : "❌";
		dataset = super.unbind(object, "title", "estimatedHours", "priority");
		dataset.put("isPublished", published);
		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<UserStory> objects) {
		assert objects != null;

		Project project;
		int projectId;

		projectId = super.getRequest().getData("projectId", int.class);
		project = this.repository.findOneProjectById(projectId);

		super.getResponse().addGlobal("projectId", projectId);
		super.getResponse().addGlobal("projectIsNotPublished", !project.isPublished());
	}
}
