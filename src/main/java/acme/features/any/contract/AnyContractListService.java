/**
 * List Service for the contracts of any user.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-22
 */

package acme.features.any.contract;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;

@Service
public class AnyContractListService extends AbstractService<Any, Contract> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected AnyContractRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Contract> objects;

		objects = this.repository.findAllContractsPublished();

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "code", "instantiationMoment", "providerName", "customerName", "goals", "budget", "isPublished");

		super.getResponse().addData(dataset);
	}

}
