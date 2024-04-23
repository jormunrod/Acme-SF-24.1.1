
package acme.features.auditor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditorDashboardRepository extends AbstractRepository {

	@Query("select count(ca) from CodeAudit ca where ca.auditor.id = :auditorId AND ca.type = acme.entities.audits.CodeAuditType.STATIC")
	Integer totalNumberOfStaticCodeAudits(int auditorId);

	@Query("select count(ca) from CodeAudit ca where ca.auditor.id = :auditorId AND ca.type = acme.entities.audits.CodeAuditType.DYNAMIC")
	Integer totalNumberOfDynamicCodeAudits(int auditorId);

	@Query("select avg(select count(ar) from AuditRecord ar where ar.codeAudit.id = ca.id) from CodeAudit ca where ca.auditor.id = :auditorId")
	Double averageNumberOfAuditRecord(int auditorId);

	@Query(value = "SELECT STDDEV_SAMP(audit_count) FROM (SELECT COUNT(ar.id) as audit_count FROM audit_record ar INNER JOIN code_audit ca ON ar.code_audit_id = ca.id WHERE ca.auditor_id = :auditorId GROUP BY ca.id) as subquery", nativeQuery = true)
	Double deviationNumberOfAuditRecord(int auditorId);

	@Query("select min(select count(ar) from AuditRecord ar where ar.codeAudit.id = ca.id) from CodeAudit ca where ca.auditor.id = :auditorId")
	Integer minimumNumberOfAuditRecord(int auditorId);

	@Query("select max(select count(ar) from AuditRecord ar where ar.codeAudit.id = ca.id) from CodeAudit ca where ca.auditor.id = :auditorId")
	Integer maximumNumberOfAuditRecord(int auditorId);

	@Query(value = "SELECT AVG(TIMESTAMPDIFF(HOUR, ar.audit_period_start, ar.audit_period_end)) FROM audit_record ar INNER JOIN code_audit ca ON ar.code_audit_id = ca.id WHERE ca.auditor_id = :auditorId", nativeQuery = true)
	Double averageTimeOfPeriodInAuditRecord(int auditorId);

	@Query(value = "SELECT STDDEV_SAMP(TIMESTAMPDIFF(HOUR, ar.audit_period_start, ar.audit_period_end)) FROM audit_record ar INNER JOIN code_audit ca ON ar.code_audit_id = ca.id WHERE ca.auditor_id = :auditorId", nativeQuery = true)
	Double deviationTimeOfPeriodInAuditRecord(int auditorId);

	@Query(value = "SELECT MIN(TIMESTAMPDIFF(HOUR, ar.audit_period_start, ar.audit_period_end)) FROM audit_record ar INNER JOIN code_audit ca ON ar.code_audit_id = ca.id WHERE ca.auditor_id = :auditorId", nativeQuery = true)
	Integer minimumTimeOfPeriodInAuditRecord(int auditorId);

	@Query(value = "SELECT MAX(TIMESTAMPDIFF(HOUR, ar.audit_period_start, ar.audit_period_end)) FROM audit_record ar INNER JOIN code_audit ca ON ar.code_audit_id = ca.id WHERE ca.auditor_id = :auditorId", nativeQuery = true)
	Integer maximumTimeOfPeriodInAuditRecord(int auditorId);
}
