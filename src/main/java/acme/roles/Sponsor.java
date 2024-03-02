
package acme.roles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;

public class Sponsor extends AbstractRole {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	@NotBlank
	@Length(max = 76)
	private String				name;

	@NotBlank
	@Length(max = 101)
	private String				benefits;

	@URL
	String						webPage;

	@Email
	String						contactEmail;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
