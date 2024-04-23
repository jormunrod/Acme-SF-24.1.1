
package acme.features.any.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Any;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;

@Service
public class AnySponsorshipListService extends AbstractService<Any, Sponsorship> {

	//Internal state -----------------------------------------------------------------------------------

	@Autowired
	protected AnySponsorshipRepository repository;

	// AbstractService interface ---------------------------------------------------------------------


	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Collection<Sponsorship> objects;

		objects = this.repository.getAllSponsorshipPublished();

		super.getBuffer().addData(objects);

	}

	@Override
	public void unbind(final Sponsorship object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "moment", "startDate", "endDate", "contactEmail", "amount", "sponsorshipType", "link");

		super.getResponse().addData(dataset);
	}

}
