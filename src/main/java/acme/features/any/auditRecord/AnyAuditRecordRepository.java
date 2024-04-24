
package acme.features.any.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;

@Repository
public interface AnyAuditRecordRepository extends AbstractRepository {

	@Query("select ar from AuditRecord ar where ar.codeAudit.id = :id")
	Collection<AuditRecord> findAuditRecordByCodeAuditId(int id);

	@Query("select ar from AuditRecord ar where ar.id = :id")
	AuditRecord findAuditRecordById(int id);
}
