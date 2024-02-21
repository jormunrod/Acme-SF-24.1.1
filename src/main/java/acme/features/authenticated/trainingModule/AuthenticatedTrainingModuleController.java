
package acme.features.authenticated.trainingModule;

import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Authenticated;
import acme.entities.training.TrainingModule;

@Controller
public class AuthenticatedTrainingModuleController extends AbstractController<Authenticated, TrainingModule> {

}
