
package acme.features.developers.trainingModule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleUpdateService extends AbstractService<Developer, TrainingModule> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingModuleRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		TrainingModule trainingModule;
		Developer developer;

		id = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findTrainingModuleById(id);
		developer = trainingModule == null ? null : trainingModule.getDeveloper();
		status = trainingModule != null && super.getRequest().getPrincipal().hasRole(developer);

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		TrainingModule object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findTrainingModuleById(id);

		super.getBuffer().addData(object);

	}

	@Override
	public void bind(final TrainingModule object) {
		assert object != null;

		super.bind(object, "code", "creationMoment", "difficultyLevel", "updateMoment", "details", "link", "totalTime");

	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		boolean status;
		int id;
		int numberOfTrainingSessions;

		id = object.getId();
		numberOfTrainingSessions = this.repository.countTrainingSessionsByTrainingModuleId(id);
		System.out.println(numberOfTrainingSessions);
		status = numberOfTrainingSessions == 0;

		super.state(status, "*", "developer.training-module.form.error.hasSessions");
	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		object.setUpdateMoment(new Date());
		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "creationMoment", "difficultyLevel", "updateMoment", "details", "link", "totalTime");

		super.getResponse().addData(dataset);
	}
}
