/**
 * Controller for the client's contracts.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-08
 */

package acme.features.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.Contract;
import acme.roles.Client;

@Controller
public class ClientContractController extends AbstractController<Client, Contract> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientContractShowService	showService;

	@Autowired
	private ClientContractListService	listService;

	@Autowired
	private ClientContractCreateService	createService;

	@Autowired
	private ClientContractUpdateService	updateService;

	@Autowired
	private ClientContractDeleteService	deleteService;

	// AbstractController interface -------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
	}

}
