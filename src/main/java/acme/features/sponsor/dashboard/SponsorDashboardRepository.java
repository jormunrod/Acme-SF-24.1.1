
package acme.features.sponsor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Invoice i where i.sponsorship.sponsor.id = :id and i.tax <= 0.21")
	Integer countInvoicesBySponsorIdAndTaxLimit21(int id);

	@Query("select count(s) from Sponsorship s where s.sponsor.id = :id and s.link is not null and s.link <> ''")
	Integer countSponsorshipsWithNonEmptyLinkBySponsorId(int id);

	@Query("SELECT AVG(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id")
	Double averageAmountOfSponsorshipsBySponsorId(int id);

	@Query("SELECT STDDEV(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id")
	Double standardDeviationAmountBySponsorId(int id);

	@Query("SELECT MAX(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id")
	Double findMaxAmountBySponsorId(int id);

	@Query("SELECT MIN(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id")
	Double findMinAmountBySponsorId(int id);

}
