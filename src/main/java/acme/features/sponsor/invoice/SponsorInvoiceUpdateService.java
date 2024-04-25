
package acme.features.sponsor.invoice;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Invoice;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceUpdateService extends AbstractService<Sponsor, Invoice> {

	@Autowired
	private SponsorInvoiceRepository repository;


	@Override
	public void authorise() {

		Invoice object;
		int id;
		boolean status;
		Sponsor sponsor;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findInvoiceById(id);
		sponsor = object.getSponsorship().getSponsor();

		status = object.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsor) && super.getRequest().getPrincipal().getActiveRoleId() == sponsor.getId();

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
		int id;
		id = super.getRequest().getData("id", int.class);
		Invoice i = this.repository.findInvoiceById(id);
		super.bind(object, "code", "dueDate", "quantity", "tax", "link");
		object.setRegistrationTime(i.getRegistrationTime());
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Invoice alredyExisting;
			Invoice actual;
			boolean status = true;
			int id;
			id = super.getRequest().getData("id", int.class);
			alredyExisting = this.repository.findInvoiceByCode(object.getCode());
			actual = this.repository.findInvoiceById(id);
			if (alredyExisting != null)
				status = alredyExisting.getCode().equals(actual.getCode());
			super.state(status, "code", "sponsor.invoice.error.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			Double quantity = object.getQuantity().getAmount();
			super.state(quantity != null && quantity >= 0., "quantity", "sponsor.invoice.error.quantityNegative");
		}
		if (!super.getBuffer().getErrors().hasErrors("dueDate")) {
			Date registrationTime = object.getRegistrationTime();
			Date dueDate = object.getDueDate();

			Calendar cal = Calendar.getInstance();
			cal.setTime(registrationTime);
			cal.add(Calendar.MONTH, 1);

			Date oneMonthAfterRegistration = cal.getTime();

			super.state(dueDate.compareTo(oneMonthAfterRegistration) >= 0, "dueDate", "sponsor.invoice.error.dueDateTooEarly");

		}
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {

			Invoice invoice;
			int id;

			id = super.getRequest().getData("id", int.class);
			invoice = this.repository.findInvoiceById(id);

			String currency = object.getQuantity().getCurrency();

			super.state(currency != null && currency.equals(invoice.getSponsorship().getAmount().getCurrency()), "quantity", "sponsor.invoice.error.quantityMustBeEqualToSponsorship");
		}
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			String currency = object.getQuantity().getCurrency();
			if (!currency.equals("EUR") && !currency.equals("GBP") && !currency.equals("USD"))
				super.state(false, "amount", "sponsor.sponsorship.error.theCurrencyMustBeAdmitedByTheSistem");
		}

	}
	@Override
	public void perform(final Invoice object) {
		assert object != null;
		this.repository.save(object);

	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;
		int id;
		id = super.getRequest().getData("id", int.class);
		Invoice i = this.repository.findInvoiceById(id);
		Dataset dataset;
		dataset = super.unbind(object, "code", "dueDate", "quantity", "tax", "link", "draftMode");
		dataset.put("registrationTime", i.getRegistrationTime());
		super.getResponse().addData(dataset);

	}

}
