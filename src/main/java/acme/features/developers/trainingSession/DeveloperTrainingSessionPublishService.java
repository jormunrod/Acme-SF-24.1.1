
package acme.features.developers.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSesion;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionPublishService extends AbstractService<Developer, TrainingSesion> {
	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingSessionRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int trainingSesionId;
		TrainingSesion trainingSesion;

		trainingSesionId = super.getRequest().getData("id", int.class);
		trainingSesion = this.repository.findTrainingSesionById(trainingSesionId);
		status = trainingSesion != null;

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		TrainingSesion object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findTrainingSesionById(id);

		super.getBuffer().addData(object);

	}

	@Override
	public void bind(final TrainingSesion object) {
		assert object != null;
		int trainingModuleId;
		TrainingModule trainingModule;

		trainingModuleId = super.getRequest().getData("trainingModule", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(trainingModuleId);
		super.bind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");
		object.setTrainingModule(trainingModule);
	}

	@Override
	public void validate(final TrainingSesion object) {
		assert object != null;

	}

	@Override
	public void perform(final TrainingSesion object) {
		assert object != null;

		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingSesion object) {
		assert object != null;

	}

}
