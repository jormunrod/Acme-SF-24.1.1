
package acme.entities.training;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TrainingModule extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Pattern(regexp = "“[A-Z]{1,3}-[0-9]{3}")
	@Column(unique = true)
	private String				code;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotBlank
	@Length(max = 101)
	private String				details;

	@NotNull
	private DifficultyLevel		difficultyLevel;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				updateMoment;

	@URL
	private String				link;

	@NotNull
	private int					totalTime;
	// Derived attributes -----------------------------------------------------
	// Relationships ----------------------------------------------------------

}
