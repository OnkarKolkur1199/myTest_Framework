package pageEvents;

import pageObjects.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
	
	ElementFetch ele = new ElementFetch();
	
	public void loginButton() {
		ele.getWebElement("XPATH", HomePageElements.loginButtonText).click();
	}
	
	public void clickOnRegister() {
		ele.getWebElement("XPATH", HomePageElements.RegisterButtonText).click();;
	}

}
