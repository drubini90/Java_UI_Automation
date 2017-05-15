package stepDefinitions;

import org.testng.Assert;

import Helper.Utilities;
import Pages.MainHomePage;
import common.Log;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uiFramework.WebDriver;

public class CommonStepDefinitions {
	
	static Log _log = Utilities.Log("Common_Steps");
	WebDriver _driver = Utilities.WebDriver(_log);

	@Given("^the user browses the (.*)$")
	public void navigateTo (String url) throws Throwable{
		_driver.navigate(url);
	}
	
	
	MainHomePage _mainHomePage = null;	
	public MainHomePage mainHomePage(){
		
		if (_mainHomePage == null){
			_mainHomePage = new MainHomePage();
		}
		return _mainHomePage;
		
	}
	
	@When("^the user clicks on the (.*)$")
	public void findLinkAndClick (String link) throws Throwable{
		mainHomePage().clickTheLink(link);
	}
	
	@Then("^the appropriate page will be loaded(.*)$")
	public void verifyLoadedPage(String actualUrl) throws Throwable{
		Assert.assertTrue(mainHomePage().verifyCurrentPage(actualUrl));
	}
}
