
package acme.features.developers.trainingSession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionUpdateService extends AbstractService<Developer, TrainingSession> {

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
		status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

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
		LocalDateTime localDateTime = LocalDateTime.of(2201, 1, 1, 0, 0);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date limitDate = Date.from(instant);

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSession existing;

			existing = this.repository.findOneTrainingSessionByCode(object.getCode());
			if (existing != null && existing.getId() != object.getId())
				super.state(existing == null, "code", "developer.training-session.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			Date oneWeekAfterCreation;
			boolean isTrue;
			boolean isGreater;
			oneWeekAfterCreation = new Date(trainingModule.getCreationMoment().getTime() + 7L * 24 * 60 * 60 * 1000);
			isTrue = object.getStartDate().before(oneWeekAfterCreation);
			isGreater = object.getStartDate().before(limitDate);
			super.state(isTrue == false, "startDate", "developer.training-session.form.error.bad-date");
			super.state(isGreater, "startDate", "developer.training-session.error.startDateLimitPassed");

		}
		if (!super.getBuffer().getErrors().hasErrors("finishDate"))
			if (object.getStartDate() != null) {
				long duration = object.getFinishDate().getTime() - object.getStartDate().getTime();
				long oneWeek = 7L * 24 * 60 * 60 * 1000;

				boolean isTrue;
				boolean isFinishBeforeLimit;
				isTrue = duration < oneWeek;
				isFinishBeforeLimit = object.getFinishDate().before(limitDate);
				super.state(isTrue == false, "finishDate", "developer.training-session.form.error.bad-duration");
				super.state(isFinishBeforeLimit, "finishDate", "developer.training-session.error.finishDateLimitPassed");
			}

	}

	@Override
	public void perform(final TrainingSession object) {
		assert object != null;

		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link", "draftMode");

		super.getResponse().addData(dataset);
	}
}
