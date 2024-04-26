
package acme.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpamConfig {

	@Value("${spam.detector.threshold:0.1}")
	private double threshold;


	public double getThreshold() {
		return this.threshold;
	}

	public void setThreshold(final double threshold) {
		this.threshold = threshold;
	}
}
