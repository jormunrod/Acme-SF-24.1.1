
package acme.features.auditor.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.entities.projects.Project;
import acme.roles.Auditor;

@Repository
public interface AuditorCodeAuditRepository extends AbstractRepository {

	@Query("select ca from CodeAudit ca where ca.auditor.id = :id")
	Collection<CodeAudit> findAllCodeAuditsByAuditorId(int id);

	@Query("select ca from CodeAudit ca where ca.id = :id")
	CodeAudit findOneCodeAuditById(int id);

	@Query("select DISTINCT ca.project from CodeAudit ca where ca.auditor.id = :auditorId")
	Collection<Project> findAllProjectsByAuditorId(int auditorId);

	@Query("select a from Auditor a where a.id = :id")
	Auditor findOneAuditorById(int id);

	@Query("select p from Project p where p.isPublished = 1")
	Collection<Project> findPublishedProjects();

	@Query("select ca from CodeAudit ca where ca.code = :code")
	CodeAudit findOneCodeAuditByCode(String code);

	@Query("select count(ar) from AuditRecord ar where ar.codeAudit.id = :id")
	int countAuditRecordsByCodeAuditId(int id);

	@Query("select DISTINCT ar from AuditRecord ar where ar.codeAudit.id = :id")
	Collection<AuditRecord> findAllAuditRecordsByCodeAuditId(int id);

	@Query("select ar.mark from AuditRecord ar where ar.codeAudit.id =:id")
	Collection<Mark> findMarksByCodeAuditId(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);
}
