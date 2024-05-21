
package acme.features.developers.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleListService extends AbstractService<Developer, TrainingModule> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingModuleRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int developerId;

		developerId = super.getRequest().getPrincipal().getActiveRoleId();
		status = super.getRequest().getPrincipal().hasRole(this.repository.findOneDeveloperById(developerId));

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Collection<TrainingModule> objects;

		objects = this.repository.getAllTrainingModuleByDeveloperId(super.getRequest().getPrincipal().getActiveRoleId());

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;
		String draftModeIntl = object.isDraftMode() ? "✔️" : "❌";
		dataset = super.unbind(object, "code", "creationMoment", "difficultyLevel", "updateMoment", "link", "totalTime", "draftMode");
		dataset.put("draftMode", draftModeIntl);

		super.getResponse().addData(dataset);
	}

}
