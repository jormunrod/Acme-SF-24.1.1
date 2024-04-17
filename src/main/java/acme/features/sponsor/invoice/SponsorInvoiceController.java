
package acme.features.sponsor.invoice;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.sponsorships.Invoice;
import acme.roles.Sponsor;

@Controller
public class SponsorInvoiceController extends AbstractController<Sponsor, Invoice> {

	@Autowired
	private SponsorInvoiceListService listService;


	@PostConstruct
	protected void initialise() {

		super.addBasicCommand("list", this.listService);

	}

}