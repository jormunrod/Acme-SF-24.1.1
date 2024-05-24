/**
 * Update Service for the Contract entity.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-08
 */

package acme.features.client.contract;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.contracts.Contract;
import acme.entities.projects.Project;
import acme.roles.Client;

@Service
public class ClientContractUpdateService extends AbstractService<Client, Contract> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ClientContractRepository repository;

	// AbstractService interface -----------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int id;
		Contract contract;
		Client client;

		// Check if the contract exists and the principal is the client
		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractById(id);
		client = contract == null ? null : contract.getClient();
		status = client != null && super.getRequest().getPrincipal().hasRole(client);

		// Check if the contract's project is published
		if (super.getRequest().hasData("project")) {
			int projectId = super.getRequest().getData("project", int.class);
			if (projectId != 0) {
				Project project = this.repository.findOneProjectById(projectId);
				status = project != null && project.isPublished();
			}
		}

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Contract object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneContractById(id);

		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Contract object) {
		assert object != null;

		super.bind(object, "code", "providerName", "customerName", "goals", "budget", "project");
	}

	@Override
	public void validate(final Contract object) {
		assert object != null;
		boolean status;
		// Check if the contract code is unique
		if (!super.getBuffer().getErrors().hasErrors("code")) {

			status = this.repository.findOneContractByCodeAndDifferentId(object.getCode(), object.getId()) == null;
			super.state(status, "code", "client.contract.form.error.duplicated");

		}
		if (!super.getBuffer().getErrors().hasErrors("budget")) {
			// Check if the budget currency is the same as the project currency
			if (object.getProject() != null && object.getBudget() != null && !Objects.equals(object.getBudget().getCurrency(), object.getProject().getCost().getCurrency())) {
				super.state(false, "budget", "client.contract.form.error.currency");
				super.state(false, "budget", "(" + object.getProject().getCost().getCurrency() + ")");
			}

			// Check if the budget amount is negative
			if (object.getProject() != null && object.getBudget() != null && object.getBudget().getAmount() < 0)
				super.state(false, "budget", "client.contract.form.error.negative");

			// Check if the budget amount is greater than the project cost
			if (object.getProject() != null && object.getBudget() != null && Objects.equals(object.getBudget().getCurrency(), object.getProject().getCost().getCurrency())) {
				status = object.getBudget().getAmount() <= object.getProject().getCost().getAmount();
				super.state(status, "budget", "client.contract.form.error.budget");
				super.state(status, "budget", "(max: " + object.getProject().getCost().getAmount() + " " + object.getProject().getCost().getCurrency() + ")");
			}
		}
	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Contract object) {
		assert object != null;
		Collection<Project> projects;
		SelectChoices choices;
		Dataset dataset;

		projects = this.repository.findAllPublishedProjects();
		choices = SelectChoices.from(projects, "title", object.getProject());
		dataset = super.unbind(object, "code", "instantiationMoment", "providerName", "customerName", "goals", "budget", "isPublished");
		dataset.put("projects", choices.getSelected().getKey());
		dataset.put("projects", choices);

		super.getResponse().addData(dataset);
	}

}
