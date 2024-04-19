
package acme.features.manager.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface ManagerDashboardRepository extends AbstractRepository {

	@Query("select count(us) from UserStory us where us.priority = acme.entities.projects.UserStoryPriority.MUST")
	Integer totalNumberOfMUSTUserStories();

	@Query("select count(us) from UserStory us where us.priority = acme.entities.projects.UserStoryPriority.SHOULD")
	Integer totalNumberOfSHOULDUserStories();

	@Query("select count(us) from UserStory us where us.priority = acme.entities.projects.UserStoryPriority.COULD")
	Integer totalNumberOfCOULDUserStories();

	@Query("select count(us) from UserStory us where us.priority = acme.entities.projects.UserStoryPriority.WONT")
	Integer totalNumberOfWONTUserStories();

	@Query("select avg(us.estimatedHours) from UserStory us")
	Double averageNumberOfEstimatedHours();

	//@Query("select sqrt(sum((us.estimatedHours - ?1) * (us.estimatedHours - ?1)) / count(us)) from UserStory us")
	//Double deviationNumberOfEstimatedHours();

	@Query("select min(us.estimatedHours) from UserStory us")
	Integer minimumNumberOfEstimatedHours();

	@Query("select max(us.estimatedHours) from UserStory us")
	Integer maximumNumberOfEstimatedHours();

	@Query("select avg(p.cost.amount) from Project p")
	Double averageNumberOfCost();

	//@Query("select sqrt(sum((p.cost.amount - ?1) * (p.cost.amount - ?1)) / count(p)) from Project p")
	//Double deviationNumberOfCost();

	@Query("select min(p.cost.amount) from Project p")
	Integer minimumNumberOfCost();

	@Query("select max(p.cost.amount) from Project p")
	Integer maximumNumberOfCost();
}
