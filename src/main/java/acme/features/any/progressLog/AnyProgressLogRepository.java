/**
 * Repository for the progress logs for any user.
 * 
 * @Author jormunrod
 * @Date 2024-04-22
 */

package acme.features.any.progressLog;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.contracts.ProgressLog;

@Repository
public interface AnyProgressLogRepository extends AbstractRepository {

	@Query("select pl from ProgressLog pl where pl.contract.id = :id")
	Collection<ProgressLog> findProgressLogByContractId(int id);

	@Query("select pl from ProgressLog pl where pl.id = :id")
	ProgressLog findProgressLogById(int id);

}
