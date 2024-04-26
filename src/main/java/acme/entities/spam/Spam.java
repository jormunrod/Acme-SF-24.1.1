
package acme.entities.spam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotBlank
	@Column(unique = true)
	private String				spanishWord;

	@NotBlank
	@Column(unique = true)
	private String				englishWord;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
