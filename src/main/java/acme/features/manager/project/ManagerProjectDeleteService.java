
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.CodeAudit;
import acme.entities.projects.Contract;
import acme.entities.projects.Project;
import acme.entities.projects.UserStory;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.trainings.TrainingModule;
import acme.roles.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	@Autowired
	protected ManagerProjectRepository repository;


	@Override
	public void authorise() {
		boolean status;
		int id;
		Project project;
		Manager manager;
		id = super.getRequest().getData("id", int.class);
		project = this.repository.findOneProjectById(id);
		manager = project == null ? null : project.getManager();
		status = project != null && !project.isPublished() && super.getRequest().getPrincipal().hasRole(manager);
		super.getResponse().setAuthorised(status);
	}
	@Override
	public void load() {
		Project object;
		int id;
		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProjectById(id);
		super.getBuffer().addData(object);
	}
	@Override
	public void bind(final Project object) {
		assert object != null;
		int id;
		Manager manager;
		id = super.getRequest().getData("id", int.class);
		manager = this.repository.findOneManagerById(id);
		super.bind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");
		object.setManager(manager);
	}
	@Override
	public void validate(final Project object) {
		assert object != null;
	}
	@Override
	public void perform(final Project object) {
		assert object != null;
		Collection<UserStory> userStories;
		Collection<TrainingModule> trainingModules;
		Collection<Contract> contracts;
		Collection<CodeAudit> codeAudits;
		Collection<Sponsorship> sponsorships;
		userStories = this.repository.findAllUserStoriesByProjectId(object.getId());
		trainingModules = this.repository.findAllTrainingModulesByProjectId(object.getId());
		contracts = this.repository.findAllContractsByProjectId(object.getId());
		codeAudits = this.repository.findAllCodeAuditsByProjectId(object.getId());
		sponsorships = this.repository.findAllSponsorshipsByProjectId(object.getId());
		this.repository.deleteAll(userStories);
		this.repository.deleteAll(trainingModules);
		this.repository.deleteAll(contracts);
		this.repository.deleteAll(codeAudits);
		this.repository.deleteAll(sponsorships);
		this.repository.delete(object);
	}
	@Override
	public void unbind(final Project object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "code", "title", "abstractText", "isPublished", "hasFatalErrors", "cost", "link");
		super.getResponse().addData(dataset);
	}
}
