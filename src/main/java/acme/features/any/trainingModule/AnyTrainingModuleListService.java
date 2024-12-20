
package acme.features.any.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;

@Service
public class AnyTrainingModuleListService extends AbstractService<Any, TrainingModule> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected AnyTrainingModuleRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<TrainingModule> objects;

		objects = this.repository.getAllTrainingModulePublished();

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "creationMoment", "difficultyLevel", "updateMoment", "link", "totalTime");

		super.getResponse().addData(dataset);
	}

}
