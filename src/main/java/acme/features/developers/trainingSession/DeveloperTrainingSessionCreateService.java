
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
		object.setDraftMode(true);

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
		int id;
		TrainingModule trainingModule;
		id = super.getRequest().getData("masterId", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSesion existing;

			existing = this.repository.findOneTrainingSesionByCode(object.getCode());
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

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link", "draftMode");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));

		super.getResponse().addData(dataset);
	}

}
