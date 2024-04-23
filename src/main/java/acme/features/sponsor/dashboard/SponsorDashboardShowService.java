
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

		Money averageAmountOfTheSponsorshipsEUR;
		Money deviationAmountOfTheSponsorshipsEUR;
		Money minimumAmountOfTheSponsorshipsEUR;
		Money maximumAmountOfTheSponsorshipsEUR;

		Money averageAmountOfTheSponsorshipsUSD;
		Money deviationAmountOfTheSponsorshipsUSD;
		Money minimumAmountOfTheSponsorshipsUSD;
		Money maximumAmountOfTheSponsorshipsUSD;

		Money averageAmountOfTheSponsorshipsGBP;
		Money deviationAmountOfTheSponsorshipsGBP;
		Money minimumAmountOfTheSponsorshipsGBP;
		Money maximumAmountOfTheSponsorshipsGBP;

		Money averageQuantityOfTheInvoicesEUR;
		Money deviationQuantityOfTheInvoicesEUR;
		Money minimumQuantityOfTheInvoicesEUR;
		Money maximumQuantityOfTheInvoicesEUR;

		Money averageQuantityOfTheInvoicesUSD;
		Money deviationQuantityOfTheInvoicesUSD;
		Money minimumQuantityOfTheInvoicesUSD;
		Money maximumQuantityOfTheInvoicesUSD;

		Money averageQuantityOfTheInvoicesGBP;
		Money deviationQuantityOfTheInvoicesGBP;
		Money minimumQuantityOfTheInvoicesGBP;
		Money maximumQuantityOfTheInvoicesGBP;

		int i = 1;

		Double cuantity;

		sponsorId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfInvoicesWithATaxLessThanOrEqualTo21 = this.repository.countInvoicesBySponsorIdAndTaxLimit21(sponsorId);
		totalNumberOfSponsorshipsWithALink = this.repository.countSponsorshipsWithNonEmptyLinkBySponsorId(sponsorId);

		Double amount1 = this.repository.averageAmountOfSponsorshipsBySponsorIdEUR(sponsorId);
		amount1 = amount1 == null ? 0 : amount1;
		Money m1 = new Money();
		m1.setCurrency("EUR");
		m1.setAmount(amount1);
		averageAmountOfTheSponsorshipsEUR = m1;

		Double amount2 = this.repository.standardDeviationAmountBySponsorIdEUR(sponsorId);
		amount2 = amount2 == null ? 0 : amount2;
		Money m2 = new Money();
		m2.setCurrency("EUR");
		m2.setAmount(amount2);
		deviationAmountOfTheSponsorshipsEUR = m2;

		Double amount3 = this.repository.findMinAmountBySponsorIdEUR(sponsorId);
		amount3 = amount3 == null ? 0 : amount3;
		Money m3 = new Money();
		m3.setCurrency("EUR");
		m3.setAmount(amount3);
		minimumAmountOfTheSponsorshipsEUR = m3;

		Double amount4 = this.repository.findMaxAmountBySponsorIdEUR(sponsorId);
		amount4 = amount4 == null ? 0 : amount4;
		Money m4 = new Money();
		m4.setCurrency("EUR");
		m4.setAmount(amount4);
		maximumAmountOfTheSponsorshipsEUR = m4;
		//usd
		Double amount5 = this.repository.averageAmountOfSponsorshipsBySponsorIdUSD(sponsorId);
		amount5 = amount5 == null ? 0 : amount5;
		Money m5 = new Money();
		m5.setCurrency("USD");
		m5.setAmount(amount5);
		averageAmountOfTheSponsorshipsUSD = m5;

		Double amount6 = this.repository.standardDeviationAmountBySponsorIdUSD(sponsorId);
		amount6 = amount6 == null ? 0 : amount6;
		Money m6 = new Money();
		m6.setCurrency("USD");
		m6.setAmount(amount6);
		deviationAmountOfTheSponsorshipsUSD = m6;

		Double amount7 = this.repository.findMinAmountBySponsorIdUSD(sponsorId);
		amount7 = amount7 == null ? 0 : amount7;
		Money m7 = new Money();
		m7.setCurrency("USD");
		m7.setAmount(amount7);
		minimumAmountOfTheSponsorshipsUSD = m7;

		Double amount8 = this.repository.findMaxAmountBySponsorIdUSD(sponsorId);
		amount8 = amount8 == null ? 0 : amount8;
		Money m8 = new Money();
		m8.setCurrency("USD");
		m8.setAmount(amount8);
		maximumAmountOfTheSponsorshipsUSD = m8;
		Double amount9 = this.repository.averageAmountOfSponsorshipsBySponsorIdGBP(sponsorId);
		amount9 = amount9 == null ? 0 : amount9;
		Money m9 = new Money();
		m9.setCurrency("GBP");
		m9.setAmount(amount9);
		averageAmountOfTheSponsorshipsGBP = m9;

		Double amount10 = this.repository.standardDeviationAmountBySponsorIdGBP(sponsorId);
		amount10 = amount10 == null ? 0 : amount10;
		Money m10 = new Money();
		m10.setCurrency("GBP");
		m10.setAmount(amount10);
		deviationAmountOfTheSponsorshipsGBP = m10;

		Double amount11 = this.repository.findMinAmountBySponsorIdGBP(sponsorId);
		amount11 = amount11 == null ? 0 : amount11;
		Money m11 = new Money();
		m11.setCurrency("GBP");
		m11.setAmount(amount11);
		minimumAmountOfTheSponsorshipsGBP = m11;

		Double amount12 = this.repository.findMaxAmountBySponsorIdGBP(sponsorId);
		amount12 = amount12 == null ? 0 : amount12;
		Money m12 = new Money();
		m12.setCurrency("GBP");
		m12.setAmount(amount12);
		maximumAmountOfTheSponsorshipsGBP = m12;

		// EUR Invoices
		Double invoiceAmount1 = this.repository.averageQuantityOfInvoicesBySponsorIdEUR(sponsorId);
		invoiceAmount1 = invoiceAmount1 == null ? 0 : invoiceAmount1;
		Money invoiceM1 = new Money();
		invoiceM1.setCurrency("EUR");
		invoiceM1.setAmount(invoiceAmount1);
		averageQuantityOfTheInvoicesEUR = invoiceM1;

		Double invoiceAmount2 = this.repository.standardDeviationQuantityOfInvoicesBySponsorIdEUR(sponsorId);
		invoiceAmount2 = invoiceAmount2 == null ? 0 : invoiceAmount2;
		Money invoiceM2 = new Money();
		invoiceM2.setCurrency("EUR");
		invoiceM2.setAmount(invoiceAmount2);
		deviationQuantityOfTheInvoicesEUR = invoiceM2;

		Double invoiceAmount3 = this.repository.minimumQuantityOfInvoicesBySponsorIdEUR(sponsorId);
		invoiceAmount3 = invoiceAmount3 == null ? 0 : invoiceAmount3;
		Money invoiceM3 = new Money();
		invoiceM3.setCurrency("EUR");
		invoiceM3.setAmount(invoiceAmount3);
		minimumQuantityOfTheInvoicesEUR = invoiceM3;

		Double invoiceAmount4 = this.repository.maximumQuantityOfInvoicesBySponsorIdEUR(sponsorId);
		invoiceAmount4 = invoiceAmount4 == null ? 0 : invoiceAmount4;
		Money invoiceM4 = new Money();
		invoiceM4.setCurrency("EUR");
		invoiceM4.setAmount(invoiceAmount4);
		maximumQuantityOfTheInvoicesEUR = invoiceM4;

		// USD Invoices
		Double invoiceAmount5 = this.repository.averageQuantityOfInvoicesBySponsorIdUSD(sponsorId);
		invoiceAmount5 = invoiceAmount5 == null ? 0 : invoiceAmount5;
		Money invoiceM5 = new Money();
		invoiceM5.setCurrency("USD");
		invoiceM5.setAmount(invoiceAmount5);
		averageQuantityOfTheInvoicesUSD = invoiceM5;

		Double invoiceAmount6 = this.repository.standardDeviationQuantityOfInvoicesBySponsorIdUSD(sponsorId);
		invoiceAmount6 = invoiceAmount6 == null ? 0 : invoiceAmount6;
		Money invoiceM6 = new Money();
		invoiceM6.setCurrency("USD");
		invoiceM6.setAmount(invoiceAmount6);
		deviationQuantityOfTheInvoicesUSD = invoiceM6;

		Double invoiceAmount7 = this.repository.minimumQuantityOfInvoicesBySponsorIdUSD(sponsorId);
		invoiceAmount7 = invoiceAmount7 == null ? 0 : invoiceAmount7;
		Money invoiceM7 = new Money();
		invoiceM7.setCurrency("USD");
		invoiceM7.setAmount(invoiceAmount7);
		minimumQuantityOfTheInvoicesUSD = invoiceM7;

		Double invoiceAmount8 = this.repository.maximumQuantityOfInvoicesBySponsorIdUSD(sponsorId);
		invoiceAmount8 = invoiceAmount8 == null ? 0 : invoiceAmount8;
		Money invoiceM8 = new Money();
		invoiceM8.setCurrency("USD");
		invoiceM8.setAmount(invoiceAmount8);
		maximumQuantityOfTheInvoicesUSD = invoiceM8;

		// GBP Invoices
		Double invoiceAmount9 = this.repository.averageQuantityOfInvoicesBySponsorIdGBP(sponsorId);
		invoiceAmount9 = invoiceAmount9 == null ? 0 : invoiceAmount9;
		Money invoiceM9 = new Money();
		invoiceM9.setCurrency("GBP");
		invoiceM9.setAmount(invoiceAmount9);
		averageQuantityOfTheInvoicesGBP = invoiceM9;

		Double invoiceAmount10 = this.repository.standardDeviationQuantityOfInvoicesBySponsorIdGBP(sponsorId);
		invoiceAmount10 = invoiceAmount10 == null ? 0 : invoiceAmount10;
		Money invoiceM10 = new Money();
		invoiceM10.setCurrency("GBP");
		invoiceM10.setAmount(invoiceAmount10);
		deviationQuantityOfTheInvoicesGBP = invoiceM10;

		Double invoiceAmount11 = this.repository.minimumQuantityOfInvoicesBySponsorIdGBP(sponsorId);
		invoiceAmount11 = invoiceAmount11 == null ? 0 : invoiceAmount11;
		Money invoiceM11 = new Money();
		invoiceM11.setCurrency("GBP");
		invoiceM11.setAmount(invoiceAmount11);
		minimumQuantityOfTheInvoicesGBP = invoiceM11;

		Double invoiceAmount12 = this.repository.maximumQuantityOfInvoicesBySponsorIdGBP(sponsorId);
		invoiceAmount12 = invoiceAmount12 == null ? 0 : invoiceAmount12;
		Money invoiceM12 = new Money();
		invoiceM12.setCurrency("GBP");
		invoiceM12.setAmount(invoiceAmount12);
		maximumQuantityOfTheInvoicesGBP = invoiceM12;
		// Create a new SponsorDashboard object
		dashboard = new SponsorDashboard();

		// Set attributes for EUR
		dashboard.setAverageAmountOfTheSponsorshipsEUR(averageAmountOfTheSponsorshipsEUR);
		dashboard.setDeviationAmountOfTheSponsorshipsEUR(deviationAmountOfTheSponsorshipsEUR);
		dashboard.setMinimumAmountOfTheSponsorshipsEUR(minimumAmountOfTheSponsorshipsEUR);
		dashboard.setMaximumAmountOfTheSponsorshipsEUR(maximumAmountOfTheSponsorshipsEUR);

		// Set attributes for USD Sponsorships
		dashboard.setAverageAmountOfTheSponsorshipsUSD(averageAmountOfTheSponsorshipsUSD);
		dashboard.setDeviationAmountOfTheSponsorshipsUSD(deviationAmountOfTheSponsorshipsUSD);
		dashboard.setMinimumAmountOfTheSponsorshipsUSD(minimumAmountOfTheSponsorshipsUSD);
		dashboard.setMaximumAmountOfTheSponsorshipsUSD(maximumAmountOfTheSponsorshipsUSD);

		// Set attributes for GBP Sponsorships
		dashboard.setAverageAmountOfTheSponsorshipsGBP(averageAmountOfTheSponsorshipsGBP);
		dashboard.setDeviationAmountOfTheSponsorshipsGBP(deviationAmountOfTheSponsorshipsGBP);
		dashboard.setMinimumAmountOfTheSponsorshipsGBP(minimumAmountOfTheSponsorshipsGBP);
		dashboard.setMaximumAmountOfTheSponsorshipsGBP(maximumAmountOfTheSponsorshipsGBP);

		// Set invoice attributes for EUR
		dashboard.setAverageQuantityOfTheInvoicesEUR(averageQuantityOfTheInvoicesEUR);
		dashboard.setDeviationQuantityOfTheInvoicesEUR(deviationQuantityOfTheInvoicesEUR);
		dashboard.setMinimumQuantityOfTheInvoicesEUR(minimumQuantityOfTheInvoicesEUR);
		dashboard.setMaximumQuantityOfTheInvoicesEUR(maximumQuantityOfTheInvoicesEUR);

		// Set invoice attributes for USD
		dashboard.setAverageQuantityOfTheInvoicesUSD(averageQuantityOfTheInvoicesUSD);
		dashboard.setDeviationQuantityOfTheInvoicesUSD(deviationQuantityOfTheInvoicesUSD);
		dashboard.setMinimumQuantityOfTheInvoicesUSD(minimumQuantityOfTheInvoicesUSD);
		dashboard.setMaximumQuantityOfTheInvoicesUSD(maximumQuantityOfTheInvoicesUSD);

		// Set invoice attributes for GBP
		dashboard.setAverageQuantityOfTheInvoicesGBP(averageQuantityOfTheInvoicesGBP);
		dashboard.setDeviationQuantityOfTheInvoicesGBP(deviationQuantityOfTheInvoicesGBP);
		dashboard.setMinimumQuantityOfTheInvoicesGBP(minimumQuantityOfTheInvoicesGBP);
		dashboard.setMaximumQuantityOfTheInvoicesGBP(maximumQuantityOfTheInvoicesGBP);

		// Set common attributes
		dashboard.setTotalNumberOfInvoicesWithATaxLessThanOrEqualTo21(totalNumberOfInvoicesWithATaxLessThanOrEqualTo21);
		dashboard.setTotalNumberOfSponsorshipsWithALink(totalNumberOfSponsorshipsWithALink);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final SponsorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object,

			"totalNumberOfInvoicesWithATaxLessThanOrEqualTo21", "totalNumberOfSponsorshipsWithALink",

			"averageAmountOfTheSponsorshipsEUR", "deviationAmountOfTheSponsorshipsEUR", "minimumAmountOfTheSponsorshipsEUR", "maximumAmountOfTheSponsorshipsEUR",

			"averageAmountOfTheSponsorshipsUSD", "deviationAmountOfTheSponsorshipsUSD", "minimumAmountOfTheSponsorshipsUSD", "maximumAmountOfTheSponsorshipsUSD",

			"averageAmountOfTheSponsorshipsGBP", "deviationAmountOfTheSponsorshipsGBP", "minimumAmountOfTheSponsorshipsGBP", "maximumAmountOfTheSponsorshipsGBP",

			"averageQuantityOfTheInvoicesEUR", "deviationQuantityOfTheInvoicesEUR", "minimumQuantityOfTheInvoicesEUR", "maximumQuantityOfTheInvoicesEUR",

			"averageQuantityOfTheInvoicesUSD", "deviationQuantityOfTheInvoicesUSD", "minimumQuantityOfTheInvoicesUSD", "maximumQuantityOfTheInvoicesUSD",

			"averageQuantityOfTheInvoicesGBP", "deviationQuantityOfTheInvoicesGBP", "minimumQuantityOfTheInvoicesGBP", "maximumQuantityOfTheInvoicesGBP");

		super.getResponse().addData(dataset);
	}
}
