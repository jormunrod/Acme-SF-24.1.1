
package acme.features.administrator.dashboard;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Administrator;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.claims.Claim;
import acme.entities.risk.Risk;
import acme.forms.AdministratorDashboard;

@Service
public class AdministratorDashboardShowService extends AbstractService<Administrator, AdministratorDashboard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrator.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		int sponsorId;
		AdministratorDashboard dashboard;
		Integer totalNumberOfAuditorPrincipals;
		Integer totalNumberOfClientPrincipals;
		Integer totalNumberOfConsumerPrincipals;
		Integer totalNumberOfDeveloperPrincipals;
		Integer totalNumberOfManagerPrincipals;
		Integer totalNumberOfProviderPrincipals;
		Integer totalNumberOfSponsorPrincipals;

		Double ratioOfNoticesWithEmailAndLink;
		Double ratioOfCriticalObjectives;
		Double ratioOfNonCriticalObjectives;

		Double averageRiskValue;
		Double minimumRiskValue;
		Double maximumRiskValue;
		Double standardDeviationRiskValue;

		Double averageNumberOfClaimsInLastTenWeeks;
		Double minimumNumberOfClaimsInLastTenWeeks;
		Double maximumNumberOfClaimsInLastTenWeeks;
		Double standardDeviationNumberOfClaimsInLastTenWeeks;

		totalNumberOfAuditorPrincipals = this.repository.countAuditors() == null ? 0 : this.repository.countAuditors();
		totalNumberOfClientPrincipals = this.repository.countClients() == null ? 0 : this.repository.countClients();
		totalNumberOfConsumerPrincipals = this.repository.countConsumers() == null ? 0 : this.repository.countConsumers();
		totalNumberOfDeveloperPrincipals = this.repository.countDevelopers() == null ? 0 : this.repository.countDevelopers();
		totalNumberOfManagerPrincipals = this.repository.countManagers() == null ? 0 : this.repository.countManagers();
		totalNumberOfProviderPrincipals = this.repository.countProviders() == null ? 0 : this.repository.countProviders();
		totalNumberOfSponsorPrincipals = this.repository.countSponsors() == null ? 0 : this.repository.countSponsors();
		double ratio1 = this.repository.countNumberOfNoticesWithLinkAndEmail() == null || this.repository.countNotices() == null ? 0. : 1. * this.repository.countNumberOfNoticesWithLinkAndEmail() / this.repository.countNotices();
		ratioOfNoticesWithEmailAndLink = ratio1;
		double ratio2 = this.repository.countCriticalObjectives() == null || this.repository.countObjectives() == null ? 0. : 1. * this.repository.countCriticalObjectives() / this.repository.countObjectives();
		ratioOfCriticalObjectives = ratio2;
		double ratio3 = this.repository.countNonCriticalObjectives() == null || this.repository.countObjectives() == null ? 0. : 1. * this.repository.countNonCriticalObjectives() / this.repository.countObjectives();
		ratioOfNonCriticalObjectives = ratio3;
		Collection<Risk> riks = this.repository.allRisks();
		averageRiskValue = AdministratorDashboardShowService.calculateAverageRiskValue(riks);
		minimumRiskValue = AdministratorDashboardShowService.findMinimumRiskValue(riks);
		maximumRiskValue = AdministratorDashboardShowService.findMaximumRiskValue(riks);
		standardDeviationRiskValue = AdministratorDashboardShowService.calculateStandardDeviationRiskValue(riks);

		Collection<Claim> claims = this.repository.allClaims();
		averageNumberOfClaimsInLastTenWeeks = 1. * claims.size() / 10;

		dashboard = new AdministratorDashboard();

		dashboard.setTotalNumberOfAuditorPrincipals(totalNumberOfAuditorPrincipals);
		dashboard.setTotalNumberOfClientPrincipals(totalNumberOfClientPrincipals);
		dashboard.setTotalNumberOfConsumerPrincipals(totalNumberOfConsumerPrincipals);
		dashboard.setTotalNumberOfDeveloperPrincipals(totalNumberOfDeveloperPrincipals);
		dashboard.setTotalNumberOfManagerPrincipals(totalNumberOfManagerPrincipals);
		dashboard.setTotalNumberOfProviderPrincipals(totalNumberOfProviderPrincipals);
		dashboard.setTotalNumberOfSponsorPrincipals(totalNumberOfSponsorPrincipals);
		dashboard.setRatioOfNoticesWithEmailAndLink(ratioOfNoticesWithEmailAndLink);
		dashboard.setRatioOfCriticalObjectives(ratioOfCriticalObjectives);
		dashboard.setRatioOfNonCriticalObjectives(ratioOfNonCriticalObjectives);
		dashboard.setAverageRiskValue(averageRiskValue);
		dashboard.setMaximumRiskValue(maximumRiskValue);
		dashboard.setMinimumRiskValue(minimumRiskValue);
		dashboard.setStandardDeviationRiskValue(standardDeviationRiskValue);

		super.getBuffer().addData(dashboard);

	}
	private static double calculateAverageRiskValue(final Collection<Risk> risks) {
		if (risks.isEmpty())
			return 0.;
		double sum = 0;
		for (Risk risk : risks)
			sum += risk.value();
		return sum / risks.size();
	}
	private static double calculateStandardDeviationRiskValue(final Collection<Risk> risks) {
		if (risks.isEmpty())
			return 0.;
		double average = AdministratorDashboardShowService.calculateAverageRiskValue(risks);
		double sumOfSquares = 0.0;
		for (Risk risk : risks)
			sumOfSquares += Math.pow(risk.value() - average, 2);
		return Math.sqrt(sumOfSquares / risks.size());
	}
	private static double findMinimumRiskValue(final Collection<Risk> risks) {
		if (risks.isEmpty())
			return 0.;
		double min = Double.MAX_VALUE;
		for (Risk risk : risks)
			if (risk.value() < min)
				min = risk.value();
		return min;
	}
	private static double findMaximumRiskValue(final Collection<Risk> risks) {
		if (risks.isEmpty())
			return 0.;
		double max = Double.MIN_VALUE;
		for (Risk risk : risks)
			if (risk.value() > max)
				max = risk.value();
		return max;
	}
	public List<Claim> filterClaims(final List<Claim> claims) {

		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.WEEK_OF_YEAR, -10);
		Date tenWeeksAgo = calendar.getTime();
		return claims.stream().filter(claim -> claim.getInstantiationMoment().after(tenWeeksAgo) || claim.getInstantiationMoment().equals(tenWeeksAgo)).toList();
	}

	@Override
	public void unbind(final AdministratorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object,

			"totalNumberOfAuditorPrincipals", "totalNumberOfClientPrincipals", "totalNumberOfConsumerPrincipals", "totalNumberOfDeveloperPrincipals", "totalNumberOfManagerPrincipals", "totalNumberOfProviderPrincipals", "totalNumberOfSponsorPrincipals",
			"ratioOfNoticesWithEmailAndLink", "ratioOfCriticalObjectives", "ratioOfNonCriticalObjectives", "averageRiskValue", "minimumRiskValue", "maximumRiskValue", "standardDeviationRiskValue", "averageNumberOfClaimsInLastTenWeeks",
			"minimumNumberOfClaimsInLastTenWeeks", "maximumNumberOfClaimsInLastTenWeeks", "standardDeviationNumberOfClaimsInLastTenWeeks");
		super.getResponse().addData(dataset);
	}
}
