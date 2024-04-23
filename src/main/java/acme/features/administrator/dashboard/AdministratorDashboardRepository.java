
package acme.features.administrator.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.claims.Claim;
import acme.entities.risk.Risk;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Invoice i where i.sponsorship.sponsor.id = :id and i.tax <= 0.21")
	Integer countInvoicesBySponsorIdAndTaxLimit21(int id);

	@Query("select count(s) from Sponsorship s where s.sponsor.id = :id and s.link is not null and s.link <> ''")
	Integer countSponsorshipsWithNonEmptyLinkBySponsorId(int id);

	@Query("SELECT AVG(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'EUR'")
	Double averageAmountOfSponsorshipsBySponsorIdEUR(int id);

	@Query("SELECT STDDEV(s.amount.amount) FROM Sponsorship s WHERE s.sponsor.id = :id AND s.amount.currency = 'EUR'")
	Double standardDeviationAmountBySponsorIdEUR(int id);

	@Query("SELECT count(u) FROM Auditor u")
	Integer countAuditors();

	@Query("SELECT count(u) FROM Client u")
	Integer countClients();

	@Query("SELECT count(u) FROM Consumer u")
	Integer countConsumers();

	@Query("SELECT count(u) FROM Developer u")
	Integer countDevelopers();

	@Query("SELECT count(u) FROM Manager u")
	Integer countManagers();

	@Query("SELECT count(u) FROM Provider u")
	Integer countProviders();

	@Query("SELECT count(u) FROM Sponsor u")
	Integer countSponsors();

	@Query("SELECT count(n) FROM Notice n")
	Integer countNotices();
	@Query("SELECT count(n) FROM Notice n WHERE n.link IS NOT NULL AND n.link <> '' AND n.email IS NOT NULL AND n.email <> ''")
	Integer countNumberOfNoticesWithLinkAndEmail();

	@Query("SELECT count(o) FROM Objective o")
	Integer countObjectives();

	@Query("SELECT count(o) FROM Objective o WHERE o.isCritical = true")
	Integer countCriticalObjectives();

	@Query("SELECT count(o) FROM Objective o WHERE o.isCritical = false")
	Integer countNonCriticalObjectives();

	@Query("SELECT r FROM Risk r ")
	Collection<Risk> allRisks();

	@Query("SELECT c FROM Claim c ")
	Collection<Claim> allClaims();

}
