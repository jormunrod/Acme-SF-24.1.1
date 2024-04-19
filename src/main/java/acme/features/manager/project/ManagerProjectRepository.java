
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.CodeAudit;
import acme.entities.projects.Contract;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.trainings.TrainingModule;
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

	@Query("select count (us) from UserStory us where us.project.id = :id and us.isPublished = true")
	Integer findAllPublishedUserStoriesByProjectId(int id);

	@Query("select us from UserStory us where us.project.id = :id")
	Collection<UserStory> findAllUserStoriesByProjectId(int id);

	@Query("select tm from TrainingModule tm where tm.project.id = :id")
	Collection<TrainingModule> findAllTrainingModulesByProjectId(int id);

	@Query("select c from Contract c where c.project.id = :id")
	Collection<Contract> findAllContractsByProjectId(int id);

	@Query("select ca from CodeAudit ca where ca.project.id = :id")
	Collection<CodeAudit> findAllCodeAuditsByProjectId(int id);

	@Query("select s from Sponsorship s where s.project.id = :id")
	Collection<Sponsorship> findAllSponsorshipsByProjectId(int id);
}
