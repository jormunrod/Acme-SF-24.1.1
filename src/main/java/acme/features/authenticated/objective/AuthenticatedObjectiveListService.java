
package acme.features.authenticated.objective;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.objective.Objective;

@Service
public class AuthenticatedObjectiveListService extends AbstractService<Authenticated, Objective> {

	@Autowired
	private AuthenticatedObjectiveRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Objective> objects;

		objects = this.repository.findAllObjectives();

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final Objective object) {
		assert object != null;

		Dataset dataset;
		String critical = object.isCritical() ? "✔️" : "❌";
		dataset = super.unbind(object, "title", "priority", "isCritical");
		dataset.put("isCritical", critical);
		super.getResponse().addData(dataset);
	}
}
