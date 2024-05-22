
package acme.features.sponsor.invoice;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceListService extends AbstractService<Sponsor, Invoice> {

	@Autowired
	private SponsorInvoiceRepository repository;


	@Override
	public void authorise() {

		boolean status;
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);
		status = sponsorship != null && super.getRequest().getPrincipal().hasRole(sponsorship.getSponsor());

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<Invoice> objects;

		int id;
		id = super.getRequest().getData("id", int.class);

		objects = this.repository.findInvoicesBySponsorshipId(id);

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;
		String draftModeIntl = object.isDraftMode() ? "✔️" : "❌";
		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "totalAmount");
		dataset.put("draftMode", draftModeIntl);
		super.getResponse().addData(dataset);
	}

	@Override
	public void unbind(final Collection<Invoice> objects) {
		assert objects != null;

		int masterId;
		Sponsorship sponsorship;
		boolean showCreate;
		masterId = super.getRequest().getData("id", int.class);

		sponsorship = this.repository.findOneSponsorshipById(masterId);
		showCreate = sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsorship.getSponsor());

		super.getResponse().addGlobal("masterId", masterId);

		super.getResponse().addGlobal("showCreate", showCreate);

	}

}
