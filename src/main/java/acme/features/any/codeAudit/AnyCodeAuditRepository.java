
package acme.features.any.codeAudit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.CodeAudit;
import acme.entities.projects.Project;

@Repository
public interface AnyCodeAuditRepository extends AbstractRepository {

	@Query("select ca from CodeAudit ca where ca.isPublished = true")
	Collection<CodeAudit> findAllCodeAuditPublished();

	@Query("select ca from CodeAudit ca where ca.id = :id")
	CodeAudit findCodeAuditById(int id);

	@Query("select p from Project p where p.isPublished = true")
	Collection<Project> findAllPublishedProjects();
}
