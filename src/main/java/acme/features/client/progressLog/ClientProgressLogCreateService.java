/**
 * Create Service for the Contract entity.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-09
 */

package acme.features.client.progressLog;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.contracts.Contract;
import acme.entities.contracts.ProgressLog;
import acme.roles.Client;

@Service
public class ClientProgressLogCreateService extends AbstractService<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ClientProgressLogRepository repository;

	// AbstractService interface -----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract;

		id = super.getRequest().getData("masterId", int.class);
		contract = this.repository.findOneContractById(id);
		status = contract != null && super.getRequest().getPrincipal().hasRole(contract.getClient());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		ProgressLog object;

		object = new ProgressLog();
		object.setPublished(false);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProgressLog object) {
		assert object != null;
		int id;
		Contract contract;
		Date currentMoment;

		id = super.getRequest().getData("masterId", int.class);
		contract = this.repository.findOneContractById(id);

		currentMoment = MomentHelper.getCurrentMoment();

		super.bind(object, "recordId", "completenessPercentage", "progressComment", "registrationMoment", "responsiblePerson", "isPublished");
		object.setContract(contract);
		object.setRegistrationMoment(currentMoment);
	}

	@Override
	public void validate(final ProgressLog object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("recordId")) {
			ProgressLog existing;

			existing = this.repository.findOneProgressLogByRecordId(object.getRecordId());
			super.state(existing == null, "recordId", "client.progress-log.form.error.duplicated");
		}
	}

	@Override
	public void perform(final ProgressLog object) {
		assert object != null;
		int id;
		Contract contract;

		id = super.getRequest().getData("masterId", int.class);
		contract = this.repository.findOneContractById(id);
		object.setContract(contract);

		this.repository.save(object);

	}

	@Override
	public void unbind(final ProgressLog object) {
		assert object != null;
		Dataset dataset;

		dataset = super.unbind(object, "recordId", "completenessPercentage", "progressComment", "registrationMoment", "responsiblePerson", "isPublished");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));

		super.getResponse().addData(dataset);
	}

}
