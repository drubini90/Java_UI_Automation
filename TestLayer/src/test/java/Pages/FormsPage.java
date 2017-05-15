package Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.Utilities;
import common.Log;
import cucumber.api.DataTable;
import uiFramework.FindBy.FindType;
import uiFramework.WebDriver;

public class FormsPage {

	static Log _log = Utilities.Log("Forms_Page");
	WebDriver _driver = Utilities.WebDriver(_log);
	WebDriverWait waitVar = new WebDriverWait(_driver.getDriver(), 120);

	public void loadFormsFillPage() {
		_driver.navigate("https://www.ultimateqa.com/filling-out-forms/");
	}

	public void fillFormWithoutCaptcha(DataTable formDetails) {
		List<List<String>> data = formDetails.raw();
		_driver.findElements(FindType.Id, "et_pb_contact_name_1", 1000).get(0).sendKeys(data.get(0).get(0));
		_driver.findElements(FindType.Id, "et_pb_contact_message_1").get(0).sendKeys(data.get(0).get(1));
		_driver.findElements(FindType.ClassName, "et_pb_button").get(0).click();
	}

	public boolean validateMessageForms(String expectedMessage, String formNumber) throws InterruptedException {
		WebElement parentElement = null;
		String actualMessage = null;
		WebElement lstElement = null;
		if (formNumber.equals("form1")) {
			lstElement = _driver.findElement(FindType.Id, "et_pb_contact_form_0", 15);

		} else {
			lstElement = _driver.findElement(FindType.Id, "et_pb_contact_form_1", 60);
				
			}
		Thread.sleep(1000);
		parentElement = lstElement.findElement(By.className("et-pb-contact-message"));
		actualMessage = parentElement.findElement(By.tagName("p")).getText();
		return (expectedMessage.toLowerCase().trim().equals(actualMessage.toLowerCase().trim()));
	}

	public void fillFormWithCaptcha(DataTable formDetails) {
		List<List<String>> data = formDetails.raw();
		_driver.findElements(FindType.Id, "et_pb_contact_name_1", 1000).get(1).sendKeys(data.get(0).get(0));
		_driver.findElements(FindType.Id, "et_pb_contact_message_1").get(1).sendKeys(data.get(0).get(1));

		WebElement result = _driver.findElement(FindType.ClassName, "et_pb_contact_captcha");
		waitVar.until(ExpectedConditions.attributeToBeNotEmpty(result, "value"));
		_driver.findElements(FindType.ClassName, "et_pb_button").get(1).click();

	}
}
