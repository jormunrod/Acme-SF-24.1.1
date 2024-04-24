
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfInvoicesWithATaxLessThanOrEqualTo21;
	Integer						totalNumberOfSponsorshipsWithALink;

	Money						averageAmountOfTheSponsorshipsEUR;
	Money						deviationAmountOfTheSponsorshipsEUR;
	Money						minimumAmountOfTheSponsorshipsEUR;
	Money						maximumAmountOfTheSponsorshipsEUR;

	Money						averageAmountOfTheSponsorshipsUSD;
	Money						deviationAmountOfTheSponsorshipsUSD;
	Money						minimumAmountOfTheSponsorshipsUSD;
	Money						maximumAmountOfTheSponsorshipsUSD;

	Money						averageAmountOfTheSponsorshipsGBP;
	Money						deviationAmountOfTheSponsorshipsGBP;
	Money						minimumAmountOfTheSponsorshipsGBP;
	Money						maximumAmountOfTheSponsorshipsGBP;

	Money						averageQuantityOfTheInvoicesEUR;
	Money						deviationQuantityOfTheInvoicesEUR;
	Money						minimumQuantityOfTheInvoicesEUR;
	Money						maximumQuantityOfTheInvoicesEUR;

	Money						averageQuantityOfTheInvoicesUSD;
	Money						deviationQuantityOfTheInvoicesUSD;
	Money						minimumQuantityOfTheInvoicesUSD;
	Money						maximumQuantityOfTheInvoicesUSD;

	Money						averageQuantityOfTheInvoicesGBP;
	Money						deviationQuantityOfTheInvoicesGBP;
	Money						minimumQuantityOfTheInvoicesGBP;
	Money						maximumQuantityOfTheInvoicesGBP;

}
