/**
 * List Service for the ProgressLog entity for any user.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-22
 */

package acme.features.any.progressLog;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.contracts.ProgressLog;

@Service
public class AnyProgressLogListService extends AbstractService<Any, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyProgressLogRepository repository;

	// AbstractService interface -----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
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
		dataset = super.unbind(object, "recordId", "completenessPercentage", "progressComment", "registrationMoment", "responsiblePerson", "isPublished");

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
