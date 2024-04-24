
package acme.features.any.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;

@Repository
public interface AnyUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us where us.project.id = :id")
	Collection<UserStory> findOneUserStoryByProjectId(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select us from UserStory us where us.id = :id")
	UserStory findOneUserStoryById(int id);

}
