
package acme.forms;

import acme.client.data.AbstractForm;
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
	Integer						minimumNumberOfCost;
	Integer						maximumNumberOfCost;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
