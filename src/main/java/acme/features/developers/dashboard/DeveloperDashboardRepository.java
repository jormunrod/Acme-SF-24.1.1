
package acme.features.developers.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface DeveloperDashboardRepository extends AbstractRepository {

	@Query("select count(tm) from TrainingModule tm where tm.updateMoment is not null and tm.developer.id = :developerId")
	Integer totalNumberOfTrainingModuleWithAnUpdateMoment(int developerId);

	@Query("select count(ts) from TrainingSession ts where (ts.link is not null and ts.link is not '') and ts.trainingModule.developer.id = :developerId")
	Integer totalNumberOfTrainingSessionsWithALink(int developerId);

	@Query("select avg(tm.totalTime) from TrainingModule tm where tm.developer.id = :developerId ")
	Double average(int developerId);

	@Query("select stddev(tm.totalTime) from TrainingModule tm where tm.developer.id = :developerId")
	Double deviation(int developerId);

	@Query("select tm.totalTime from TrainingModule tm where tm.developer.id = :developerId")
	Collection<Integer> findAllTrainingModuleTimes(int developerId);

	@Query("select min(tm.totalTime) from TrainingModule tm where tm.developer.id = :developerId ")
	Integer minimumTimeOfTheTrainingModules(int developerId);

	@Query("select max(tm.totalTime) from TrainingModule tm where tm.developer.id = :developerId ")
	Integer maximumTimeOfTheTrainingModules(int developerId);

}
