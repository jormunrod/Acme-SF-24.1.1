
package acme.features.sponsor.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Invoice;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceDeleteService extends AbstractService<Sponsor, Invoice> {

	@Autowired
	private SponsorInvoiceRepository repository;


	@Override
	public void authorise() {

		Invoice invoice;
		int id;
		boolean status;
		Sponsor sponsor;

		id = super.getRequest().getData("id", int.class);
		invoice = this.repository.findInvoiceById(id);

		status = invoice != null && super.getRequest().getPrincipal().hasRole(invoice.getSponsorship().getSponsor()) && invoice.isDraftMode();

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Invoice object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findInvoiceById(id);

		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final Invoice object) {
		assert object != null;
		super.bind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link");
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;

	}
	@Override
	public void perform(final Invoice object) {
		assert object != null;
		this.repository.delete(object);

	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;
		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "draftMode");

		super.getResponse().addData(dataset);

	}

}
