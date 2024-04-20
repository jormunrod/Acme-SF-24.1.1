
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDashboard extends AbstractForm {
	/*
	 * The system must handle client dashboards with the following data: total number of progress logs with a completeness rate below 25%, between 25% and 50%, between 50% and 75%, and above 75%; average, deviation, minimum, and maximum budget of the
	 * contracts.
	 */

	// Serialisation identifier -----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfProgressLogsBelow25;
	Integer						numberOfProgressLogsBetween25And50;
	Integer						numberOfProgressLogsBetween50And75;
	Integer						numberOfProgressLogsAbove75;

	Money						averageBudget;
	Money						deviationBudget;
	Money						minimumBudget;
	Money						maximumBudget;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
