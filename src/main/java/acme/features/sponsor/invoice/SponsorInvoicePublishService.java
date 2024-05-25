
package acme.features.sponsor.invoice;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class SponsorInvoicePublishService extends AbstractService<Sponsor, Invoice> {

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

		status = object.isDraftMode() && object.getSponsorship().isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsor) && super.getRequest().getPrincipal().getActiveRoleId() == sponsor.getId();

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
		LocalDateTime localDateTime = LocalDateTime.of(2201, 01, 01, 00, 00);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date limitDueDate = Date.from(instant);

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

		if (!super.getBuffer().getErrors().hasErrors("dueDate")) {
			Date registrationTime = object.getRegistrationTime();
			Date dueDate = object.getDueDate();

			Calendar cal = Calendar.getInstance();
			cal.setTime(registrationTime);
			cal.add(Calendar.MONTH, 1);

			Date oneMonthAfterRegistration = cal.getTime();

			super.state(dueDate.compareTo(oneMonthAfterRegistration) >= 0, "dueDate", "sponsor.invoice.error.dueDateTooEarly");
			if (!dueDate.after(MomentHelper.getBaseMoment()))
				super.state(false, "dueDate", "sponsor.invoice.error.DueDateMustBeInFuture");
			super.state(dueDate.before(limitDueDate), "dueDate", "sponsor.invoice.error.dueDateLimitPassed");
		}
		if (!super.getBuffer().getErrors().hasErrors("quantity")) {
			int id;
			Sponsorship sponsorship;
			Invoice invoice;
			Double quantity = object.getQuantity().getAmount();
			String currency = object.getQuantity().getCurrency();

			id = super.getRequest().getData("id", int.class);
			invoice = this.repository.findInvoiceById(id);
			sponsorship = invoice.getSponsorship();

			Double totalAmounOfinvoice = this.repository.sumTotalAmountBySponsorshipId(invoice.getSponsorship().getId()) == null ? 0. : this.repository.sumTotalAmountBySponsorshipId(invoice.getSponsorship().getId());
			totalAmounOfinvoice += object.getTotalAmountWithTax().getAmount();
			totalAmounOfinvoice -= invoice.getTotalAmountWithTax().getAmount();

			if (totalAmounOfinvoice > sponsorship.getAmount().getAmount())
				super.state(false, "*", "sponsor.invoice.error.theTotalAmountIsHigherThanTheSponsorshipAmount");
			if (!(quantity != null && quantity > 0.))
				super.state(false, "quantity", "sponsor.invoice.error.quantityNegativeOrZero");
			if (!currency.equals("EUR") && !currency.equals("GBP") && !currency.equals("USD"))
				super.state(false, "amount", "sponsor.invoice.error.theCurrencyMustBeAdmitedByTheSistem");
			if (!(currency != null && currency.equals(invoice.getSponsorship().getAmount().getCurrency())))
				super.state(false, "quantity", "sponsor.invoice.error.quantityMustBeEqualToSponsorship");
		}

	}
	@Override
	public void perform(final Invoice object) {
		assert object != null;
		object.setDraftMode(false);
		object.setTotalAmount(object.getTotalAmountWithTax());
		this.repository.save(object);

	}

	@Override
	public void unbind(final Invoice object) {
		assert object != null;
		int id;
		id = super.getRequest().getData("id", int.class);
		Invoice i = this.repository.findInvoiceById(id);
		Dataset dataset;
		dataset = super.unbind(object, "code", "registrationTime", "dueDate", "quantity", "tax", "link", "draftMode", "totalAmount");
		if (object.getQuantity() != null)
			dataset.put("totalAmount", object.getTotalAmountWithTax());
		dataset.put("registrationTime", i.getRegistrationTime());
		super.getResponse().addData(dataset);

	}

}
