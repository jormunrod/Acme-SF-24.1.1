/**
 * Create Service for the Claim entity.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-21
 */

package acme.features.authenticated.claim;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.claims.Claim;

@Service
public class AuthenticatedClaimCreateService extends AbstractService<Authenticated, Claim> {

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

		object = new Claim();

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Claim object) {
		assert object != null;
		Date currentMoment;

		currentMoment = MomentHelper.getCurrentMoment();

		super.bind(object, "code", "instantiationMoment", "heading", "description", "department", "email", "link");
		object.setInstantiationMoment(currentMoment);

	}

	@Override
	public void validate(final Claim object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Claim existing;

			existing = this.repository.findOneClaimByCode(object.getCode());
			super.state(existing == null, "code", "authenticated.claim.form.error.duplicated");
		}
	}

	@Override
	public void perform(final Claim object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Claim object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "code", "instantiationMoment", "heading", "description", "department", "email", "link");

		super.getResponse().addData(dataset);
	}

}
