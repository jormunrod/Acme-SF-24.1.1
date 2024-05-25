
package acme.features.sponsor.invoice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;
import acme.roles.Sponsor;

@Repository
public interface SponsorInvoiceRepository extends AbstractRepository {

	@Query("select i from Invoice i")
	Collection<Invoice> findAllInvoice();

	@Query("select i from Invoice i where i.sponsorship.id = :id")
	Collection<Invoice> findInvoicesBySponsorshipId(int id);

	@Query("select i from Invoice i where i.id = :id")
	Invoice findInvoiceById(int id);

	@Query("select i.sponsorship from Invoice i where i.id = :id")
	Sponsorship findOneSponsorshipByInvoiceId(int id);

	@Query("select s from Sponsor s where s.id = :id")
	Sponsor findOneSponsorById(int id);

	@Query("select s from Sponsorship s where s.id = :id")
	Sponsorship findOneSponsorshipById(int id);

	@Query("select i from Invoice i where i.code = :code")
	Invoice findInvoiceByCode(String code);

	@Query("SELECT SUM(i.totalAmount.amount) FROM Invoice i WHERE i.sponsorship.id = :id")
	Double sumTotalAmountBySponsorshipId(int id);

	@Query("SELECT SUM(i.totalAmount.amount) FROM Invoice i WHERE i.sponsorship.id = :id and i.draftMode = false")
	Double sumTotalAmountPublishedBySponsorshipId(int id);
}
