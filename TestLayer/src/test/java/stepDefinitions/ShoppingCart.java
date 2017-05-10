package stepDefinitions;

import Helper.Utilities;
import Pages.ShoppingCartPage;
import common.Log;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uiFramework.WebDriver;

public class ShoppingCart {

	static Log _log = Utilities.Log("ShoppingCart");
	WebDriver _driver = Utilities.WebDriver(_log);
	ShoppingCartPage _shoppingCartPage = null;
	
	public ShoppingCartPage ShoppingCartPage(){
		if(_shoppingCartPage == null){
			_shoppingCartPage = new ShoppingCartPage();
		}
		return _shoppingCartPage;
	}
	
	@Given("^the user browses the site (.*)$")
	public void NavigateTo(String url) throws Throwable {
		_driver.navigate(url);
	}

	@When("^the user searches for (.*)$")
	public void Search(String product) throws Throwable {
		ShoppingCartPage().search(product);
	}

	@Then("^the current page will be the search result page$")
	public void VerifyCurrentPage() throws Throwable {
		_log.info("the current page will be the search result page");
	}

	@When("^the user adds the (.*) product in the search result to the cart$")
	public void AddToCart(int index) throws Throwable {
		ShoppingCartPage().addProductToCart(index);
	}

	@Then("^the shopping cart has the added product$")
	public void VerifyCart() throws Throwable {
		_log.info("VerifyCart");
	}
	
	

}
