
package acme.features.administrator.banner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.banner.Banner;

@Service
public class AdministratorBannerUpdateService extends AbstractService<Administrator, Banner> {

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

		if (!super.getBuffer().getErrors().hasErrors("displayStart") && !super.getBuffer().getErrors().hasErrors("displayEnd")) {
			Date displayStart = object.getDisplayStart();
			Date displayEnd = object.getDisplayEnd();

			boolean displayStartAfterInstantiation;
			boolean displayStartAfterUpdate;
			boolean displayEndAfterDisplayStart;
			boolean isDisplayForAWeek;

			LocalDateTime startDateTime = LocalDateTime.ofInstant(displayStart.toInstant(), ZoneId.systemDefault());
			LocalDateTime endDateTime = LocalDateTime.ofInstant(displayEnd.toInstant(), ZoneId.systemDefault());
			isDisplayForAWeek = Duration.between(startDateTime, endDateTime).toDays() > 7;

			displayEndAfterDisplayStart = displayEnd.after(displayStart);
			displayStartAfterInstantiation = object.getInstantiationMoment().before(displayStart);
			displayStartAfterUpdate = object.getUpdateMoment().before(displayStart);

			super.state(displayEndAfterDisplayStart, "displayEnd", "administrator.banner.form.error.displayEnd");
			super.state(displayStartAfterInstantiation, "displayStart", "administrator.banner.form.error.displayStartAfterInstantiation");
			super.state(displayStartAfterUpdate, "displayStart", "administrator.banner.form.error.displayStartAfterUpdate");
			super.state(isDisplayForAWeek, "*", "administrator.banner.form.error.isDisplayForAWeek");
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;
		Date date;

		date = MomentHelper.getCurrentMoment();
		object.setUpdateMoment(date);

		this.repository.save(object);
	}

	@Override
	public void unbind(final Banner object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "instantiationMoment", "updateMoment", "displayStart", "displayEnd", "picture", "slogan", "webDocument");

		super.getResponse().addData(dataset);
	}
}
