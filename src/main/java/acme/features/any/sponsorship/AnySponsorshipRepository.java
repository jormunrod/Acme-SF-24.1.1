
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface AnySponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.draftMode = FALSE")
	Collection<Sponsorship> getAllSponsorshipPublished();

	@Query("select s from Sponsorship s where s.id =:id")
	Sponsorship findSponsorshipById(int id);

	@Query("select p from Project p where p.isPublished = TRUE")
	Collection<Project> findPublishedProjects();
}
