
package acme.features.manager.assignment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Assignment;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerAssignmentRepository extends AbstractRepository {

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select u from UserStory u where u.manager.id = :id")
	Collection<UserStory> findAllUserStoriesByManagerId(int id);

	@Query("select a.userStory from Assignment a where a.project.id = :id")
	Collection<UserStory> findAllUserStoriesByProjectId(int id);

	@Query("select u from UserStory u where u.id = :id")
	UserStory findOneUserStoryById(int id);

	@Query("select a from Assignment a where a.project.id = :projectId and a.userStory.id = :userStoryId")
	Assignment findOneAssignmentByProjectAndUserStoryId(int projectId, int userStoryId);

	@Query("select us.manager from UserStory us where us.id = :id")
	Manager findOneManagerByUserStoryId(int id);

}
