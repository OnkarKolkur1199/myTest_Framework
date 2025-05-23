package pageEvents;

import pageObjects.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
	
	ElementFetch ele = new ElementFetch();
	
	public void startHereButton() {
		ele.getWebElement("XPATH", HomePageElements.startHereButtonText).click();
	}

}
