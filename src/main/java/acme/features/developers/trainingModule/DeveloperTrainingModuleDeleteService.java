
package acme.features.developers.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.trainings.DifficultyLevel;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSession;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleDeleteService extends AbstractService<Developer, TrainingModule> {
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
		status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(developer);

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

		super.bind(object, "code", "difficultyLevel", "updateMoment", "details", "link", "totalTime");

	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		Collection<TrainingSession> trainingSessions;
		trainingSessions = this.repository.findTrainingSessionByTrainingModuleId(object.getId());
		this.repository.deleteAll(trainingSessions);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Collection<Project> projects;
		SelectChoices choices;
		SelectChoices choicesLevels;
		Dataset dataset;

		projects = this.repository.findPublishedProjects();

		choices = SelectChoices.from(projects, "title", object.getProject());
		choicesLevels = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());
		dataset = super.unbind(object, "code", "details", "difficultyLevel", "updateMoment", "link", "totalTime", "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("difficultyLevels", choicesLevels);

		super.getResponse().addData(dataset);
	}
}
