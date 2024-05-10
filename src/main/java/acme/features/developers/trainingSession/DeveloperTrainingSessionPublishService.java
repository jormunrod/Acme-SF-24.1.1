
package acme.features.developers.trainingSession;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionPublishService extends AbstractService<Developer, TrainingSession> {
	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingSessionRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int trainingSessionId;
		TrainingModule trainingModule;

		trainingSessionId = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleByTrainingSessionId(trainingSessionId);
		status = trainingModule.isDraftMode() && trainingModule != null && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		TrainingSession object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findTrainingSessionById(id);

		super.getBuffer().addData(object);

	}

	@Override
	public void bind(final TrainingSession object) {
		assert object != null;

		super.bind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");

	}

	@Override
	public void validate(final TrainingSession object) {
		assert object != null;

		int id;
		TrainingModule trainingModule;
		id = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleByTrainingSessionId(id);

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSession existing;

			existing = this.repository.findOneTrainingSessionByCode(object.getCode());
			if (existing != null && existing.getId() != object.getId())
				super.state(existing == null, "code", "developer.training-session.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			Date oneWeekAfterCreation;
			boolean isTrue;
			oneWeekAfterCreation = new Date(trainingModule.getCreationMoment().getTime() + 7L * 24 * 60 * 60 * 1000);
			isTrue = object.getStartDate().before(oneWeekAfterCreation);
			super.state(isTrue == false, "startDate", "developer.training-session.form.error.bad-date");

		}
		if (!super.getBuffer().getErrors().hasErrors("finishDate")) {
			long duration = object.getFinishDate().getTime() - object.getStartDate().getTime();
			long oneWeek = 7L * 24 * 60 * 60 * 1000;

			boolean isTrue;
			isTrue = duration < oneWeek;
			super.state(isTrue == false, "finishDate", "developer.training-session.form.error.bad-duration");
		}

	}

	@Override
	public void perform(final TrainingSession object) {
		assert object != null;
		TrainingSession trainingSession;
		trainingSession = object;

		trainingSession.setDraftMode(false);

		this.repository.save(trainingSession);

	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link", "draftMode");

		super.getResponse().addData(dataset);

	}

}
