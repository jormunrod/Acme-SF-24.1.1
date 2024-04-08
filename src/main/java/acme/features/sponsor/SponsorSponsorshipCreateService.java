
package acme.features.sponsor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Project;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.sponsorships.SponsorshipType;
import acme.roles.Sponsor;

@Service
public class SponsorSponsorshipCreateService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	protected SponsorSponsorshipRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);

	}

	@Override
	public void load() {
		Sponsorship object;
		Sponsor sponsor;

		sponsor = this.repository.findOneSponsorById(super.getRequest().getPrincipal().getActiveRoleId());

		object = new Sponsorship();
		object.setDraftMode(true);

		object.setSponsor(sponsor);

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

		sponsorId = super.getRequest().getPrincipal().getActiveRoleId();
		projects = this.repository.findProjectsBySponsorId(sponsorId);

		choices = SelectChoices.from(projects, "title", object.getProject());
		typeChoices = SelectChoices.from(SponsorshipType.class, object.getSponsorshipType());
		dataset = super.unbind(object, "code", "sponsorshipType", "moment", "startDate", "endDate", "contactEmail", "amount", "link", "draftMode");
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);
		dataset.put("sponsorshipTypes", typeChoices);
		super.getResponse().addData(dataset);

	}
}
