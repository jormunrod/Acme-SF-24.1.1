/**
 * Delete Service for the Claim entity.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-21
 */

package acme.features.authenticated.claim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.claims.Claim;

@Service
public class AuthenticatedClaimDeleteService extends AbstractService<Authenticated, Claim> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedClaimRepository repository;

	// AbstractService interface -----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Claim object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneClaimById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Claim object) {
		assert object != null;

		super.bind(object, "code", "instantiationMoment", "heading", "description", "department", "email", "link", "isPublished");
	}

	@Override
	public void validate(final Claim object) {
		assert object != null;
	}

	@Override
	public void perform(final Claim object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final Claim object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "code", "instantiationMoment", "heading", "description", "department", "email", "link", "isPublished");

		super.getResponse().addData(dataset);
	}

}
