
package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count(ca) from CodeAudit ar where ca.type = 'STATIC'")
	Integer totalNumberOfStaticCodeAudits();

	@Query("select count(ca) from CodeAudit ar where ca.type = 'DYNAMIC'")
	Integer totalNumberOfDynamicCodeAudits();

	Double averageNumberOfAuditRecord();

	Double deviationNumberOfAuditRecord();

	Integer minimumNumberOfAuditRecord();

	Integer maximumNumberOfAuditRecord();

	Double averageTimeOfPeriodInAuditRecord();

	Double deviationTimeOfPeriodInAuditRecord();

	Integer minimumTimeOfPeriodInAuditRecord();

	Integer maximumTimeOfPeriodInAuditRecord();
}
