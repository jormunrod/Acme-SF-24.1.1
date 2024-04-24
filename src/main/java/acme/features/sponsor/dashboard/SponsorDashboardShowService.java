
package acme.features.sponsor.dashboard;

import java.util.ArrayList;
import java.util.List;

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
		boolean status;
		Sponsor sponsor;

		sponsor = this.repository.findOneSponsorById(super.getRequest().getPrincipal().getActiveRoleId());

		status = super.getRequest().getPrincipal().hasRole(sponsor);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {

		int sponsorId = super.getRequest().getPrincipal().getActiveRoleId();
		SponsorDashboard dashboard;
		Integer totalNumberOfInvoicesWithATaxLessThanOrEqualTo21;
		Integer totalNumberOfSponsorshipsWithALink;
		List<Money> averageAmountOfTheSponsorships = new ArrayList<>();
		List<Money> deviationAmountOfTheSponsorships = new ArrayList<>();
		List<Money> minimumAmountOfTheSponsorships = new ArrayList<>();
		List<Money> maximumAmountOfTheSponsorships = new ArrayList<>();

		List<Money> averageQuantityOfTheInvoices = new ArrayList<>();
		List<Money> deviationQuantityOfTheInvoices = new ArrayList<>();
		List<Money> minimumQuantityOfTheInvoices = new ArrayList<>();
		List<Money> maximumQuantityOfTheInvoices = new ArrayList<>();

		List<Object[]> sponsorhipAverages = new ArrayList<>(this.repository.averageAmountOfSponsorshipsBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> sponsorhipMinimmums = new ArrayList<>(this.repository.minimumAmountOfSponsorshipsBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> sponsorhipMaximums = new ArrayList<>(this.repository.maximunAmountOfSponsorshipsBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> sponsorhipDeviations = new ArrayList<>(this.repository.deviationAmountOfSponsorshipsBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> invoiceAverages = new ArrayList<>(this.repository.averageQuantityInvoicesBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> invoiceMinimmums = new ArrayList<>(this.repository.minimumQuantityInvoicesBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> invoiceMaximums = new ArrayList<>(this.repository.maximunQuantityInvoicesBySponsorIdGroupedByCurrency(sponsorId));
		List<Object[]> invoiceDeviations = new ArrayList<>(this.repository.deviationQuantityInvoicesBySponsorIdGroupedByCurrency(sponsorId));

		for (int i = 0; i < sponsorhipAverages.size(); i++) {
			Object[] result = sponsorhipAverages.get(i);
			String currencyA = " /" + result[0];
			Double averageA = (Double) result[1];
			Money a = new Money();
			a.setAmount(averageA);
			a.setCurrency(currencyA);
			averageAmountOfTheSponsorships.add(a);

			Object[] minimumResult = sponsorhipMinimmums.get(i);
			String currencyMin = " /" + minimumResult[0];
			Double minimum = (Double) minimumResult[1];
			Money minAmount = new Money();
			minAmount.setAmount(minimum);
			minAmount.setCurrency(currencyMin);
			minimumAmountOfTheSponsorships.add(minAmount);

			Object[] maximumResult = sponsorhipMaximums.get(i);
			String currencyMax = " /" + maximumResult[0];
			Double maximum = (Double) maximumResult[1];
			Money maxAmount = new Money();
			maxAmount.setAmount(maximum);
			maxAmount.setCurrency(currencyMax);
			maximumAmountOfTheSponsorships.add(maxAmount);

			Object[] deviationResults = sponsorhipDeviations.get(i);
			String currencyDev = " /" + deviationResults[0];
			Double amountDev = (Double) deviationResults[1];
			Money d = new Money();
			d.setAmount(amountDev);
			d.setCurrency(currencyDev);
			deviationAmountOfTheSponsorships.add(d);

			Object[] invoiceAverageResult = invoiceAverages.get(i);
			String currencyInvA = " /" + invoiceAverageResult[0];
			Double averageInvA = (Double) invoiceAverageResult[1];
			Money invoiceAvg = new Money();
			invoiceAvg.setAmount(averageInvA);
			invoiceAvg.setCurrency(currencyInvA);
			averageQuantityOfTheInvoices.add(invoiceAvg);

			Object[] invoiceMinimumResult = invoiceMinimmums.get(i);
			String currencyInvMin = " /" + invoiceMinimumResult[0];
			Double minimumInv = (Double) invoiceMinimumResult[1];
			Money invoiceMin = new Money();
			invoiceMin.setAmount(minimumInv);
			invoiceMin.setCurrency(currencyInvMin);
			minimumQuantityOfTheInvoices.add(invoiceMin);

			Object[] invoiceMaximumResult = invoiceMaximums.get(i);
			String currencyInvMax = " /" + invoiceMaximumResult[0];
			Double maximumInv = (Double) invoiceMaximumResult[1];
			Money invoiceMax = new Money();
			invoiceMax.setAmount(maximumInv);
			invoiceMax.setCurrency(currencyInvMax);
			maximumQuantityOfTheInvoices.add(invoiceMax);

			Object[] invoiceDeviationResult = invoiceDeviations.get(i);
			String currencyInvDev = " /" + invoiceDeviationResult[0];
			Double deviationInv = (Double) invoiceDeviationResult[1];
			Money invoiceDev = new Money();
			invoiceDev.setAmount(deviationInv);
			invoiceDev.setCurrency(currencyInvDev);
			deviationQuantityOfTheInvoices.add(invoiceDev);

		}

		sponsorId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfInvoicesWithATaxLessThanOrEqualTo21 = this.repository.countInvoicesBySponsorIdAndTaxLimit21(sponsorId);
		totalNumberOfSponsorshipsWithALink = this.repository.countSponsorshipsWithNonEmptyLinkBySponsorId(sponsorId);

		dashboard = new SponsorDashboard();
		dashboard.setTotalNumberOfInvoicesWithATaxLessThanOrEqualTo21(totalNumberOfInvoicesWithATaxLessThanOrEqualTo21);
		dashboard.setTotalNumberOfSponsorshipsWithALink(totalNumberOfSponsorshipsWithALink);
		dashboard.setAverageAmountOfTheSponsorships(averageAmountOfTheSponsorships);
		dashboard.setMaximumAmountOfTheSponsorships(maximumAmountOfTheSponsorships);
		dashboard.setMinimumAmountOfTheSponsorships(minimumAmountOfTheSponsorships);
		dashboard.setDeviationAmountOfTheSponsorships(deviationAmountOfTheSponsorships);
		dashboard.setAverageQuantityOfTheInvoices(averageQuantityOfTheInvoices);
		dashboard.setDeviationQuantityOfTheInvoices(deviationQuantityOfTheInvoices);
		dashboard.setMaximumQuantityOfTheInvoices(maximumQuantityOfTheInvoices);
		dashboard.setMinimumQuantityOfTheInvoices(minimumQuantityOfTheInvoices);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object,

			"totalNumberOfInvoicesWithATaxLessThanOrEqualTo21", "totalNumberOfSponsorshipsWithALink",

			"averageAmountOfTheSponsorships", "deviationAmountOfTheSponsorships", "minimumAmountOfTheSponsorships", "maximumAmountOfTheSponsorships",

			"averageQuantityOfTheInvoices", "deviationQuantityOfTheInvoices", "minimumQuantityOfTheInvoices", "maximumQuantityOfTheInvoices");

		super.getResponse().addData(dataset);
	}
}
