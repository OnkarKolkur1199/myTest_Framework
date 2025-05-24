package qa.tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pageEvents.DashboardPageEvents;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageEvents.RegisterPageEvents;
import utils.ElementFetch;
import org.testng.Reporter;

public class TestCase1 extends BaseTest {
	
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents loginPage = new LoginPageEvents();
	DashboardPageEvents dashboardPage = new DashboardPageEvents();
	RegisterPageEvents registerPage = new RegisterPageEvents();
	
	@Test (dataProvider = "RegisterationTestData", dataProviderClass = BaseTest.class, priority = 1)
	public void FillRegistrationData(String firstName, String lastName, String email, String pass, String confirmPass) {
		Reporter.log("Step 1 - Clicking on Register<br>");
		homePage.clickOnRegister();
		Reporter.log("Step 2 - Filling the details<br>");
		registerPage.clickOnMale();
		registerPage.enterRegistrationCreds(firstName, lastName, email, pass, confirmPass);
	}
	
	@Test (dataProvider = "RegisterationTestData", dataProviderClass = BaseTest.class, priority = 2)
	public void SubmitAndVerfiyAccount(String firstName, String lastName, String email, String pass, String confirmPass) {
		Reporter.log("Step 3 - Submitting the details and Clicking on Register button<br>");
		registerPage.submitAndRegisterButton();
		Reporter.log("Step 4 - Clicking on continue<br>");
		registerPage.clickOnContinue();
		Reporter.log("Step 5 - Account verification<br>");
		dashboardPage.verifyAccount(email);
	}
}
