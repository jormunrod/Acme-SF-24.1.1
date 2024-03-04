
package acme.entities.sponsorships;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
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
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				updateMoment;

	@NotNull
	@DurationMin(days = 7)
	private Duration			displayPeriod;

	@NotNull
	@URL
	private String				picture;

	@NotBlank
	@Length(max = 75)
	private String				slogan;

	private String				link;
}
