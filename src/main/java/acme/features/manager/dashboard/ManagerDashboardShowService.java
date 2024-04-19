
package acme.features.manager.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ManagerDashboard;
import acme.roles.Manager;

@Service
public class ManagerDashboardShowService extends AbstractService<Manager, ManagerDashboard> {

	@Autowired
	private ManagerDashboardRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		ManagerDashboard dashboard;
		Integer totalNumberOfMUSTUserStories;
		Integer totalNumberOfSHOULDUserStories;
		Integer totalNumberOfCOULDUserStories;
		Integer totalNumberOfWONTUserStories;

		Double averageNumberOfEstimatedHours;
		//Double deviationNumberOfEstimatedHours;
		Integer minimumNumberOfEstimatedHours;
		Integer maximumNumberOfEstimatedHours;

		Double averageNumberOfCost;
		//Double deviationNumberOfCost;
		Integer minimumNumberOfCost;
		Integer maximumNumberOfCost;

		totalNumberOfMUSTUserStories = this.repository.totalNumberOfMUSTUserStories();
		totalNumberOfSHOULDUserStories = this.repository.totalNumberOfSHOULDUserStories();
		totalNumberOfCOULDUserStories = this.repository.totalNumberOfCOULDUserStories();
		totalNumberOfWONTUserStories = this.repository.totalNumberOfWONTUserStories();

		averageNumberOfEstimatedHours = this.repository.averageNumberOfEstimatedHours();
		//deviationNumberOfEstimatedHours = this.repository.deviationNumberOfEstimatedHours();
		minimumNumberOfEstimatedHours = this.repository.minimumNumberOfEstimatedHours();
		maximumNumberOfEstimatedHours = this.repository.maximumNumberOfEstimatedHours();

		averageNumberOfCost = this.repository.averageNumberOfCost();
		//deviationNumberOfCost = this.repository.deviationNumberOfCost();
		minimumNumberOfCost = this.repository.minimumNumberOfCost();
		maximumNumberOfCost = this.repository.maximumNumberOfCost();

		dashboard = new ManagerDashboard();
		dashboard.setTotalNumberOfMUSTUserStories(totalNumberOfMUSTUserStories);
		dashboard.setTotalNumberOfSHOULDUserStories(totalNumberOfSHOULDUserStories);
		dashboard.setTotalNumberOfCOULDUserStories(totalNumberOfCOULDUserStories);
		dashboard.setTotalNumberOfWONTUserStories(totalNumberOfWONTUserStories);

		dashboard.setAverageNumberOfEstimatedHours(averageNumberOfEstimatedHours);
		//dashboard.setDeviationNumberOfEstimatedHours(deviationNumberOfEstimatedHours);
		dashboard.setMinimumNumberOfEstimatedHours(minimumNumberOfEstimatedHours);
		dashboard.setMaximumNumberOfEstimatedHours(maximumNumberOfEstimatedHours);

		dashboard.setAverageNumberOfCost(averageNumberOfCost);
		//dashboard.setDeviationNumberOfCost(deviationNumberOfCost);
		dashboard.setMinimumNumberOfCost(minimumNumberOfCost);
		dashboard.setMaximumNumberOfCost(maximumNumberOfCost);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ManagerDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfMUSTUserStories", "totalNumberOfSHOULDUserStories", //
			"totalNumberOfCOULDUserStories", "totalNumberOfWONTUserStories", "averageNumberOfEstimatedHours", //
			/* "deviationNumberOfEstimatedHours", */ "minimumNumberOfEstimatedHours", //
			"maximumNumberOfEstimatedHours", "averageNumberOfCost", /* "deviationNumberOfCost", */ //
			"minimumNumberOfCost", "maximumNumberOfCost");

		super.getResponse().addData(dataset);
	}
}
