
package acme.entities.objective;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objective extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 75)
	private String				title;

	@NotBlank
	@Length(max = 100)
	private String				description;

	@NotNull
	private ObjectivePriority	priority;

	private boolean				isCritical;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endDate;

	@URL
	@Length(max = 255)
	private String				link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
