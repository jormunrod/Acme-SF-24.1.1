
package acme.features.authenticated.risk;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Authenticated;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.risk.Risk;

@Service
public class AuthenticatedRiskListService extends AbstractService<Authenticated, Risk> {
	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected AuthenticatedRiskRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Authenticated.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<Risk> objects;

		objects = this.repository.findAllRisk();

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final Risk object) {
		assert object != null;

		Dataset dataset;
		dataset = super.unbind(object, "reference", "identificationDate", "impact");
		dataset.put("value", object.value());

		super.getResponse().addData(dataset);
	}
}
