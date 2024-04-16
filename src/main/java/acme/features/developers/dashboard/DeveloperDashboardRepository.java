
package acme.features.developers.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface DeveloperDashboardRepository extends AbstractRepository {

	@Query("select count(tm) from TrainingModule tm where tm.updateMoment is not null")
	Integer totalNumberOfTrainingModuleWithAnUpdateMoment();

	@Query("select count(ts) from TrainingSesion ts where ts.link is not null")
	Integer totalNumberOfTrainingSessionsWithALink();

	@Query("select avg(tm.totalTime) from TrainingModule tm")
	Double average();

	@Query("SELECT tm.totalTime FROM TrainingModule tm")
	Collection<Integer> findAllTrainingModuleTimes();

	@Query("select min(tm.totalTime) from TrainingModule tm")
	Integer minimumTimeOfTheTrainingModules();

	@Query("select max(tm.totalTime) from TrainingModule tm")
	Integer maximumTimeOfTheTrainingModules();

}
