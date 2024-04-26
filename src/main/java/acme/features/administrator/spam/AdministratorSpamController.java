
package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Administrator;
import acme.entities.spam.Spam;

@Controller
public class AdministratorSpamController extends AbstractController<Administrator, Spam> {

	@Autowired
	private AdministratorSpamListService	listService;

	@Autowired
	private AdministratorSpamShowService	showService;

	@Autowired
	private AdministratorSpamCreateService	createService;

	@Autowired
	private AdministratorSpamUpdateService	updateService;

	@Autowired
	private AdministratorSpamDeleteService	deleteService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
	}
}
