
package acme.features.any.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.trainings.TrainingModule;

@Repository
public interface AnyTrainingModuleRepository extends AbstractRepository {

	@Query("select tm from TrainingModule tm where tm.draftMode = FALSE")
	Collection<TrainingModule> getAllTrainingModulePublished();
}
