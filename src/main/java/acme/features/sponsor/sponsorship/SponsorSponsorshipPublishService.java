
package acme.features.sponsor.sponsorship;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.sponsorships.SponsorshipType;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipPublishService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int sponsorshipId;
		Sponsorship sponsorship;
		Sponsor sponsor;
		boolean estadoProyecto = true;

		if (super.getRequest().hasData("project") && super.getRequest().getData("project", int.class) > 0) {
			Project p = this.repository.findOneProjectById(super.getRequest().getData("project", int.class));
			if (p != null)
				estadoProyecto = p.isPublished();
			else
				estadoProyecto = false;

		}

		sponsorshipId = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(sponsorshipId);
		sponsor = sponsorship == null ? null : sponsorship.getSponsor();
		status = sponsorship != null && super.getRequest().getPrincipal().hasRole(sponsor) && estadoProyecto && sponsorship.isDraftMode();
		super.getResponse().setAuthorised(status);

	}

	@Override
	public void load() {
		Sponsorship object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSponsorshipById(id);

		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final Sponsorship object) {
		assert object != null;

		int projectId;
		Project project;
		Sponsorship sponsorship;
		int id;
		id = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);

		projectId = super.getRequest().getData("project", int.class);

		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "sponsorshipType", "startDate", "endDate", "contactEmail", "amount", "link");
		object.setMoment(sponsorship.getMoment());
		object.setProject(project);
	}

	@Override
	public void validate(final Sponsorship object) {
		assert object != null;
		LocalDateTime localDateTime = LocalDateTime.of(2201, 01, 01, 00, 00);
		Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
		Date limitEndDate = Date.from(instant);
		Date limitStartDate = MomentHelper.deltaFromMoment(limitEndDate, -31, ChronoUnit.DAYS);

		if (!super.getBuffer().getErrors().hasErrors("code")) {
			Sponsorship alredyExisting;
			Sponsorship actual;
			boolean status = true;
			int id;
			id = super.getRequest().getData("id", int.class);
			alredyExisting = this.repository.findSponsorshipByCode(object.getCode());
			actual = this.repository.findOneSponsorshipById(id);
			if (alredyExisting != null)
				status = alredyExisting.getCode().equals(actual.getCode());
			super.state(status, "code", "sponsor.sponsorship.error.duplicated");
		}
		if (!super.getBuffer().getErrors().hasErrors("startDate")) {
			Date startDate = object.getStartDate();

			if (!startDate.after(MomentHelper.getBaseMoment()))
				super.state(false, "startDate", "sponsor.sponsorship.error.startDateMustBeInFuture");
			if (!startDate.before(limitStartDate))
				super.state(false, "startDate", "sponsor.sponsorship.error.startdateLimitPassed");

		}

		if (!super.getBuffer().getErrors().hasErrors("endDate")) {
			Date startDate = object.getStartDate();
			Date endDate = object.getEndDate();
			if (startDate != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(startDate);
				cal.add(Calendar.MONTH, 1);
				Date oneMonthAfterStartDate = cal.getTime();
				super.state(endDate.compareTo(oneMonthAfterStartDate) >= 0, "endDate", "sponsor.sponsorship.error.endDateNotOneMonthAfter");

			}
			super.state(endDate.before(limitEndDate), "endDate", "sponsor.sponsorship.error.EndDateLimitPassed");

		}

		if (!super.getBuffer().getErrors().hasErrors("amount")) {
			int id;
			Sponsorship sponsorship;
			id = super.getRequest().getData("id", int.class);
			sponsorship = this.repository.findOneSponsorshipById(id);
			String currency = object.getAmount().getCurrency();
			Double amount = object.getAmount().getAmount();
			boolean status = this.repository.invoicesNotPublishedBySponsorshipId(object.getId()).isEmpty();
			double invoicesAmount = this.repository.sumTotalAmountBySponsorshipId(object.getId()) == null ? 0. : this.repository.sumTotalAmountBySponsorshipId(object.getId());
			double diference = amount - invoicesAmount;

			if (this.repository.findInvoicesBySponsorshipId(id).isEmpty())
				super.state(false, "*", "sponsor.sponsorship.error.ThereAreNoInvoicesAsociated");
			else if (!status)
				super.state(status, "*", "sponsor.sponsorship.error.NotAllInvoicesPublished");
			else {
				if (!currency.equals(sponsorship.getAmount().getCurrency()))
					super.state(false, "amount", "sponsor.sponsorship.error.youcantChangeTheCurrency");
				if (diference > 0.)
					super.state(false, "*", "sponsor.sponsorship.error.invoicesLeft");
				if (!amount.equals(sponsorship.getAmount().getAmount()) && diference < 0)
					super.state(false, "amount", "sponsor.sponsorship.error.youcantChangeAmount");

			}

			if (!currency.equals("EUR") && !currency.equals("GBP") && !currency.equals("USD"))
				super.state(false, "amount", "sponsor.sponsorship.error.theCurrencyMustBeAdmitedByTheSistem");
			if (amount <= 0)
				super.state(false, "amount", "sponsor.sponsorship.error.amountNotPositive");
			if (amount > 1000000)
				super.state(false, "amount", "sponsor.sponsorship.error.AmountMustBeUnder1000000");

		}

	}
	@Override
	public void perform(final Sponsorship object) {
		assert object != null;
		Sponsorship sponsorship;
		sponsorship = object;
		sponsorship.setDraftMode(false);
		this.repository.save(sponsorship);

	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;
		Collection<Project> projects;
		SelectChoices choices;
		Dataset dataset;
		SelectChoices typeChoices;
		Sponsorship sponsorship;
		int id;
		id = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(id);
		projects = this.repository.findAllPublishedProjects();

		choices = SelectChoices.from(projects, "title", object.getProject());
		typeChoices = SelectChoices.from(SponsorshipType.class, object.getSponsorshipType());
		dataset = super.unbind(object, "code", "sponsorshipType", "startDate", "endDate", "contactEmail", "amount", "link", "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("moment", sponsorship.getMoment());
		dataset.put("sponsorshipType", typeChoices.getSelected().getKey());
		dataset.put("sponsorshipTypes", typeChoices);

		super.getResponse().addData(dataset);

	}

}
