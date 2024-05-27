
package acme.entities.sponsorships;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "id"), @Index(columnList = "draftMode"), @Index(columnList = "code"), @Index(columnList = "sponsorship_id")

})
public class Invoice extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "IN-[0-9]{4}-[0-9]{4}")
	private String				code;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				registrationTime;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dueDate;

	@NotNull
	private Money				quantity;

	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	private double				tax;

	@URL
	@Length(max = 255)
	private String				link;

	private boolean				draftMode			= true;

	@NotNull
	private Money				totalAmount;

	// Derived attributes -----------------------------------------------------


	public Money getTotalAmountWithTax() {
		Double totalAmount = this.quantity.getAmount() * (1 + this.tax);
		Money m = new Money();
		m.setAmount(totalAmount);
		m.setCurrency(this.quantity.getCurrency());
		return m;

	}

	// Relationships ----------------------------------------------------------


	@ManyToOne(optional = false)
	@NotNull
	@Valid
	private Sponsorship sponsorship;


	@Override
	public String toString() {
		return "Invoice{" + "code='" + this.code + '\'' + ", registrationTime=" + this.registrationTime + ", dueDate=" + this.dueDate + ", quantity=" + this.quantity + ", tax=" + this.tax + ", link='" + this.link + '\'' + ", draftMode=" + this.draftMode
			+ ", totalAmount=" + this.totalAmount + '}';
	}
}
