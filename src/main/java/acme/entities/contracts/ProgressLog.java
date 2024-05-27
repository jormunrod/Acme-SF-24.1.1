/**
 * ProgressLog Entity
 * 
 * @Author: jormunrod
 */

package acme.entities.contracts;

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

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "contract_id"), @Index(columnList = "recordId")
})
public class ProgressLog extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "PG-[A-Z]{1,2}-[0-9]{4}")
	private String				recordId;

	@NotNull
	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	private Double				completenessPercentage;

	@NotBlank
	@Length(max = 100)
	private String				progressComment;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				registrationMoment;

	@NotBlank
	@Length(max = 75)
	private String				responsiblePerson;

	private boolean				isPublished			= false;

	// Derived attributes --------------------------------------------------------

	// Relationships -------------------------------------------------------------

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	private Contract			contract;

}
