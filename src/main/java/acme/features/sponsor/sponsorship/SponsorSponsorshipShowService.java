
package acme.features.sponsor.sponsorship;

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
public class SponsorSponsorshipShowService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	protected SponsorSponsorshipRepository repository;


	@Override
	public void authorise() {
		boolean status;
		Sponsorship object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneSponsorshipById(id);

		status = object != null && super.getRequest().getPrincipal().hasRole(object.getSponsor());

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
	public void unbind(final Sponsorship object) {
		assert object != null;
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
		dataset.put("sponsorshipTypes", typeChoices);
		super.getResponse().addData(dataset);
	}
}
