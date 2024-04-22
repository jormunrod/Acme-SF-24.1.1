
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

	Money						averageAmountOfTheSponsorships;
	Money						deviationAmountOfTheSponsorships;
	Money						minimumAmountOfTheSponsorships;
	Money						maximumAmountOfTheSponsorships;

	Money						averageQuantityOfTheInvoices;
	Money						deviationQuantityOfTheInvoices;
	Money						minimumQuantityOfTheInvoices;
	Money						maximumQuantityOfTheInvoices;

}
