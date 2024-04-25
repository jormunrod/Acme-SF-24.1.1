
package acme.features.any.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.UserStory;

@Service
public class AnyUserStoryListService extends AbstractService<Any, UserStory> {

	@Autowired
	private AnyUserStoryRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<UserStory> objects;
		int id;

		id = super.getRequest().getData("projectId", int.class);
		objects = this.repository.findOneUserStoryByProjectId(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final UserStory object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "title", "estimatedHours", "priority", "isPublished");

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<UserStory> objects) {
		assert objects != null;

		int projectId;

		projectId = super.getRequest().getData("projectId", int.class);

		super.getResponse().addGlobal("projectId", projectId);
	}
}
