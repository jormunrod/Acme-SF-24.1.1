
package acme.features.developers.trainingSession;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSesion;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionListService extends AbstractService<Developer, TrainingSesion> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected DeveloperTrainingSessionRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		TrainingModule trainingModule;

		id = super.getRequest().getData("id", int.class);
		trainingModule = this.repository.findOneTrainingModuleById(id);
		status = trainingModule != null && super.getRequest().getPrincipal().hasRole(trainingModule.getDeveloper());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<TrainingSesion> objects;
		int id;

		id = super.getRequest().getData("id", int.class);
		objects = this.repository.findTrainingSesionByTrainingModuleId(id);

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final TrainingSesion object) {
		assert object != null;
		int id;
		Dataset dataset;
		id = super.getRequest().getData("id", int.class);
		dataset = super.unbind(object, "code", "startDate", "finishDate", "location", "instructor", "contactEmail", "link");

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<TrainingSesion> objects) {
		assert objects != null;

		int masterId;

		masterId = super.getRequest().getData("id", int.class);

		super.getResponse().addGlobal("masterId", masterId);

	}
}
