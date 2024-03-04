
package acme.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditorDashboard {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfStaticCodeAudits;
	Integer						totalNumberOfDynamicCodeAudits;

	Double						averageNumberOfAuditRecord;
	Double						deviationNumberOfAuditRecord;
	Integer						minimumNumberOfAuditRecord;
	Integer						maximumNumberOfAuditRecord;

	Double						averageTimeOfPeriodInAuditRecord;
	Double						deviationTimeOfPeriodInAuditRecord;
	Integer						minimumTimeOfPeriodInAuditRecord;
	Integer						maximumTimeOfPeriodInAuditRecord;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
