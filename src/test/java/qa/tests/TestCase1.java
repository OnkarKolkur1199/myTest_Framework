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
	
	@Test (dataProvider = "loginTestData", dataProviderClass = BaseTest.class)
	public void sampleMethodForEnteringCreds(String myMail, String myPassword) {
		Reporter.log("Step 1 - Signing into LoginPage<br>");
		homePage.startHereButton();
		Reporter.log("Step 2 - Verifying Login Button is present<br>");
		loginPage.verifyLoginPageLogged();
		Reporter.log("Step 3 - Enter the creds<br>");
		loginPage.enterCreds(myMail, myPassword);
	}
}
