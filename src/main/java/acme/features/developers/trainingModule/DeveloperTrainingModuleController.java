
package acme.features.developers.trainingModule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.trainings.TrainingModule;
import acme.roles.Developer;

@Controller
public class DeveloperTrainingModuleController extends AbstractController<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------------------
	@Autowired
	protected DeveloperTrainingModuleListService	listService;

	@Autowired
	protected DeveloperTrainingModuleShowService	showService;

	// Constructors ------------------------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
