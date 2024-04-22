
package acme.features.any.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.trainings.TrainingModule;

@Repository
public interface AnyTrainingModuleRepository extends AbstractRepository {

	@Query("select tm from TrainingModule tm where tm.draftMode = FALSE")
	Collection<TrainingModule> getAllTrainingModulePublished();

	@Query("select tm from TrainingModule tm where tm.id =:id")
	TrainingModule findTrainingModuleById(int id);

	@Query("select p from Project p where p.isPublished = TRUE")
	Collection<Project> findPublishedProjects();
}
