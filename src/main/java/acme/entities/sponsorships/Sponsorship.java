
package acme.entities.sponsorships;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sponsorship extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank()
	@Pattern(regexp = "“[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@NotNull()
	@Temporal(TemporalType.TIMESTAMP)
	@Past()
	private Date				moment;

	@NotNull()
	@Temporal(TemporalType.TIMESTAMP)
	private Date				duration;

	@Email()
	private String				contactEmail;

	@NotNull()
	@Min(1)
	private int					amount;

	@NotNull
	private SponsorshipType		sponsorshipType;

	@URL()
	private String				link;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
