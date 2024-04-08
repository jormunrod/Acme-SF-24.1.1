
package acme.features.auditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.CodeAudit;

@Repository
public interface AuditorCodeAuditRepository extends AbstractRepository {

	@Query("select ca from CodeAudit ca where ca.auditor.id = :id")
	Collection<CodeAudit> getAllCodeAuditsByAuditorId(int id);
}
