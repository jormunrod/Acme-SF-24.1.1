
package acme.features.sponsor.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.roles.Sponsor;

@Repository
public interface SponsorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Invoice i where i.sponsorship.sponsor.id = :id and i.tax <= 0.21")
	Integer countInvoicesBySponsorIdAndTaxLimit21(int id);

	@Query("select count(s) from Sponsorship s where s.sponsor.id = :id and s.link is not null and s.link <> ''")
	Integer countSponsorshipsWithNonEmptyLinkBySponsorId(int id);

	@Query("SELECT s.amount.currency, AVG(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id GROUP BY s.amount.currency")
	Collection<Object[]> averageAmountOfSponsorshipsBySponsorIdGroupedByCurrency(int id);

	@Query("SELECT s.amount.currency, MIN(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id GROUP BY s.amount.currency")
	Collection<Object[]> minimumAmountOfSponsorshipsBySponsorIdGroupedByCurrency(int id);

	@Query("SELECT s.amount.currency, MAX(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id GROUP BY s.amount.currency")
	Collection<Object[]> maximunAmountOfSponsorshipsBySponsorIdGroupedByCurrency(int id);

	@Query("SELECT s.amount.currency, STDDEV(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id GROUP BY s.amount.currency")
	Collection<Object[]> deviationAmountOfSponsorshipsBySponsorIdGroupedByCurrency(int id);

	@Query("select s from Sponsor s where s.id = :id")
	Sponsor findOneSponsorById(int id);

	@Query("SELECT i.quantity.currency, AVG(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id GROUP BY i.quantity.currency")
	Collection<Object[]> averageQuantityInvoicesBySponsorIdGroupedByCurrency(int id);

	@Query("SELECT i.quantity.currency, MIN(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id GROUP BY i.quantity.currency")
	Collection<Object[]> minimumQuantityInvoicesBySponsorIdGroupedByCurrency(int id);

	@Query("SELECT i.quantity.currency, MAX(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id GROUP BY i.quantity.currency")
	Collection<Object[]> maximunQuantityInvoicesBySponsorIdGroupedByCurrency(int id);

	@Query("SELECT i.quantity.currency, STDDEV(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id GROUP BY i.quantity.currency")
	Collection<Object[]> deviationQuantityInvoicesBySponsorIdGroupedByCurrency(int id);

}
