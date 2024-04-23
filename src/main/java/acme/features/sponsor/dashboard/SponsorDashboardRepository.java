
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

	@Query("SELECT AVG(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'EUR'")
	Double averageAmountOfSponsorshipsBySponsorIdEUR(int id);

	@Query("SELECT STDDEV(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'EUR'")
	Double standardDeviationAmountBySponsorIdEUR(int id);

	@Query("SELECT MAX(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'EUR'")
	Double findMaxAmountBySponsorIdEUR(int id);

	@Query("SELECT MIN(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'EUR'")
	Double findMinAmountBySponsorIdEUR(int id);

	@Query("SELECT AVG(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'USD'")
	Double averageAmountOfSponsorshipsBySponsorIdUSD(int id);

	@Query("SELECT STDDEV(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'USD'")
	Double standardDeviationAmountBySponsorIdUSD(int id);

	@Query("SELECT MAX(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'USD'")
	Double findMaxAmountBySponsorIdUSD(int id);

	@Query("SELECT MIN(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'USD'")
	Double findMinAmountBySponsorIdUSD(int id);

	@Query("SELECT AVG(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'GBP'")
	Double averageAmountOfSponsorshipsBySponsorIdGBP(int id);

	@Query("SELECT STDDEV(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'GBP'")
	Double standardDeviationAmountBySponsorIdGBP(int id);

	@Query("SELECT MAX(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'GBP'")
	Double findMaxAmountBySponsorIdGBP(int id);

	@Query("SELECT MIN(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'GBP'")
	Double findMinAmountBySponsorIdGBP(int id);

	@Query("SELECT AVG(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'EUR'")
	Double averageQuantityOfInvoicesBySponsorIdEUR(int id);

	@Query("SELECT STDDEV(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'EUR'")
	Double standardDeviationQuantityOfInvoicesBySponsorIdEUR(int id);

	@Query("SELECT MIN(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'EUR'")
	Double minimumQuantityOfInvoicesBySponsorIdEUR(int id);

	@Query("SELECT MAX(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'EUR'")
	Double maximumQuantityOfInvoicesBySponsorIdEUR(int id);

	@Query("SELECT AVG(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'USD'")
	Double averageQuantityOfInvoicesBySponsorIdUSD(int id);

	@Query("SELECT STDDEV(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'USD'")
	Double standardDeviationQuantityOfInvoicesBySponsorIdUSD(int id);

	@Query("SELECT MIN(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'USD'")
	Double minimumQuantityOfInvoicesBySponsorIdUSD(int id);

	@Query("SELECT MAX(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'USD'")
	Double maximumQuantityOfInvoicesBySponsorIdUSD(int id);

	@Query("SELECT AVG(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'GBP'")
	Double averageQuantityOfInvoicesBySponsorIdGBP(int id);

	@Query("SELECT STDDEV(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'GBP'")
	Double standardDeviationQuantityOfInvoicesBySponsorIdGBP(int id);

	@Query("SELECT MIN(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'GBP'")
	Double minimumQuantityOfInvoicesBySponsorIdGBP(int id);

	@Query("SELECT MAX(i.quantity.amount) FROM Invoice i WHERE i.sponsorship.sponsor.id = :id AND i.quantity.currency = 'GBP'")
	Double maximumQuantityOfInvoicesBySponsorIdGBP(int id);
}
