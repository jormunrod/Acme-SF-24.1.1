
package acme.features.developers.trainingSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionDeleteService extends AbstractService<Developer, TrainingSession> {

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

	}

	@Override
	public void perform(final TrainingSession object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final TrainingSession object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link", "draftMode");

		super.getResponse().addData(dataset);
	}
}
