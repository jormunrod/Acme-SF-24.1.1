
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;

public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us where us.project.id = :id")
	Collection<UserStory> findAllUserStoryByProjectId(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select us.project from UserStory us where us.id = :id")
	Project findOneProjectByUserStoryId(int id);

	@Query("select us from UserStory us where us.id = :id")
	UserStory findOneUserStoryById(int id);
}
