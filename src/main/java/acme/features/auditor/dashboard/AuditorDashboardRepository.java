
package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count(ca) from CodeAudit ar where ca.auditor.id = :auditorId && ca.type = 'STATIC'")
	Integer totalNumberOfStaticCodeAudits(int auditorId);

	@Query("select count(ca) from CodeAudit ar where ca.auditor.id = :auditorId && ca.type = 'DYNAMIC'")
	Integer totalNumberOfDynamicCodeAudits(int auditorId);

	@Query("select avg(select count(ar) from AuditRecord ar where a.worker.id = w.id) from CodeAudit ca where ca.auditor.id = :auditorId")
	Double averageNumberOfAuditRecord(int auditorId);

	//Double deviationNumberOfAuditRecord();

	@Query("select min(select count(ar) from AuditRecord ar where a.worker.id = w.id) from CodeAudit ca where ca.auditor.id = :auditorId")
	Integer minimumNumberOfAuditRecord(int auditorId);

	@Query("select max(select count(ar) from AuditRecord ar where a.worker.id = w.id) from CodeAudit ca where ca.auditor.id = :auditorId")
	Integer maximumNumberOfAuditRecord(int auditorId);

	Double averageTimeOfPeriodInAuditRecord(int auditorId);

	Double deviationTimeOfPeriodInAuditRecord(int auditorId);

	Integer minimumTimeOfPeriodInAuditRecord(int auditorId);

	Integer maximumTimeOfPeriodInAuditRecord(int auditorId);
}
