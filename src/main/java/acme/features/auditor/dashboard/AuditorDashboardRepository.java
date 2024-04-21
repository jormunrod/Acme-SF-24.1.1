
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

	@Query("select avg(select count(ar) from AuditRecord ar where a.worker.id = w.id) from CodeAudit ca")
	Double averageNumberOfAuditRecord();

	//Double deviationNumberOfAuditRecord();

	@Query("select min(select count(ar) from AuditRecord ar where a.worker.id = w.id) from CodeAudit ca")
	Integer minimumNumberOfAuditRecord();

	@Query("select max(select count(ar) from AuditRecord ar where a.worker.id = w.id) from CodeAudit ca")
	Integer maximumNumberOfAuditRecord();

	Double averageTimeOfPeriodInAuditRecord();

	Double deviationTimeOfPeriodInAuditRecord();

	Integer minimumTimeOfPeriodInAuditRecord();

	Integer maximumTimeOfPeriodInAuditRecord();
}
