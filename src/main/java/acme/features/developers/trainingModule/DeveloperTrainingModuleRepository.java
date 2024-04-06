
package acme.features.developers.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.trainings.TrainingModule;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	@Query("select tm from TrainingModule tm where tm.developer.id = :id")
	Collection<TrainingModule> getAllTrainingModuleByDeveloperId(int id);

	@Query("select tm from TrainingModule tm where tm.id =:id")
	TrainingModule findTrainingModuleById(int id);

	@Query("select d from Developer d where d.id =:id")
	Developer findOneDeveloperById(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select tm from TrainingModule tm where tm.code = :code")
	TrainingModule findOneTrainingModuleByCode(String code);

}
