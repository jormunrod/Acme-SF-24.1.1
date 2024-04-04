
package acme.features.sponsor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface SponsorSponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.sponsor.id = :id")
	Collection<Sponsorship> findOneAnnouncementById(int id);

	@Query("select s from Sponsorship s ")
	Collection<Sponsorship> findAllSponsorship();

}