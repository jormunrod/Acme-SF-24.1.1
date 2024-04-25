/**
 * List Service for the ProgressLog entity.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-20
 */

package acme.features.client.progressLog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogListService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ClientProgressLogRepository repository;

	// AbstractService interface -----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract;

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractById(id);
		status = contract != null && super.getRequest().getPrincipal().hasRole(contract.getClient());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<ProgressLog> objects;
		int id;

		id = super.getRequest().getData("id", int.class);
		objects = this.repository.findProgressLogByContractId(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "recordId", "completenessPercentage", "progressComment", "registrationMoment", "responsiblePerson");

		String published = object.isPublished() ? "✔️" : "❌";
		dataset.put("isPublished", published);

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<ProgressLog> objects) {
		assert objects != null;
		int masterId;

		masterId = super.getRequest().getData("id", int.class);
		super.getResponse().addGlobal("masterId", masterId);
	}

}
