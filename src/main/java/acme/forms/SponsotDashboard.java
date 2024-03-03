
package acme.forms;

import acme.client.data.AbstractForm;

public class SponsotDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	int							totalNumberOfInvoicesWithATaxLessThanOrEqualto21;
	int							totalNumberOfSponsorshipsWithALink;
	Double						averageAmountOfTheSponsorships;
	Double						deviationAmountOfTheSponsorships;
	int							minimumAmountOfTheSponsorships;
	int							maximumAmountOfTheSponsorships;
	Double						averageQuantityOfTheInvoices;
	Double						deviationQuantityOfTheInvoices;
	int							minimumQuantityOfTheInvoices;
	int							maximumQuantityOfTheInvoices;

}
