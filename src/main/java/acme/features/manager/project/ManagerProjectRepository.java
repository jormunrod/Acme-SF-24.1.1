
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditRecord;
import acme.entities.audits.CodeAudit;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.entities.projects.Assignment;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.trainings.TrainingModule;
import acme.entities.trainings.TrainingSession;
import acme.roles.Manager;

@Repository
public interface ManagerProjectRepository extends AbstractRepository {

	@Query("select p from Project p where p.manager.id = :id")
	Collection<Project> findAllProjectByManagerId(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select p from Project p where p.code = :code")
	Project findOneProjectByCode(String code);

	@Query("select count (a.userStory) from Assignment a where a.project.id = :id and a.userStory.isPublished = true")
	Integer findAllPublishedUserStoriesByProjectId(int id);

	@Query("select a from Assignment a where a.project.id = :id")
	Collection<Assignment> findAllAssignmentsByProjectId(int id);

	@Query("select tm from TrainingModule tm where tm.project.id = :id")
	Collection<TrainingModule> findAllTrainingModulesByProjectId(int id);

	@Query("select c from Contract c where c.project.id = :id")
	Collection<Contract> findAllContractsByProjectId(int id);

	@Query("select ca from CodeAudit ca where ca.project.id = :id")
	Collection<CodeAudit> findAllCodeAuditsByProjectId(int id);

	@Query("select s from Sponsorship s where s.project.id = :id")
	Collection<Sponsorship> findAllSponsorshipsByProjectId(int id);

	@Query("select pl from ProgressLog pl where pl.contract.id = :id")
	Collection<ProgressLog> findAllProgressLogsByContractId(int id);

	@Query("select ts from TrainingSession ts where ts.trainingModule.id = :id")
	Collection<TrainingSession> findAllTrainingSesionByTrainingModuleId(int id);

	@Query("select i from Invoice i where i.sponsorship.id = :id")
	Collection<Invoice> findAllInvoicesBySponsorshipId(int id);

	@Query("select ar from AuditRecord ar where ar.codeAudit.id = :id")
	Collection<AuditRecord> findAllAduditRecordsByCodeAuditId(int id);
}
