
package acme.features.developers.trainingSession;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
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
		super.getResponse().setAuthorised(true);

	}

	@Override
	public void load() {
		TrainingSesion object;

		object = new TrainingSesion();

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

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			TrainingSesion existing;

			existing = this.repository.findOneTrainingSesionByCode(object.getCode());
			super.state(existing == null, "code", "developer.training-sesion.form.error.duplicated");
		}

	}

	@Override
	public void perform(final TrainingSesion object) {
		assert object != null;

		this.repository.save(object);

	}

	@Override
	public void unbind(final TrainingSesion object) {
		assert object != null;
		int developerId;
		Collection<TrainingModule> trainingModules;
		SelectChoices choices;
		Dataset dataset;

		developerId = super.getRequest().getPrincipal().getActiveRoleId();
		System.out.println(developerId);
		trainingModules = this.repository.findTrainingModuleByDeveloperId(developerId);
		System.out.println(trainingModules);
		choices = SelectChoices.from(trainingModules, "code", object.getTrainingModule());
		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");
		dataset.put("trainingModule", choices.getSelected().getKey());
		dataset.put("trainingModules", choices);

		super.getResponse().addData(dataset);
	}

}
