
package acme.features.any.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.AuditRecord;

@Service
public class AnyAuditRecordListService extends AbstractService<Any, AuditRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyAuditRecordRepository repository;

	// AbstractService interface -----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<AuditRecord> objects;
		int id;

		id = super.getRequest().getData("id", int.class);
		objects = this.repository.findAuditRecordByCodeAuditId(id);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "code", "auditPeriodStart", "auditPeriodEnd", "mark");

		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<AuditRecord> objects) {
		assert objects != null;
		int masterId;

		masterId = super.getRequest().getData("id", int.class);
		super.getResponse().addGlobal("masterId", masterId);
	}
}
