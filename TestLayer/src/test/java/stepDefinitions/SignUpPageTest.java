package stepDefinitions;

import org.testng.Assert;

import Helper.Utilities;
import common.Log;
import uiFramework.WebDriver;
import Pages.SignUpPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class SignUpPageTest {

	static Log _log = Utilities.Log("Signup_Page");
	WebDriver _driver = Utilities.WebDriver(_log);

	SignUpPage _signUpPage = null;

	public SignUpPage signUpPage() {
		if (_signUpPage == null) {
			_signUpPage = new SignUpPage();
		}
		return _signUpPage;
	}

	@Then("^the signup page is loaded$")
	public void the_signup_page_is_loaded() throws Throwable {
		Assert.assertTrue(signUpPage().isSignUpPage());
	}

	@Given("^the user is on the signup page$")
	public void loadSignUpPage() throws Throwable {
		signUpPage().loadSignUp();
	}

	@When("^the user clicks on LinkedIn in signup page$")
	public void clickLinkedInButton() throws Throwable {
		signUpPage().clickLinkedInButton_SignUp();
	}

	@Then("^LinkedIn SignIn pop-up opens$")
	public void verifyLinkedInPopup() throws Throwable {
		Assert.assertTrue(signUpPage().isLinkedInPopup());
	}

	@When("^the user submits the login credentials$")
	public void fillSignUpPage(DataTable userCredential) throws Throwable {
		signUpPage().fillLoginDetails(userCredential);
	}

	@Then("^a new user account (.*) created$")
	public void verifyUserAccountPage(String createStatus) throws Throwable {
		Assert.assertTrue(signUpPage().isUserAccountCreated(createStatus));
	}

	@And("^system throws an error message as (.*)$")
	public void checkErrorMessage(String message) throws Throwable {
		Assert.assertTrue(signUpPage().validErrorMessage(message));
	}

}
