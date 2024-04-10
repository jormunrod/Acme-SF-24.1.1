/**
 * Repository for the contract entity
 * 
 * @Author: jormunrod
 * @Date: 2024-04-08
 */

package acme.features.client;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Contract;
import acme.entities.projects.ProgressLog;
import acme.entities.projects.Project;
import acme.roles.Client;

@Repository
public interface ClientContractRepository extends AbstractRepository {

	@Query("select c from Contract c where c.client.id = :id")
	Collection<Contract> findAllContractByClientId(int id);

	@Query("select c from Contract c where c.id = :id")
	Contract findOneContractById(int id);

	@Query("select DISTINCT c.project from Contract c where c.client.id = :id")
	Collection<Project> findAllProjectByClientId(int id);

	@Query("select p from Project p where p.isPublished = 1")
	Collection<Project> findAllPublishedProjects();

	@Query("select pl from ProgressLog pl where pl.contract.id = :id")
	Collection<ProgressLog> findAllProgressLogsByContractId(int id);

	@Query("select c from Client c where c.id = :id")
	Client findOneClientById(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select c from Contract c where c.code = :code")
	Contract findOneContractByCode(String code);

}
