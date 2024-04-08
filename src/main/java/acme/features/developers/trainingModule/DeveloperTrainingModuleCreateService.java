
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
public class DeveloperTrainingModuleCreateService extends AbstractService<Developer, TrainingModule> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingModuleRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);

	}

	@Override
	public void load() {
		TrainingModule object;
		Developer developer;

		developer = this.repository.findOneDeveloperById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new TrainingModule();
		object.setDeveloper(developer);

		super.getBuffer().addData(object);

	}

	@Override
	public void bind(final TrainingModule object) {
		assert object != null;
		int projectId;
		Project project;

		projectId = super.getRequest().getData("project", int.class);
		project = this.repository.findOneProjectById(projectId);
		super.bind(object, "code", "creationMoment", "difficultyLevel", "updateMoment", "details", "link", "totalTime");
		object.setProject(project);
	}

	@Override
	public void validate(final TrainingModule object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingModule existing;

			existing = this.repository.findOneTrainingModuleByCode(object.getCode());
			super.state(existing == null, "code", "developer.training-module.form.error.duplicated");
		}

		if (!super.getBuffer().getErrors().hasErrors("totalTime"))
			super.state(object.getTotalTime() <= 0, "totalTime", "developer.training-module.form.error.invalid-total-time");

	}

	@Override
	public void perform(final TrainingModule object) {
		assert object != null;

		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;
		int developerId;
		Collection<Project> projects;
		SelectChoices choices;
		SelectChoices choicesLevels;
		Dataset dataset;

		developerId = super.getRequest().getPrincipal().getActiveRoleId();
		projects = this.repository.findProjectsByDeveloperId(developerId);
		choices = SelectChoices.from(projects, "title", object.getProject());
		choicesLevels = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());
		dataset = super.unbind(object, "code", "creationMoment", "difficultyLevel", "updateMoment", "details", "link", "totalTime");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("difficultyLevels", choicesLevels);

		super.getResponse().addData(dataset);
	}
}
