package pageEvents;

import org.testng.Assert;
import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {

	ElementFetch ele = new ElementFetch();
	
	public void verifyLoginPageLogged() {
		Assert.assertTrue(ele.getWebElement("XPATH", LoginPageElements.loginText).isDisplayed(), "Login button not found");
	}
	
	public void enterCreds(String myMail, String myPassword) {
		ele.getWebElement("XPATH", LoginPageElements.emailAddress).sendKeys(myMail);
		ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys(myPassword);
	}
	
	public void clickOnLogin() {
		ele.getWebElement("XPATH", LoginPageElements.loginText).click();;
	}

}
