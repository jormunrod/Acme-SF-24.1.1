/**
 * Controller for the progress log for any user.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-22
 */

package acme.features.any.progressLog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.contracts.ProgressLog;

@Controller
public class AnyProgressLogController extends AbstractController<Any, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyProgressLogListService	listService;

	@Autowired
	protected AnyProgressLogShowService	showService;

	// AbstractController interface -------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
