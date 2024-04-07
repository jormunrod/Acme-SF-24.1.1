
package acme.features.developers.trainingSession;

import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.trainings.TrainingSesion;
import acme.roles.Developer;

@Service
public class DeveloperTrainingSessionPublishService extends AbstractService<Developer, TrainingSesion> {

}
