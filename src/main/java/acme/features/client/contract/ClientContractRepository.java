/**
 * Repository for the contract entity
 * 
 * @Author: jormunrod
 * @Date: 2024-04-08
 */

package acme.features.client.contract;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.entities.projects.Project;
import acme.roles.Client;

@Repository
public interface ClientContractRepository extends AbstractRepository {

	@Query("select c from Contract c where c.client.id = :id")
	Collection<Contract> findAllContractByClientId(int id);

	@Query("select c from Contract c where c.id = :id")
	Contract findOneContractById(int id);

	@Query("select p from Project p where p.isPublished = true")
	Collection<Project> findAllPublishedProjects();

	@Query("select pl from ProgressLog pl where pl.contract.id = :id")
	Collection<ProgressLog> findAllProgressLogsByContractId(int id);

	@Query("select c from Client c where c.id = :id")
	Client findOneClientById(int id);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select c from Contract c where c.code = :code")
	Contract findOneContractByCode(String code);

	@Query("select c from Contract c where c.code = :code and c.id != :id")
	Contract findOneContractByCodeAndDifferentId(String code, int id);

	@Query("select sum(c.budget.amount) from Contract c where c.project.id = :projectId")
	Integer totalCostOfContractsByProjectId(int projectId);

}
