
package acme.features.sponsor.invoice;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface SponsorInvoiceRepository extends AbstractRepository {

	@Query("select i from Invoice i")
	Collection<Invoice> findAllInvoice();

	@Query("select s from Sponsorship s where s.code = :code")
	Sponsorship findSponsorshipByCode(String code);

	@Query("select i from Invoice i where i.sponsorship.id = :id")
	Collection<Invoice> findInvoicesBySponsorshipId(int id);

}
