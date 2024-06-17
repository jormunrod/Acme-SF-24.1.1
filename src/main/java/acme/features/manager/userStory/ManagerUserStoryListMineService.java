
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Service
public class ManagerUserStoryListMineService extends AbstractService<Manager, UserStory> {

	@Autowired
	private ManagerUserStoryRepository repository;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Manager.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<UserStory> objects;
		Principal principal;
		int id;

		principal = super.getRequest().getPrincipal();
		id = principal.getActiveRoleId();

		objects = this.repository.findAllUserStoryByManagerId(id);

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
}
