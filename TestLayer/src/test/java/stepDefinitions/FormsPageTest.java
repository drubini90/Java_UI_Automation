package stepDefinitions;

import org.junit.Assert;

import Helper.Utilities;
import Pages.FormsPage;
import common.Log;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uiFramework.WebDriver;

public class FormsPageTest {

	static Log _log = Utilities.Log("Forms_Page");
	WebDriver _driver = Utilities.WebDriver(_log);

	FormsPage _formsPage = null;

	public FormsPage formsPage() {
		if (_formsPage == null) {
			_formsPage = new FormsPage();
		}
		return _formsPage;
	}

	@Given("^the user is on the forms page$")
	public void loadFormsPage() throws Throwable {
		formsPage().loadFormsFillPage();
	}

	@When("^the user fills form 1$")
	public void fillFormOne(DataTable formDetails) throws Throwable {
		formsPage().fillFormWithoutCaptcha(formDetails);
	}
	
	@Then("^the appropriate message is displayed(.*) for (.*)$")
	public void validateMessage(String expectedMessage, String formNumber) throws Throwable{
		Assert.assertTrue(formsPage().validateMessageForms(expectedMessage, formNumber));
	}
	
	@When("^the user fills form 2$")
	public void fillFormTwo (DataTable formDetails) throws Throwable {
		formsPage().fillFormWithCaptcha(formDetails);
	}
}
