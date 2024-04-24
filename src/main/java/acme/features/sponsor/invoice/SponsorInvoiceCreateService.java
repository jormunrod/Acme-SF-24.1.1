
package acme.features.sponsor.invoice;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Invoice;
import acme.entities.sponsorships.Sponsorship;
import acme.roles.Sponsor;

@Service
public class SponsorInvoiceCreateService extends AbstractService<Sponsor, Invoice> {

	@Autowired
	protected SponsorInvoiceRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Sponsor sponsor;

		sponsor = this.repository.findOneSponsorById(super.getRequest().getPrincipal().getActiveRoleId());

		status = super.getRequest().getPrincipal().hasRole(sponsor);

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Invoice object;

		object = new Invoice();
		object.setDraftMode(true);

		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final Invoice object) {
		assert object != null;
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		super.bind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link");
		object.setSponsorship(sponsorship);
	}

	@Override
	public void validate(final Invoice object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Invoice alredyExisting;
			alredyExisting = this.repository.findInvoiceByCode(object.getCode());
			super.state(alredyExisting == null, "code", "sponsor.invoice.error.duplicated");
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
			int id;
			Sponsorship sponsorship;

			id = super.getRequest().getData("masterId", int.class);
			sponsorship = this.repository.findOneSponsorshipById(id);
			String currency = object.getQuantity().getCurrency();

			super.state(currency != null && currency.equals(sponsorship.getAmount().getCurrency()), "quantity", "sponsor.invoice.error.quantityMustBeEqualToSponsorship");
		}

	}
	@Override
	public void perform(final Invoice object) {
		assert object != null;
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);
		object.setSponsorship(sponsorship);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;
		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "draftMode");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));
		super.getResponse().addData(dataset);

	}
}
