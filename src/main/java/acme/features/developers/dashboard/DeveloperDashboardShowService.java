
package acme.features.developers.dashboard;

import java.util.Collection;

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

	public Double calculateStandardDeviation() {
		Double mean = this.repository.average();
		Collection<Integer> times = this.repository.findAllTrainingModuleTimes();
		double variance = 0.0;

		for (Integer time : times)
			variance += Math.pow(time - mean, 2);

		variance = variance / times.size();
		return Math.sqrt(variance);
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

		totalNumberOfTrainingModuleWithAnUpdateMoment = this.repository.totalNumberOfTrainingModuleWithAnUpdateMoment();
		totalNumberOfTrainingSessionsWithALink = this.repository.totalNumberOfTrainingSessionsWithALink();
		average = this.repository.average();
		deviation = this.calculateStandardDeviation();
		minimumTimeOfTheTrainingModules = this.repository.minimumTimeOfTheTrainingModules();
		maximumTimeOfTheTrainingModules = this.repository.maximumTimeOfTheTrainingModules();

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
