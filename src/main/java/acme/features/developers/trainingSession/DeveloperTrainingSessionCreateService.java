
package acme.features.developers.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
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
		boolean status;
		int id;
		TrainingModule trainingModule;

		id = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);
		status = trainingModule != null && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

		super.getResponse().setAuthorised(status);

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
		int id;
		TrainingModule trainingModule;
		id = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);

		super.bind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");
		object.setTrainingModule(trainingModule);
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

		int id;
		TrainingModule trainingModule;

		id = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);
		object.setTrainingModule(trainingModule);

		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingSesion object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));

		super.getResponse().addData(dataset);
	}

}
