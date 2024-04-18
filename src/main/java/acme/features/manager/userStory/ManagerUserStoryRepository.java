
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.UserStory;

public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us where us.manager.id = :id")
	Collection<UserStory> findAllUserStoryByManagerId(int id);
}
