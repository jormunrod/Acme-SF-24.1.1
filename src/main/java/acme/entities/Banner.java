
package acme.entities;

import java.time.Duration;
import java.time.Instant;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.time.DurationMin;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Past
	private Instant				instantiationUpdateMoment;

	// TODO: must start at any moment after the instantiation/update moment
	@DurationMin(days = 7)
	private Duration			displayPeriod;

	private String				picture;

	@NotBlank
	@Length(max = 75)
	private String				slogan;

	private String				link;
}
