package qa.tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.ElementFetch;
import org.testng.Reporter;

public class TestCase1 extends BaseTest {
	
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents loginPage = new LoginPageEvents();
	
	@Test
	public void sampleMethodForEnteringCreds() {
		Reporter.log("Signin into LoginPage<br>");
		homePage.startHereButton();
		Reporter.log("Verifying Login Button is present<br>");
		loginPage.verifyLoginPageLogged();
		Reporter.log("Enter the creds");
		loginPage.enterCreds();
	}
}
