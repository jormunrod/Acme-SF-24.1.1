
package acme.features.sponsor.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorDashboardRepository extends AbstractRepository {

	@Query("select count(tm) from TrainingModule tm where tm.updateMoment is not null")
	Integer totalNumberOfTrainingModuleWithAnUpdateMoment();

	@Query("select count(ts) from TrainingSesion ts where ts.link is not null")
	Integer totalNumberOfTrainingSessionsWithALink();

	@Query("select avg(select count(tm.totalTime) from TrainingModule tm where tm.developer.id = d.id) from Developer d ")
	Double average();

	@Query("select tm.totalTime from TrainingModule tm")
	Collection<Integer> findAllTrainingModuleTimes();

	@Query("select min(tm.totalTime) from TrainingModule tm")
	Integer minimumTimeOfTheTrainingModules();

	@Query("select max(tm.totalTime) from TrainingModule tm")
	Integer maximumTimeOfTheTrainingModules();

}
