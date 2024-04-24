/**
 * Controller for the contracts of any user.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-22
 */

package acme.features.any.contract;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.contracts.Contract;

@Controller
public class AnyContractController extends AbstractController<Any, Contract> {

	// Internal state ---------------------------------------------------------------------

	@Autowired
	protected AnyContractListService	listService;

	@Autowired
	protected AnyContractShowService	showService;

	// Constructors ------------------------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}
}
