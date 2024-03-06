
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDashboard extends AbstractForm {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfMUSTUserStories;
	Integer						totalNumberOfSHOULDUserStories;
	Integer						totalNumberOfCOULDUserStories;
	Integer						totalNumberOfWONTUserStories;

	Double						averageNumberOfEstimatedHours;
	Double						deviationNumberOfEstimatedHours;
	Integer						minimumNumberOfEstimatedHours;
	Integer						maximumNumberOfEstimatedHours;

	Double						averageNumberOfCost;
	Double						deviationNumberOfCost;
	Money						minimumNumberOfCost;
	Money						maximumNumberOfCost;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
