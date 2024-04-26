/**
 * Repository for the client dashboard.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-20
 */

package acme.features.client.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.data.datatypes.Money;
import acme.client.repositories.AbstractRepository;

@Repository
public interface ClientDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completenessPercentage < 0.25 AND pl.contract.client.id = :id")
	Integer numberOfProgressLogsBelow25(int id);

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completenessPercentage >= 0.25 AND pl.completenessPercentage < 0.50 AND pl.contract.client.id = :id")
	Integer numberOfProgressLogsBetween25And50(int id);

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completenessPercentage >= 0.50 AND pl.completenessPercentage <= 0.75 AND pl.contract.client.id = :id")
	Integer numberOfProgressLogsBetween50And75(int id);

	@Query("SELECT COUNT(pl) FROM ProgressLog pl WHERE pl.completenessPercentage > 0.75 AND pl.contract.client.id = :id")
	Integer numberOfProgressLogsAbove75(int id);

	@Query("SELECT AVG(c.budget.amount) FROM Contract c WHERE c.client.id = :id")
	Double averageBudget(int id);

	@Query("SELECT MIN(c.budget.amount) FROM Contract c WHERE c.client.id = :id")
	Double minimumBudget(int id);

	@Query("SELECT MAX(c.budget.amount) FROM Contract c WHERE c.client.id = :id")
	Double maximumBudget(int id);

	@Query("SELECT c.budget FROM Contract c WHERE c.client.id = :id")
	Collection<Money> findBudgetsByClientId(int id);

}
