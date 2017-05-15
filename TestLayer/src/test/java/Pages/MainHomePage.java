package Pages;

import Helper.Utilities;
import common.Log;
import uiFramework.FindBy.FindType;
import uiFramework.WebDriver;

public class MainHomePage {

	static Log _log = Utilities.Log("Main_Home_Page");
	WebDriver _driver = Utilities.WebDriver(_log);

	public void clickTheLink(String link) {
		_driver.findElement(FindType.LinkText, link).click();
	}

	public boolean verifyCurrentPage(String actualUrl) {

		String currentUrl = _driver.url();
		_driver.navigateBack();
		return actualUrl.equals(currentUrl);

	}

}
