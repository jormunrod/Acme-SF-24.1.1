
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Assignment;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.roles.Manager;

public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select a.userStory from Assignment a where a.project.id = :id")
	Collection<UserStory> findAllUserStoryByProjectId(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select a.project from Assignment a where a.project.id = :id")
	Project findOneProjectByUserStoryId(int id);

	@Query("select us from UserStory us where us.id = :id")
	UserStory findOneUserStoryById(int id);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select us from UserStory us where us.title = :title")
	UserStory findOneUserStoryByTitle(String title);

	@Query("select a from Assignment a where a.userStory.id = :id")
	Collection<Assignment> findAllAssignmentsByUserStoryId(int id);

	@Query("select us from UserStory us where us.manager.id = :id")
	Collection<UserStory> findAllUserStoryByManagerId(int id);
}
