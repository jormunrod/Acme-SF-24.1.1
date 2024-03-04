
package acme.forms;

import acme.client.data.AbstractForm;

public class SponsorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfInvoicesWithATaxLessThanOrEqualto21;
	Integer						totalNumberOfSponsorshipsWithALink;
	Double						averageAmountOfTheSponsorships;
	Double						deviationAmountOfTheSponsorships;
	Integer						minimumAmountOfTheSponsorships;
	Integer						maximumAmountOfTheSponsorships;
	Double						averageQuantityOfTheInvoices;
	Double						deviationQuantityOfTheInvoices;
	Integer						minimumQuantityOfTheInvoices;
	Integer						maximumQuantityOfTheInvoices;

}
