
package acme.features.sponsor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.sponsorships.Sponsorship;
import acme.roles.Sponsor;

@Controller
public class SponsorSponsorshipController extends AbstractController<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipListMineService	listMineService;
	@Autowired
	private SponsorSponsorshipShowService		showService;
	@Autowired
	private SponsorSponsorshipCreateService		createService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);

		super.addCustomCommand("list-mine", "list", this.listMineService);

	}

}
