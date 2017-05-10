package uiFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import common.Log;
import uiFramework.FindBy.FindType;


public class UIWebElement implements WebElement {
			
	private WebElement _element = null;
	private Log _log = null;
	
	public UIWebElement(WebElement element,Log logger)
	{
		_element = element;
		_log = logger;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCssValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*
			/// <summary>
			/// Finds descendent element
			/// </summary>
			/// <param name="findType">Method to use for searching for the child element</param>
			/// <param name="valueToFind">Value of the search method</param>
			/// <returns>WebElement child</returns>
			public WebElement findElement(FindType findType, String valueToFind)
			{
				return findElement(findType, valueToFind, 1500);
			}

			/// <summary>
			/// Finds descendent element
			/// </summary>
			/// <param name="findType">Method to use for searching for the child element</param>
			/// <param name="valueToFind">Value of the search method</param>
			/// <param name="waitTime">Duration of time to search for the element</param>
			/// <returns>WebElement child</returns>
			public WebElement findElement(FindType findType, String valueToFind, int waitTime)
			{
				WebElement child = null;
					try
					{									 
	                    while (child == null) 
	                    {
	                        try
	                        {
	                            child = _element.findElement(new FindBy().GetBy(findType, valueToFind));
	                        }
	                        catch(Exception ex)
	                        {
	                        	throw ex;
	                        }
	                    }
					}
					catch (Exception e)
					{
						_log.info("[GetDescendentElement] NoSuchElementException caught: " + e.getMessage());
					}

				if (child != null)
				{
					_log.info(String.format("[GetDescendentElement] Found element with FindBy.{0} = {1}.", findType.toString(), valueToFind));
				}
				else
				{
					_log.info(String.format("[GetDescendentElement] Did not find element with FindBy.{0} = {1}.", findType.toString(), valueToFind));
				}
				return child;
			}
			*/


}
