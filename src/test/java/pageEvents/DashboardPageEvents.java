package pageEvents;

import pageObjects.DashboardPageElements;
import utils.ElementFetch;

public class DashboardPageEvents {
	
ElementFetch ele = new ElementFetch();
	
	public boolean verifyAccount(String myMail) {
		String accountHolderName = ele.getWebElement("XPATH", DashboardPageElements.accountName).getText();
		if(accountHolderName.equals(myMail)) {
			return true;
		}
		return false;
	}

}
