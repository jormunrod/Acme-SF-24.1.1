
package acme.features.administrator.spam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.spam.Spam;

@Service
public class AdministratorSpamListService extends AbstractService<Administrator, Spam> {

	@Autowired
	private AdministratorSpamRepository repository;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<Spam> objects;

		objects = this.repository.findAllSpams();

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final Spam object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "spanishWord", "englishWord");

		super.getResponse().addData(dataset);
	}
}
