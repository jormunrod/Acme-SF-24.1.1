
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
import acme.roles.Developer;

@Service
public class DeveloperTrainingModulePublishService extends AbstractService<Developer, TrainingModule> {
	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingModuleRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		Developer developer;
		boolean status = false;

		developer = this.repository.findOneDeveloperById(super.getRequest().getPrincipal().getActiveRoleId());

		int trainingModuleId = super.getRequest().getData("id", int.class);
		TrainingModule trainingModule = this.repository.findTrainingModuleById(trainingModuleId);

		if (super.getRequest().hasData("project")) {
			int projectId = super.getRequest().getData("project", int.class);
			if (projectId > 0) {
				Project project = this.repository.findOneProjectById(projectId);
				if (project != null && project.isPublished() && trainingModule != null && trainingModule.isDraftMode())
					status = super.getRequest().getPrincipal().hasRole(developer);
			} else
				status = trainingModule != null && trainingModule.isDraftMode() && super.getRequest().getPrincipal().hasRole(developer);
		}

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

		super.bind(object, "code", "difficultyLevel", "details", "link", "totalTime");

	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		int id = object.getId();
		int totalNumberOfTrainingSessions = this.repository.countTrainingSessionsByTrainingModuleId(id);
		int publishedTrainingSessions = this.repository.countPublishedTrainingSessionsByTrainingModuleId(id);

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingModule existing;

			existing = this.repository.findOneTrainingModuleByCode(object.getCode());
			if (existing != null && existing.getId() != object.getId())
				super.state(false, "code", "developer.training-module.form.error.duplicated");
		}

		if (totalNumberOfTrainingSessions == 0)
			super.state(false, "*", "developer.training-module.form.error.hasNotSessions");
		else if (publishedTrainingSessions != totalNumberOfTrainingSessions)
			super.state(false, "*", "developer.training-module.form.error.hasUnpublishedSessions");

	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;
		TrainingModule trainingModule;
		trainingModule = object;

		trainingModule.setDraftMode(false);

		this.repository.save(trainingModule);

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
		dataset = super.unbind(object, "code", "details", "difficultyLevel", "link", "totalTime", "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("difficultyLevels", choicesLevels);
		dataset.put("creationMoment", object.getCreationMoment());
		dataset.put("updateMoment", object.getUpdateMoment());

		super.getResponse().addData(dataset);
	}
}
