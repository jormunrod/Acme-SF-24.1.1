
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

		int sponsorId;
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

		Double cuantity;

		sponsorId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfInvoicesWithATaxLessThanOrEqualTo21 = this.repository.countInvoicesBySponsorIdAndTaxLimit21(sponsorId);
		totalNumberOfSponsorshipsWithALink = this.repository.countSponsorshipsWithNonEmptyLinkBySponsorId(sponsorId);

		Double amount1 = this.repository.averageAmountOfSponsorshipsBySponsorId(sponsorId);
		Money m1 = new Money();
		m1.setCurrency("EUR");
		m1.setAmount(amount1);
		averageAmountOfTheSponsorships = m1;

		Double amount2 = this.repository.averageAmountOfSponsorshipsBySponsorId(sponsorId);
		Money m2 = new Money();
		m2.setCurrency("EUR");
		m2.setAmount(amount2);
		deviationAmountOfTheSponsorships = m2;

		Double amount3 = this.repository.findMinAmountBySponsorId(sponsorId);
		Money m3 = new Money();
		m3.setCurrency("EUR");
		m3.setAmount(amount3);
		minimumAmountOfTheSponsorships = m3;

		Double amount4 = this.repository.findMaxAmountBySponsorId(sponsorId);
		Money m4 = new Money();
		m4.setCurrency("EUR");
		m4.setAmount(amount4);
		maximumAmountOfTheSponsorships = m4;

		averageQuantityOfTheInvoices = m2;
		deviationQuantityOfTheInvoices = m2;
		minimumQuantityOfTheInvoices = m2;
		maximumQuantityOfTheInvoices = m2;

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
