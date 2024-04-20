/**
 * Repository for the progress logs of the clients.
 * 
 * @Author jormunrod
 * @Date 2024-04-20
 */

package acme.features.client.progressLog;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.projects.Contract;
import acme.entities.projects.ProgressLog;

@Repository
public interface ClientProgressLogRepository extends AbstractRepository {

	@Query("select c from Contract c where c.id = :id")
	Contract findOneContractById(int id);

	@Query("select pl from ProgressLog pl where pl.contract.id = :id")
	Collection<ProgressLog> findProgressLogByContractId(int id);

	@Query("select pl.contract from ProgressLog pl where pl.id = :id")
	Contract findOneContractByProgressLogId(int id);

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findProgressLogById(int id);

	@Query("select pl from ProgressLog pl where pl.recordId = :recordId")
	ProgressLog findOneProgressLogByRecordId(String recordId);

}
