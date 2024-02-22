
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProgressLog extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	// TODO: Add the relationship with the contract
	//@ManyToOne
	//private Contract contract;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "PG-[A-Z]{1,2}-[0-9]{4}")
	private String				recordId;

	@Positive
	private double				completenessPercentage;

	@NotBlank
	@Length(max = 100)
	private String				progressComment;

	@Past
	private String				registrationMoment;

	@NotBlank
	@Length(max = 75)
	private String				responsiblePerson;

}
