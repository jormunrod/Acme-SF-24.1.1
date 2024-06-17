
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

		Double averageNumberOfUserStoryEstimatedHours;
		Double deviationNumberOfUserStoryEstimatedHours;
		Integer minimumNumberOfUserStoryEstimatedHours;
		Integer maximumNumberOfUserStoryEstimatedHours;

		Double averageNumberOfProjectCost;
		Double deviationNumberOfProjectCost;
		Integer minimumNumberOfProjectCost;
		Integer maximumNumberOfProjectCost;

		totalNumberOfMUSTUserStories = this.repository.totalNumberOfMUSTUserStories(id);
		totalNumberOfSHOULDUserStories = this.repository.totalNumberOfSHOULDUserStories(id);
		totalNumberOfCOULDUserStories = this.repository.totalNumberOfCOULDUserStories(id);
		totalNumberOfWONTUserStories = this.repository.totalNumberOfWONTUserStories(id);

		averageNumberOfUserStoryEstimatedHours = this.repository.averageNumberOfUserStoryEstimatedHours(id);
		deviationNumberOfUserStoryEstimatedHours = this.repository.deviationNumberOfUserStoryEstimatedHours(id);
		minimumNumberOfUserStoryEstimatedHours = this.repository.minimumNumberOfUserStoryEstimatedHours(id);
		maximumNumberOfUserStoryEstimatedHours = this.repository.maximumNumberOfUserStoryEstimatedHours(id);

		averageNumberOfProjectCost = this.repository.averageNumberOfProjectCost(id);
		deviationNumberOfProjectCost = this.repository.deviationNumberOfProjectCost(id);
		minimumNumberOfProjectCost = this.repository.minimumNumberOfProjectCost(id);
		maximumNumberOfProjectCost = this.repository.maximumNumberOfProjectCost(id);

		dashboard = new ManagerDashboard();
		dashboard.setTotalNumberOfMUSTUserStories(totalNumberOfMUSTUserStories);
		dashboard.setTotalNumberOfSHOULDUserStories(totalNumberOfSHOULDUserStories);
		dashboard.setTotalNumberOfCOULDUserStories(totalNumberOfCOULDUserStories);
		dashboard.setTotalNumberOfWONTUserStories(totalNumberOfWONTUserStories);

		dashboard.setAverageNumberOfUserStoryEstimatedHours(averageNumberOfUserStoryEstimatedHours);
		dashboard.setDeviationNumberOfUserStoryEstimatedHours(deviationNumberOfUserStoryEstimatedHours);
		dashboard.setMinimumNumberOfUserStoryEstimatedHours(minimumNumberOfUserStoryEstimatedHours);
		dashboard.setMaximumNumberOfUserStoryEstimatedHours(maximumNumberOfUserStoryEstimatedHours);

		dashboard.setAverageNumberOfProjectCost(averageNumberOfProjectCost);
		dashboard.setDeviationNumberOfProjectCost(deviationNumberOfProjectCost);
		dashboard.setMinimumNumberOfProjectCost(minimumNumberOfProjectCost);
		dashboard.setMaximumNumberOfProjectCost(maximumNumberOfProjectCost);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ManagerDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfMUSTUserStories", "totalNumberOfSHOULDUserStories", //
			"totalNumberOfCOULDUserStories", "totalNumberOfWONTUserStories", "averageNumberOfUserStoryEstimatedHours", //
			"deviationNumberOfUserStoryEstimatedHours", "minimumNumberOfUserStoryEstimatedHours", //
			"maximumNumberOfUserStoryEstimatedHours", "averageNumberOfProjectCost", "deviationNumberOfProjectCost", //
			"minimumNumberOfProjectCost", "maximumNumberOfProjectCost");

		super.getResponse().addData(dataset);
	}
}
