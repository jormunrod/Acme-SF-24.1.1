/**
 * Controller for the claims.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-21
 */

package acme.features.any.claim;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.claims.Claim;

@Controller
public class AnyClaimController extends AbstractController<Any, Claim> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyClaimShowService		showService;

	@Autowired
	private AnyClaimListService		listService;

	@Autowired
	private AnyClaimCreateService	createService;

	// AbstractController interface -------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("create", this.createService);
	}

}
