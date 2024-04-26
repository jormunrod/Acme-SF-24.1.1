
package acme.features.administrator.objective;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.objective.Objective;
import acme.entities.objective.ObjectivePriority;

@Service
public class AdministratorObjectivesCreateService extends AbstractService<Administrator, Objective> {

	@Autowired
	private AdministratorObjectiveRepository repository;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Objective object;
		Date date;

		date = MomentHelper.getCurrentMoment();
		object = new Objective();
		object.setInstantiationMoment(date);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Objective object) {
		assert object != null;

		super.bind(object, "title", "isCritical", "priority", "description", "startDate", "endDate", "link");
	}

	@Override
	public void validate(final Objective object) {
		assert object != null;
		boolean status;
		boolean confirmation;

		if (!super.getBuffer().getErrors().hasErrors("startDate") && !super.getBuffer().getErrors().hasErrors("endDate")) {
			status = object.getInstantiationMoment().before(object.getStartDate());
			super.state(status, "startDate", "administrator.objective.form.error.startDate");

			status = object.getStartDate().before(object.getEndDate());
			super.state(status, "endDate", "administrator.objective.form.error.endDate");
		}

		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void perform(final Objective object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final Objective object) {
		assert object != null;

		SelectChoices choices;
		Dataset dataset;

		choices = SelectChoices.from(ObjectivePriority.class, object.getPriority());
		dataset = super.unbind(object, "title", "instantiationMoment", "isCritical", "priority", "description", "startDate", "endDate", "link");
		dataset.put("priority", choices);

		super.getResponse().addData(dataset);
	}
}
