
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;
import acme.roles.Sponsor;

@Repository
public interface SponsorSponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.sponsor.id = :id")
	Collection<Sponsorship> findSponsorshipBySponsorId(int id);

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findOneSponsorshipById(int id);

	@Query("select s from Sponsor s where s.id = :id")
	Sponsor findOneSponsorById(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select DISTINCT s.project from Sponsorship s where s.sponsor.id = :id")
	Collection<Project> findProjectsBySponsorId(int id);

	@Query("select s from Sponsorship s")
	Collection<Sponsorship> findAllSponsorship();

	@Query("select s from Sponsorship s where s.code = :code")
	Sponsorship findSponsorshipByCode(String code);

	@Query("select p from Project p where p.isPublished = true")
	Collection<Project> findAllPublishedProjects();

	@Query("select i from Invoice i where i.sponsorship.id = :id")
	Collection<Invoice> findInvoicesBySponsorshipId(int id);

	@Query("SELECT SUM(i.totalAmount.amount) FROM Invoice i WHERE i.sponsorship.id = :id")
	Double sumTotalAmountBySponsorshipId(int id);

	@Query("SELECT SUM(i.totalAmount.amount) FROM Invoice i WHERE i.sponsorship.id = :id and i.draftMode = false")
	Double sumTotalAmountPublishedBySponsorshipId(int id);

	@Query("SELECT i FROM Invoice i WHERE i.sponsorship.id = :id and i.draftMode = true")
	Collection<Invoice> invoicesNotPublishedBySponsorshipId(int id);

	@Query("select i from Invoice i where i.sponsorship.id = :id and i.draftMode = false")
	Collection<Invoice> findPublishedInvoicesBySponsorshipId(int id);

}
