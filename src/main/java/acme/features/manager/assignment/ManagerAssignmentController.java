
package acme.features.manager.assignment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.projects.Assignment;
import acme.roles.Manager;

@Controller
public class ManagerAssignmentController extends AbstractController<Manager, Assignment> {

	@Autowired
	private ManagerAssignmentCreateService	createService;

	@Autowired
	private ManagerAssignmentDeleteService	deleteService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
	}
}
