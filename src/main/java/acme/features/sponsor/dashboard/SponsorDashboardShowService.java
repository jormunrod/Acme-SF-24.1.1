
package acme.features.sponsor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.SponsorDashboard;
import acme.roles.Sponsor;

@Service
public class SponsorDashboardShowService extends AbstractService<Sponsor, SponsorDashboard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private SponsorDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		SponsorDashboard dashboard;
		Integer totalNumberOfInvoicesWithATaxLessThanOrEqualTo21;
		Integer totalNumberOfSponsorshipsWithALink;

		Money averageAmountOfTheSponsorships;
		Money deviationAmountOfTheSponsorships;
		Money minimumAmountOfTheSponsorships;
		Money maximumAmountOfTheSponsorships;

		Money averageQuantityOfTheInvoices;
		Money deviationQuantityOfTheInvoices;
		Money minimumQuantityOfTheInvoices;
		Money maximumQuantityOfTheInvoices;

		int i = 1;
		Money m = new Money();
		m.setAmount(23.4);
		m.setCurrency("EUR");

		totalNumberOfInvoicesWithATaxLessThanOrEqualTo21 = i;
		totalNumberOfSponsorshipsWithALink = i;

		averageAmountOfTheSponsorships = m;
		deviationAmountOfTheSponsorships = m;
		minimumAmountOfTheSponsorships = m;
		maximumAmountOfTheSponsorships = m;

		averageQuantityOfTheInvoices = m;
		deviationQuantityOfTheInvoices = m;
		minimumQuantityOfTheInvoices = m;
		maximumQuantityOfTheInvoices = m;

		dashboard = new SponsorDashboard();
		dashboard.setAverageAmountOfTheSponsorships(averageAmountOfTheSponsorships);
		dashboard.setAverageQuantityOfTheInvoices(averageQuantityOfTheInvoices);
		dashboard.setDeviationAmountOfTheSponsorships(deviationAmountOfTheSponsorships);
		dashboard.setDeviationQuantityOfTheInvoices(deviationQuantityOfTheInvoices);
		dashboard.setMaximumAmountOfTheSponsorships(maximumAmountOfTheSponsorships);
		dashboard.setMaximumQuantityOfTheInvoices(maximumQuantityOfTheInvoices);
		dashboard.setMinimumAmountOfTheSponsorships(minimumAmountOfTheSponsorships);
		dashboard.setMinimumQuantityOfTheInvoices(minimumQuantityOfTheInvoices);
		dashboard.setTotalNumberOfInvoicesWithATaxLessThanOrEqualTo21(totalNumberOfInvoicesWithATaxLessThanOrEqualTo21);
		dashboard.setTotalNumberOfSponsorshipsWithALink(totalNumberOfSponsorshipsWithALink);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfInvoicesWithATaxLessThanOrEqualTo21", "totalNumberOfSponsorshipsWithALink", "averageAmountOfTheSponsorships", "deviationAmountOfTheSponsorships", "minimumAmountOfTheSponsorships",
			"maximumAmountOfTheSponsorships", "averageQuantityOfTheInvoices", "deviationQuantityOfTheInvoices", "minimumQuantityOfTheInvoices", "maximumQuantityOfTheInvoices");

		super.getResponse().addData(dataset);
	}
}
