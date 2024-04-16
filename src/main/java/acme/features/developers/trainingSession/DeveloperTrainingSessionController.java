
package acme.features.developers.trainingSession;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.trainings.TrainingSesion;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingSessionController extends AbstractController<Developer, TrainingSesion> {

	// Internal state ---------------------------------------------------------------------
	@Autowired
	protected DeveloperTrainingSessionListService		listService;

	@Autowired
	protected DeveloperTrainingSessionShowService		showService;

	@Autowired
	protected DeveloperTrainingSessionCreateService		createService;

	@Autowired
	protected DeveloperTrainingSessionUpdateService		updateService;

	@Autowired
	protected DeveloperTrainingSessionDeleteService		deleteService;

	@Autowired
	protected DeveloperTrainingSessionPublishService	publishService;

	// Constructors ------------------------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("publish", "update", this.publishService);
	}
}
