
package acme.features.developers.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.DeveloperDashboard;
import acme.roles.Developer;

@Service
public class DeveloperDashboardShowService extends AbstractService<Developer, DeveloperDashboard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private DeveloperDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		DeveloperDashboard dashboard;
		Integer totalNumberOfTrainingModuleWithAnUpdateMoment;
		Integer totalNumberOfTrainingSessionsWithALink;
		Double average;
		Double deviation;
		Integer minimumTimeOfTheTrainingModules;
		Integer maximumTimeOfTheTrainingModules;
		int developerId;

		developerId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfTrainingModuleWithAnUpdateMoment = this.repository.totalNumberOfTrainingModuleWithAnUpdateMoment(developerId);
		totalNumberOfTrainingSessionsWithALink = this.repository.totalNumberOfTrainingSessionsWithALink(developerId);
		average = this.repository.average(developerId);
		deviation = this.repository.deviation(developerId);
		minimumTimeOfTheTrainingModules = this.repository.minimumTimeOfTheTrainingModules(developerId);
		maximumTimeOfTheTrainingModules = this.repository.maximumTimeOfTheTrainingModules(developerId);

		dashboard = new DeveloperDashboard();
		dashboard.setTotalNumberOfTrainingModuleWithAnUpdateMoment(totalNumberOfTrainingModuleWithAnUpdateMoment);
		dashboard.setTotalNumberOfTrainingSessionsWithALink(totalNumberOfTrainingSessionsWithALink);
		dashboard.setAverage(average);
		dashboard.setDeviation(deviation);
		dashboard.setMinimumTimeOfTheTrainingModules(minimumTimeOfTheTrainingModules);
		dashboard.setMaximumTimeOfTheTrainingModules(maximumTimeOfTheTrainingModules);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final DeveloperDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"totalNumberOfTrainingModuleWithAnUpdateMoment", //
			"totalNumberOfTrainingSessionsWithALink", //
			"average", "deviation", "minimumTimeOfTheTrainingModules", //
			"maximumTimeOfTheTrainingModules");

		super.getResponse().addData(dataset);
	}
}
