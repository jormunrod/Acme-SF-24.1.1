
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;

public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select ca from CodeAudit ca where ca.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select ar from AuditRecord ar where ar.codeAudit.id = :id")
	Collection<AuditRecord> findAllAuditRecordByCodeAuditId(int id);

	@Query("select ar.codeAudit from AuditRecord ar where ar.id = :id")
	CodeAudit findOneCodeAuditByAuditRecordId(int id);

	@Query("select ar from AuditRecord ar where ar.id = :id")
	AuditRecord findOneAuditRecordById(int id);
}
