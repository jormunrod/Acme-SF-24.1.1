
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
		Date date;
		date = MomentHelper.getCurrentMoment();

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneBannerById(id);

		object.setUpdateMoment(date);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Banner object) {
		assert object != null;

		super.bind(object, "displayStart", "displayEnd", "picture", "slogan", "webDocument");
	}

	@Override
	public void validate(final Banner object) {
		assert object != null;

		Date displayStart = object.getDisplayStart();
		Date displayEnd = object.getDisplayEnd();
		LocalDateTime startDateTime;
		LocalDateTime endDateTime;
		LocalDateTime maxDateTime = LocalDateTime.of(2201, 01, 01, 00, 00);
		LocalDateTime minDateTime = LocalDateTime.of(1999, 12, 31, 23, 59);
		Date now = MomentHelper.getCurrentMoment();

		if (displayStart != null && displayEnd != null) {

			startDateTime = LocalDateTime.ofInstant(displayStart.toInstant(), ZoneId.systemDefault());
			endDateTime = LocalDateTime.ofInstant(displayEnd.toInstant(), ZoneId.systemDefault());

			boolean displayEndAfterDisplayStart;
			boolean isDisplayForAWeek;

			isDisplayForAWeek = Duration.between(startDateTime, endDateTime).toDays() > 7;
			displayEndAfterDisplayStart = displayEnd.after(displayStart);

			super.state(displayEndAfterDisplayStart, "displayEnd", "administrator.banner.form.error.displayEnd");
			super.state(isDisplayForAWeek, "*", "administrator.banner.form.error.isDisplayForAWeek");
		}

		if (!super.getBuffer().getErrors().hasErrors("displayStart")) {
			boolean startDateIsMaxDateTime;
			boolean startDateIsMinDateTime;
			boolean displayStartAfterUpdate;

			startDateTime = LocalDateTime.ofInstant(displayStart.toInstant(), ZoneId.systemDefault());
			startDateIsMaxDateTime = startDateTime.isBefore(maxDateTime);
			startDateIsMinDateTime = startDateTime.isAfter(minDateTime);
			displayStartAfterUpdate = now.before(displayStart) || now.equals(displayStart);

			super.state(startDateIsMaxDateTime, "displayStart", "administrator.banner.form.error.startDateIsMaxDateTime");
			super.state(startDateIsMinDateTime, "displayStart", "administrator.banner.form.error.startDateIsMinDateTime");
			super.state(displayStartAfterUpdate, "displayStart", "administrator.banner.form.error.displayStartAfterUpdate");
		}

		if (!super.getBuffer().getErrors().hasErrors("displayEnd")) {
			boolean endDateIsMaxDateTime;
			boolean endDateIsMinDateTime;

			endDateTime = LocalDateTime.ofInstant(displayEnd.toInstant(), ZoneId.systemDefault());
			endDateIsMaxDateTime = endDateTime.isBefore(maxDateTime);
			endDateIsMinDateTime = endDateTime.isAfter(minDateTime);

			super.state(endDateIsMaxDateTime, "displayEnd", "administrator.banner.form.error.endDateIsMaxDateTime");
			super.state(endDateIsMinDateTime, "displayEnd", "administrator.banner.form.error.endDateIsMinDateTime");
		}
	}

	@Override
	public void perform(final Banner object) {
		assert object != null;

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
