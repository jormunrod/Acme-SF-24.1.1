
package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Banner;

@Service
public class AdministratorBannerDeleteService extends AbstractService<Administrator, Banner> {

	@Autowired
	private AdministratorBannerRepository repository;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Banner object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneBannerById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "instantiationMoment", "updateMoment", "displayStart", "displayEnd", "picture", "slogan", "webDocument");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

		this.repository.delete(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "instantiationMoment", "updateMoment", "displayStart", "displayEnd", "picture", "slogan", "webDocument");

		super.getResponse().addData(dataset);
	}
}
