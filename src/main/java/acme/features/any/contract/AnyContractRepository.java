/**
 * Repository for the contracts of any user.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-22
 */

package acme.features.any.contract;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;

@Repository
public interface AnyContractRepository extends AbstractRepository {

	@Query("select c from Contract c where c.isPublished = true")
	Collection<Contract> findAllContractsPublished();

	@Query("select c from Contract c where c.id = :id")
	Contract findContractById(int id);

	@Query("select p from Project p where p.isPublished = true")
	Collection<Project> findAllPublishedProjects();

}
