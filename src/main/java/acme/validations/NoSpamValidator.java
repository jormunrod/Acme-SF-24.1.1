
package acme.validations;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.components.SpamConfig;
import acme.features.administrator.spam.AdministratorSpamRepository;
import detector.SpamDetector;

public class NoSpamValidator implements ConstraintValidator<NoSpam, String> {

	@Autowired
	private SpamConfig					spamConfig;

	@Autowired
	private AdministratorSpamRepository	repository;

	private SpamDetector				spamDetector;


	@PostConstruct
	public void init() {
		List<String> spams = this.repository.findAllWords().stream().toList();
		double threshold = this.spamConfig.getThreshold();
		this.spamDetector = new SpamDetector(spams, threshold);
	}

	@Override
	public void initialize(final NoSpam constraintAnnotation) {
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		if (value == null)
			return true;
		return !this.spamDetector.isSpam(value);
	}
}
