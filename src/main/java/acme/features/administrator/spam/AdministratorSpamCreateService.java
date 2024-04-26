
package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.spam.Spam;

@Service
public class AdministratorSpamCreateService extends AbstractService<Administrator, Spam> {

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
		Spam object;

		object = new Spam();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Spam object) {
		assert object != null;

		super.bind(object, "spanishWord", "englishWord");
	}

	@Override
	public void validate(final Spam object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("spanishWords")) {
			Spam existing;

			existing = this.repository.findOneSpamByWord(object.getSpanishWord());
			super.state(existing == null, "*", "administrator.spam.form.err.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("englishWords")) {
			Spam existing;

			existing = this.repository.findOneSpamByWord(object.getEnglishWord());
			super.state(existing == null, "*", "administrator.spam.form.err.duplicated");
		}
	}

	@Override
	public void perform(final Spam object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final Spam object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "spanishWord", "englishWord");

		super.getResponse().addData(dataset);
	}
}
