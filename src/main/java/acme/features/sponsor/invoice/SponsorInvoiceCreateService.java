
package acme.features.sponsor.invoice;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
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
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		sponsor = this.repository.findOneSponsorById(super.getRequest().getPrincipal().getActiveRoleId());

		status = sponsorship.getSponsor().getId() == sponsor.getId() && sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsor);

		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Invoice object;
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		object = new Invoice();
		object.setDraftMode(true);
		object.setSponsorship(sponsorship);

		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final Invoice object) {
		assert object != null;
		int id;
		Sponsorship sponsorship;

		id = super.getRequest().getData("masterId", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		super.bind(object, "code", "dueDate", "quantity", "tax", "link", "totalAmount");
		object.setSponsorship(sponsorship);
		object.setRegistrationTime(MomentHelper.getBaseMoment());
		if (object.getQuantity() != null)
			object.setTotalAmount(object.getTotalAmountWithTax());

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
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			String currency = object.getQuantity().getCurrency();
			if (!currency.equals("EUR") && !currency.equals("GBP") && !currency.equals("USD"))
				super.state(false, "quantity", "sponsor.invoice.error.theCurrencyMustBeAdmitedByTheSistem");
		}
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			int id;
			Sponsorship sponsorship;
			id = super.getRequest().getData("masterId", int.class);
			sponsorship = this.repository.findOneSponsorshipById(id);
			Double totalAmounOfinvoice = this.repository.sumTotalAmountBySponsorshipId(id) == null ? 0. : this.repository.sumTotalAmountBySponsorshipId(id);
			totalAmounOfinvoice += object.getTotalAmountWithTax().getAmount();

			if (totalAmounOfinvoice > sponsorship.getAmount().getAmount())
				super.state(false, "quantity", "sponsor.invoice.error.theTotalAmountIsHigherThanTheSponsorshipAmount");
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
		object.setTotalAmount(object.getTotalAmountWithTax());
		object.setId(0);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;

		Dataset dataset;
		dataset = super.unbind(object, "code", "dueDate", "quantity", "tax", "link", "draftMode", "totalAmount");
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));
		dataset.put("registrationTime", MomentHelper.getBaseMoment());
		if (object.getQuantity() != null)
			dataset.put("totalAmount", object.getTotalAmountWithTax());
		super.getResponse().addData(dataset);

	}
}
