
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
		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();

		Integer totalNumberOfMUSTUserStories;
		Integer totalNumberOfSHOULDUserStories;
		Integer totalNumberOfCOULDUserStories;
		Integer totalNumberOfWONTUserStories;

		Double averageNumberOfEstimatedHours;
		Double deviationNumberOfEstimatedHours;
		Integer minimumNumberOfEstimatedHours;
		Integer maximumNumberOfEstimatedHours;

		Double averageNumberOfCost;
		Double deviationNumberOfCost;
		Integer minimumNumberOfCost;
		Integer maximumNumberOfCost;

		totalNumberOfMUSTUserStories = this.repository.totalNumberOfMUSTUserStories(id);
		totalNumberOfSHOULDUserStories = this.repository.totalNumberOfSHOULDUserStories(id);
		totalNumberOfCOULDUserStories = this.repository.totalNumberOfCOULDUserStories(id);
		totalNumberOfWONTUserStories = this.repository.totalNumberOfWONTUserStories(id);

		averageNumberOfEstimatedHours = this.repository.averageNumberOfEstimatedHours(id);
		deviationNumberOfEstimatedHours = this.repository.deviationNumberOfEstimatedHours(id);
		minimumNumberOfEstimatedHours = this.repository.minimumNumberOfEstimatedHours(id);
		maximumNumberOfEstimatedHours = this.repository.maximumNumberOfEstimatedHours(id);

		averageNumberOfCost = this.repository.averageNumberOfCost(id);
		deviationNumberOfCost = this.repository.deviationNumberOfCost(id);
		minimumNumberOfCost = this.repository.minimumNumberOfCost(id);
		maximumNumberOfCost = this.repository.maximumNumberOfCost(id);

		dashboard = new ManagerDashboard();
		dashboard.setTotalNumberOfMUSTUserStories(totalNumberOfMUSTUserStories);
		dashboard.setTotalNumberOfSHOULDUserStories(totalNumberOfSHOULDUserStories);
		dashboard.setTotalNumberOfCOULDUserStories(totalNumberOfCOULDUserStories);
		dashboard.setTotalNumberOfWONTUserStories(totalNumberOfWONTUserStories);

		dashboard.setAverageNumberOfEstimatedHours(averageNumberOfEstimatedHours);
		dashboard.setDeviationNumberOfEstimatedHours(deviationNumberOfEstimatedHours);
		dashboard.setMinimumNumberOfEstimatedHours(minimumNumberOfEstimatedHours);
		dashboard.setMaximumNumberOfEstimatedHours(maximumNumberOfEstimatedHours);

		dashboard.setAverageNumberOfCost(averageNumberOfCost);
		dashboard.setDeviationNumberOfCost(deviationNumberOfCost);
		dashboard.setMinimumNumberOfCost(minimumNumberOfCost);
		dashboard.setMaximumNumberOfCost(maximumNumberOfCost);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ManagerDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfMUSTUserStories", "totalNumberOfSHOULDUserStories", //
			"totalNumberOfCOULDUserStories", "totalNumberOfWONTUserStories", "averageNumberOfEstimatedHours", //
			"deviationNumberOfEstimatedHours", "minimumNumberOfEstimatedHours", //
			"maximumNumberOfEstimatedHours", "averageNumberOfCost", "deviationNumberOfCost", //
			"minimumNumberOfCost", "maximumNumberOfCost");

		super.getResponse().addData(dataset);
	}
}
