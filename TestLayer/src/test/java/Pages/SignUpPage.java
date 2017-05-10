package Pages;

import java.util.List;

import Helper.Helpers;
import Helper.Utilities;
import common.Log;
import cucumber.api.DataTable;
import uiFramework.FindBy.FindType;
import uiFramework.WebDriver;

public class SignUpPage {

	static Log _log = Utilities.Log("Signup_Page");
	WebDriver _driver = Utilities.WebDriver(_log);

	public boolean isSignUpPage() {
		return _driver.findElement(FindType.Id, "btn-signup") != null;
	}

	public void loadSignUp() {
		_driver.navigate("http://courses.ultimateqa.com/users/sign_up");
	}

	public void clickLinkedInButton_SignUp() {
		_driver.findElement(FindType.ClassName, "linkedin").click();
	}

	public boolean isLinkedInPopup() {
		return _driver.findElement(FindType.ClassName, "logo") != null;
	}

	public void fillLoginDetails(DataTable userCredentials) throws Throwable {
		List<List<String>> data = userCredentials.raw();
		_driver.findElement(FindType.Id, "user_first_name").sendKeys(data.get(0).get(0));
		_driver.findElement(FindType.Id, "user_last_name").sendKeys(data.get(0).get(1));

		// generating random email id
		String emailId = data.get(0).get(2);
		if (emailId.contentEquals("random")) {
			_driver.findElement(FindType.Id, "user_email").sendKeys(Helpers.random_AlphaNumericString_Generator(6) + "@"
					+ Helpers.random_AlphabeticString_Generator(4) + ".com");
		} else {
			_driver.findElement(FindType.Id, "user_email").sendKeys(emailId);
		}

		_driver.findElement(FindType.Id, "user_password").sendKeys(data.get(0).get(3));
		_driver.findElement(FindType.Id, "btn-signup").click();

	}

	public boolean isUserAccountCreated(String createStatus) {
		if (createStatus.contentEquals("has been")) {
			return _driver.findElement(FindType.ClassName, "header-user-avatar") != null;
		} else {
			return _driver.findElement(FindType.Id, "notifications-error") != null;
		}
	}

	public boolean validErrorMessage(String message) throws InterruptedException {
		Thread.sleep(5000);
		String actualError = _driver.findElement(FindType.ClassName, "notifications-error__list-item").getText();
		System.out.println(actualError);
		return (actualError.toLowerCase().trim().equals(message.toLowerCase().trim()));
	}
}
