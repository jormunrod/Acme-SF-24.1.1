/**
 * Controller for the client's progressLog.
 * 
 * @Author: jormunrod
 * @Date: 2024-04-20
 */

package acme.features.client.progressLog;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.ProgressLog;
import acme.roles.Client;

@Controller
public class ClientProgressLogController extends AbstractController<Client, ProgressLog> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ClientProgressLogListService		listService;

	@Autowired
	protected ClientProgressLogShowService		showService;

	@Autowired
	protected ClientProgressLogCreateService	createService;

	//	@Autowired
	//	protected ClientProgressLogUpdateService	updateService;
	//
	//	@Autowired
	//	protected ClientProgressLogDeleteService	deleteService;
	//
	//	@Autowired
	//	protected ClientProgressLogPublishService	publishService;

	// AbstractController interface -------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		//		super.addBasicCommand("update", this.updateService);
		//		super.addBasicCommand("delete", this.deleteService);

		//		super.addCustomCommand("publish", "update", this.publishService);
	}

}
