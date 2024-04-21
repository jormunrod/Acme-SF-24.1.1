/**
 * Controller for the claims.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-21
 */

package acme.features.authenticated.claim;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Authenticated;
import acme.entities.claims.Claim;

@Controller
public class AuthenticatedClaimController extends AbstractController<Authenticated, Claim> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedClaimShowService	showService;

	@Autowired
	private AuthenticatedClaimListService	listService;

	//	@Autowired
	//	private AuthenticatedClaimCreateService createService;
	//	
	//	@Autowired
	//	private AuthenticatedClaimUpdateService updateService;
	//	
	//	@Autowired
	//	private AuthenticatedClaimDeleteService deleteService;
	//	
	//	@Autowired
	//	private AuthenticatedClaimPublishService publishService;

	// AbstractController interface -------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("list", this.listService);
		//		super.addBasicCommand("create", this.createService);
		//		super.addBasicCommand("update", this.updateService);
		//		super.addBasicCommand("delete", this.deleteService);
		//
		//		super.addCustomCommand("publish", "update", this.publishService);
	}

}
