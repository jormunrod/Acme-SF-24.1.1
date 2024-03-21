
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfAuditorPrincipals;
	Integer						totalNumberOfClientPrincipals;
	Integer						totalNumberOfConsumerPrincipals;
	Integer						totalNumberOfDeveloperPrincipals;
	Integer						totalNumberOfManagerPrincipals;
	Integer						totalNumberOfProviderPrincipals;
	Integer						totalNumberOfSponsorPrincipals;

	Double						ratioOfNoticesWithEmailAndLink;
	Double						ratioOfCriticalObjectives;
	Double						ratioOfNonCriticalObjectives;

	Double						averageRiskValue;
	Double						minimumRiskValue;
	Double						maximumRiskValue;
	Double						standardDeviationRiskValue;

	Double						averageNumberOfClaimsInLastTenWeeks;
	Double						minimumNumberOfClaimsInLastTenWeeks;
	Double						maximumNumberOfClaimsInLastTenWeeks;
	Double						standardDeviationNumberOfClaimsInLastTenWeeks;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
