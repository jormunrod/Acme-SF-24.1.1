/**
 * Update Service for the Contract entity.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-08
 */

package acme.features.client.contract;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.projects.Contract;
import acme.entities.projects.Project;
import acme.roles.Client;

@Service
public class ClientContractPublishService extends AbstractService<Client, Contract> {

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

		id = super.getRequest().getData("id", int.class);
		contract = this.repository.findOneContractById(id);
		client = contract == null ? null : contract.getClient();
		status = client != null && super.getRequest().getPrincipal().hasRole(client);

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

		super.bind(object, "code", "instantiationMoment", "providerName", "customerName", "goals", "budget", "isPublished");
	}

	@Override
	public void validate(final Contract object) {
		assert object != null;

		boolean status;
		Integer totalCost;

		totalCost = this.repository.totalCostOfContractsByProjectId(object.getProject().getId());
		status = totalCost <= object.getProject().getCost().getAmount();

		System.out.println("Total cost: " + totalCost);
		System.out.println("Project cost: " + object.getProject().getCost().getAmount());

		super.state(status, "budget", "client.contract.form.error.budgetExceeded");
	}

	@Override
	public void perform(final Contract object) {
		assert object != null;

		Contract contract;
		contract = object;

		contract.setPublished(true);
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
