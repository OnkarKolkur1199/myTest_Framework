package pageEvents;

import org.testng.Assert;

import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;
import pageObjects.RegisterPageElements;
import utils.ElementFetch;

public class RegisterPageEvents {
	
	ElementFetch ele = new ElementFetch();
	
	public void submitAndRegisterButton() {
		ele.getWebElement("XPATH", RegisterPageElements.registerSubmitButton).click();
	}
	
	public void clickOnContinue() {
		ele.getWebElement("XPATH", RegisterPageElements.continueButton).click();
	}
	
	public void clickOnMale() {
		ele.getWebElement("XPATH", RegisterPageElements.maleRadio).click();
	}
	
	public void clickOnFemale() {
		ele.getWebElement("XPATH", RegisterPageElements.femaleRadio).click();
	}
	
	public void enterRegistrationCreds(String firstName, String lastName, String email, String pass, String confirmPass) {
		ele.getWebElement("XPATH", RegisterPageElements.firstName).sendKeys(firstName);
		ele.getWebElement("XPATH", RegisterPageElements.lastName).sendKeys(lastName);
		ele.getWebElement("XPATH", RegisterPageElements.eMailBox).sendKeys(email);
		ele.getWebElement("XPATH", RegisterPageElements.passBox).sendKeys(pass);
		ele.getWebElement("XPATH", RegisterPageElements.confirmPassBox).sendKeys(confirmPass);
	}

}
