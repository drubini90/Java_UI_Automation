package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Helper.Utilities;
import common.Log;
import uiFramework.FindBy.FindType;
import uiFramework.WebDriver;



public class ShoppingCartPage {
	
	static Log _log = Utilities.Log("ShoppingCart");
	WebDriver _driver = Utilities.WebDriver(_log);
	
	public void search(String product){
		_driver.findElement(FindType.Id,"twotabsearchtextbox").sendKeys(product);
		_driver.findElement(FindType.ClassName, "nav-search-submit").findElement(By.tagName("input")).click();
	}
	
	public void addProductToCart(int index){
		
		_log.info("addProductToCart");
		List<WebElement> lstProducts = _driver.findElements(FindType.ClassName, "s-access-image");
		_log.info("Total Product Count :" + lstProducts.size());
		lstProducts.get(index).click();
		_driver.findElement(FindType.Id, "add-to-cart-button").click();
				//s-access-image cfMarker
	}
}
