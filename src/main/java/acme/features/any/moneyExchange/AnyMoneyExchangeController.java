
package acme.features.any.moneyExchange;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.client.data.accounts.Any;

@Controller
public class AnyMoneyExchangeController extends AbstractController<Any, MoneyExchange> {
	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyMoneyExchangePerformService exchangeService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("perform", this.exchangeService);
	}
}
