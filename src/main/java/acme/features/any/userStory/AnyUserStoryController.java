
package acme.features.any.userStory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;
import acme.entities.projects.UserStory;

@Controller
public class AnyUserStoryController extends AbstractController<Any, UserStory> {

	@Autowired
	protected AnyUserStoryListService	listService;

	@Autowired
	protected AnyUserStoryShowService	showService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}
}
