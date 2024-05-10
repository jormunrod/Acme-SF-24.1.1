
package acme.features.developers.trainingSession;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSession;

@Repository
public interface DeveloperTrainingSessionRepository extends AbstractRepository {

	@Query("select ts from TrainingSession ts where ts.trainingModule.id = :id")
	Collection<TrainingSession> findTrainingSessionByTrainingModuleId(int id);

	@Query("select ts from TrainingSession ts where ts.id = :id")
	TrainingSession findTrainingSessionById(int id);

	@Query("select ts from TrainingSession ts where ts.code = :code")
	TrainingSession findOneTrainingSessionByCode(String code);

	@Query("select tm from TrainingModule tm where tm.id =:id")
	TrainingModule findOneTrainingModuleById(int id);

	@Query("select DISTINCT tm from TrainingModule tm")
	TrainingModule findAllDistinctTrainingModuleById(int id);

	@Query("select DISTINCT tm from TrainingModule tm where tm.developer.id = :id")
	Collection<TrainingModule> findTrainingModuleByDeveloperId(int id);

	@Query("select ts.trainingModule from TrainingSession ts where ts.id = :id")
	TrainingModule findOneTrainingModuleByTrainingSessionId(int id);

}
