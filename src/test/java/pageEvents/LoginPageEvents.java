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
		//ele.getWebElement("XPATH", LoginPageElements.emailAddress).sendKeys("onkarkolkur1999@gmail.com");
		//ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys("Happy@1199");
		ele.getWebElement("XPATH", LoginPageElements.emailAddress).sendKeys(myMail);
		ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys(myPassword);
	}

}
