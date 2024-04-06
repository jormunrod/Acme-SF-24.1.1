
package acme.features.developers.trainingModule;

import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingModule;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleUpdateService extends AbstractService<Developer, TrainingModule> {

}
