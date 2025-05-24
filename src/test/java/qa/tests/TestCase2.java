package qa.tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.ElementFetch;

public class TestCase2 extends BaseTest {
	
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents loginPage = new LoginPageEvents();
	
	@Test
	public void verifyFirstPage() {
		Reporter.log("Step 1 - Signin into LoginPage<br>");
		homePage.startHereButton();
		Reporter.log("Step 2 - Verifying Login Button is present<br>");
		loginPage.verifyLoginPageLogged();
	}

}
