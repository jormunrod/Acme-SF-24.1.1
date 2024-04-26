
package acme.features.developers.trainingSession;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
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
		TrainingModule trainingModule;

		trainingSesionId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleByTrainingSesionId(trainingSesionId);
		status = trainingModule.isDraftMode() && trainingModule != null && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

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

		super.bind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");

	}

	@Override
	public void validate(final TrainingSesion object) {
		assert object != null;

		int id;
		TrainingModule trainingModule;
		id = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleByTrainingSesionId(id);

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSesion existing;

			existing = this.repository.findOneTrainingSesionByCode(object.getCode());
			if (existing != null && existing.getId() != object.getId())
				super.state(existing == null, "code", "developer.training-sesion.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			Date oneWeekAfterCreation;
			boolean isTrue;
			oneWeekAfterCreation = new Date(trainingModule.getCreationMoment().getTime() + 7L * 24 * 60 * 60 * 1000);
			isTrue = object.getStartDate().before(oneWeekAfterCreation);
			super.state(isTrue == false, "startDate", "developer.training-sesion.form.error.bad-date");

		}
		if (!super.getBuffer().getErrors().hasErrors("finishDate")) {
			long duration = object.getFinishDate().getTime() - object.getStartDate().getTime();
			long oneWeek = 7L * 24 * 60 * 60 * 1000;

			boolean isTrue;
			isTrue = duration < oneWeek;
			super.state(isTrue == false, "finishDate", "developer.training-sesion.form.error.bad-duration");
		}

	}

	@Override
	public void perform(final TrainingSesion object) {
		assert object != null;
		TrainingSesion trainingSesion;
		trainingSesion = object;

		trainingSesion.setDraftMode(false);

		this.repository.save(trainingSesion);

	}

	@Override
	public void unbind(final TrainingSesion object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link", "draftMode");

		super.getResponse().addData(dataset);

	}

}
