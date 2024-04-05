
package acme.features.developers.trainingSession;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.trainings.TrainingSesion;

@Repository
public interface DeveloperTrainingSessionRepository extends AbstractRepository {

	@Query("select ts from TrainingSesion ts where ts.trainingModule.id = :id")
	Collection<TrainingSesion> findTrainingSesionByTrainingModuleId(int id);

	@Query("select ts from TrainingSesion ts where ts.id = :id")
	TrainingSesion getTrainingSesionById(int id);

}
