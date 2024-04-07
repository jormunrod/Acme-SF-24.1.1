
package acme.features.developers.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingSesion;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionCreateService extends AbstractService<Developer, TrainingSesion> {
	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingSessionRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);

	}

	@Override
	public void load() {
		TrainingSesion object;

		object = new TrainingSesion();

		super.getBuffer().addData(object);

	}

	@Override
	public void bind(final TrainingSesion object) {
		assert object != null;

		super.bind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");

	}

	@Override
	public void validate(final TrainingSesion object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSesion existing;

			existing = this.repository.findOneTrainingSesionByCode(object.getCode());
			super.state(existing == null, "code", "developer.training-sesion.form.error.duplicated");
		}

	}

	@Override
	public void perform(final TrainingSesion object) {
		assert object != null;

		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingSesion object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");

		super.getResponse().addData(dataset);
	}

}
