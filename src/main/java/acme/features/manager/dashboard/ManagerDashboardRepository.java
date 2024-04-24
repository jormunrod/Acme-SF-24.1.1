
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(us) from UserStory us where us.manager.id = :id AND us.priority = acme.entities.projects.UserStoryPriority.MUST")
	Integer totalNumberOfMUSTUserStories(int id);

	@Query("select count(us) from UserStory us where us.manager.id = :id AND us.priority = acme.entities.projects.UserStoryPriority.SHOULD")
	Integer totalNumberOfSHOULDUserStories(int id);

	@Query("select count(us) from UserStory us where us.manager.id = :id AND us.priority = acme.entities.projects.UserStoryPriority.COULD")
	Integer totalNumberOfCOULDUserStories(int id);

	@Query("select count(us) from UserStory us where us.manager.id = :id AND us.priority = acme.entities.projects.UserStoryPriority.WONT")
	Integer totalNumberOfWONTUserStories(int id);

	@Query("select avg(us.estimatedHours) from UserStory us where us.manager.id = :id")
	Double averageNumberOfEstimatedHours(int id);

	@Query(value = "SELECT STDDEV(us.estimated_hours) FROM user_story us WHERE us.manager_id = :id", nativeQuery = true)
	Double deviationNumberOfEstimatedHours(int id);

	@Query("select min(us.estimatedHours) from UserStory us where us.manager.id = :id")
	Integer minimumNumberOfEstimatedHours(int id);

	@Query("select max(us.estimatedHours) from UserStory us where us.manager.id = :id")
	Integer maximumNumberOfEstimatedHours(int id);

	@Query("select avg(p.cost.amount) from Project p where p.manager.id = :id")
	Double averageNumberOfCost(int id);

	@Query(value = "SELECT STDDEV(p.cost_amount) FROM project p WHERE p.manager_id = :id", nativeQuery = true)
	Double deviationNumberOfCost(int id);

	@Query("select min(p.cost.amount) from Project p where p.manager.id = :id")
	Integer minimumNumberOfCost(int id);

	@Query("select max(p.cost.amount) from Project p where p.manager.id = :id")
	Integer maximumNumberOfCost(int id);
}
