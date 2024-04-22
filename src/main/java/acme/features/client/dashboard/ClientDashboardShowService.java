
package acme.features.client.dashboard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.datatypes.Money;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		int clientId;
		ClientDashboard dashboard;
		Integer numberOfProgressLogsBelow25;
		Integer numberOfProgressLogsBetween25And50;
		Integer numberOfProgressLogsBetween50And75;
		Integer numberOfProgressLogsAbove75;
		Money averageBudget = new Money();
		Money deviationBudget = new Money();
		Money minimumBudget = new Money();
		Money maximumBudget = new Money();

		clientId = super.getRequest().getPrincipal().getActiveRoleId();

		numberOfProgressLogsBelow25 = this.repository.numberOfProgressLogsBelow25(clientId);
		numberOfProgressLogsBetween25And50 = this.repository.numberOfProgressLogsBetween25And50(clientId);
		numberOfProgressLogsBetween50And75 = this.repository.numberOfProgressLogsBetween50And75(clientId);
		numberOfProgressLogsAbove75 = this.repository.numberOfProgressLogsAbove75(clientId);
		averageBudget.setAmount(this.repository.averageBudget(clientId));
		averageBudget.setCurrency("Currency");

		deviationBudget.setAmount(this.calculateStandardDeviation(clientId));
		deviationBudget.setCurrency("Currency");

		minimumBudget.setAmount(this.repository.minimumBudget(clientId));
		minimumBudget.setCurrency("Currency");

		maximumBudget.setAmount(this.repository.maximumBudget(clientId));
		maximumBudget.setCurrency("Currency");

		dashboard = new ClientDashboard();
		dashboard.setNumberOfProgressLogsBelow25(numberOfProgressLogsBelow25);
		dashboard.setNumberOfProgressLogsBetween25And50(numberOfProgressLogsBetween25And50);
		dashboard.setNumberOfProgressLogsBetween50And75(numberOfProgressLogsBetween50And75);
		dashboard.setNumberOfProgressLogsAbove75(numberOfProgressLogsAbove75);
		dashboard.setAverageBudget(averageBudget);
		dashboard.setDeviationBudget(deviationBudget);
		dashboard.setMinimumBudget(minimumBudget);
		dashboard.setMaximumBudget(maximumBudget);

		super.getBuffer().addData(dashboard);
	}

	private Double calculateStandardDeviation(final int clientId) {
		Double averageBudget = this.repository.averageBudget(clientId);
		Collection<Money> budgets = this.repository.findBudgetsByClientId(clientId);

		if (budgets.isEmpty())
			return null;

		double variance = budgets.stream().mapToDouble(budget -> Math.pow(budget.getAmount() - averageBudget, 2)).average().orElse(0.0);

		return Math.sqrt(variance);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, //
			"numberOfProgressLogsBelow25", //
			"numberOfProgressLogsBetween25And50", //
			"numberOfProgressLogsBetween50And75", //
			"numberOfProgressLogsAbove75", //
			"averageBudget", "deviationBudget", "minimumBudget", "maximumBudget");

		super.getResponse().addData(dataset);
	}
}
