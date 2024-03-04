
package acme.entities.invoices;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.sponsorships.Sponsorship;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank()
	@Column(unique = true)
	@Pattern(regexp = "IN-[0-9]{4}-[0-9]{4}")
	private String				code;

	@NotNull()
	@Past()
	@Temporal(TemporalType.TIMESTAMP)
	private Date				registrationTime;

	@NotNull()
	@Past()
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dueDate;

	@NotNull()
	@Positive()
	private int					quantity;

	@NotNull()
	@PositiveOrZero()
	private int					tax;

	@URL()
	@Length(max = 255)
	private String				link;

	// Derived attributes -----------------------------------------------------


	@Transient
	public int getTotalAmount() {
		return this.quantity + this.tax;

	}

	// Relationships ----------------------------------------------------------


	@ManyToOne(optional = false)
	@NotNull()
	@Valid()
	private Sponsorship sponsorship;
}
