package Pages;

import Helper.Utilities;
import common.Log;
import uiFramework.FindBy.FindType;
import uiFramework.WebDriver;

public class MainHomePage {
	
	static Log _log = Utilities.Log("Main_Home_Page");
	WebDriver _driver = Utilities.WebDriver(_log);
	
	public void clickTheLink (String link){
		_driver.findElement(FindType.LinkText,link).click();
	}
	
	public void verifyCurrentPage(){
		_driver.navigateBack();
	}
}
