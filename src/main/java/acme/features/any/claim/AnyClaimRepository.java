/**
 * Repository for the claim entity
 * 
 * @Author: jormunrod
 * @Date: 2024-04-21
 */

package acme.features.any.claim;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.claims.Claim;

@Repository
public interface AnyClaimRepository extends AbstractRepository {

	@Query("select c from Claim c")
	Collection<Claim> findAllClaims();

	@Query("select c from Claim c where c.id = :id")
	Claim findOneClaimById(int id);

	@Query("select c from Claim c where c.code = :code")
	Claim findOneClaimByCode(String code);

}
