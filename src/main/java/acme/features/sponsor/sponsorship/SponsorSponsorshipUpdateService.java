
package acme.features.sponsor.sponsorship;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.sponsorships.SponsorshipType;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipUpdateService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int sponsorshipId;
		Sponsorship sponsorship;
		Sponsor sponsor;
		sponsorshipId = super.getRequest().getData("id", int.class);
		sponsorship = this.repository.findOneSponsorshipById(sponsorshipId);
		sponsor = sponsorship == null ? null : sponsorship.getSponsor();
		status = sponsorship != null && sponsorship.isDraftMode() && super.getRequest().getPrincipal().hasRole(sponsor) && sponsor.getId() == super.getRequest().getPrincipal().getActiveRoleId();
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

		projectId = super.getRequest().getData("project", int.class);

		project = this.repository.findOneProjectById(projectId);

		super.bind(object, "code", "sponsorshipType", "moment", "startDate", "endDate", "contactEmail", "amount", "link");
		object.setProject(project);
	}

	@Override
	public void validate(final Sponsorship object) {
		assert object != null;

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
			Date moment = object.getMoment();
			Date startDate = object.getStartDate();

			super.state(startDate.after(moment), "startDate", "sponsor.sponsorship.error.startDateBeforeMoment");
		}

		if (!super.getBuffer().getErrors().hasErrors("endDate")) {
			Date startDate = object.getStartDate();
			Date endDate = object.getEndDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.MONTH, 1);
			Date oneMonthAfterStartDate = cal.getTime();
			super.state(endDate.compareTo(oneMonthAfterStartDate) >= 0, "endDate", "sponsor.sponsorship.error.endDateNotOneMonthAfter");
		}

		if (!super.getBuffer().getErrors().hasErrors("amount")) {
			Money amount = object.getAmount();
			super.state(amount.getAmount() > 0, "amount", "sponsor.sponsorship.error.amountNotPositive");
		}

	}
	@Override
	public void perform(final Sponsorship object) {
		assert object != null;

		this.repository.save(object);

	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;
		int sponsorId;
		Collection<Project> projects;
		SelectChoices choices;
		Dataset dataset;
		SelectChoices typeChoices;

		projects = this.repository.findAllPublishedProjects();

		choices = SelectChoices.from(projects, "title", object.getProject());
		typeChoices = SelectChoices.from(SponsorshipType.class, object.getSponsorshipType());
		dataset = super.unbind(object, "code", "sponsorshipType", "moment", "startDate", "endDate", "contactEmail", "amount", "link", "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("sponsorshipType", typeChoices.getSelected().getKey());
		dataset.put("sponsorshipTypes", typeChoices);

		super.getResponse().addData(dataset);

	}

}
