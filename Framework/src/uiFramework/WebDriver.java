package uiFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Log;
import common.ReadPropertyFile;
import enums.Browser;
import uiFramework.FindBy.FindType;

public class WebDriver {

	private static RemoteWebDriver _driver = null;
	private static Browser _browser = Browser.Undefined;
	// private static String _primaryWindowHandle = null;
	private static final String TASKLIST = "tasklist";
	private static final String KILL = "taskkill /F /IM ";
	ReadPropertyFile propertyFile = null;

	static Log _log = null;

	public WebDriver(Log logger) {
		if (_driver == null) {
			propertyFile = new ReadPropertyFile();
			_browser = propertyFile.Browser();
			launchBrowser();
		}
		_log = logger;
	}
	
	public  org.openqa.selenium.WebDriver getDriver(){
		return _driver;
	}

	public void launchBrowser() {

		try {

			killProcess("werfault.exe");
			switch (_browser) {
			case Firefox: {
				killProcess("firefox.exe");
				System.setProperty("webdriver.gecko.driver",
						new File(System.getProperty("user.dir")).getParent() + "\\External\\Drivers\\geckodriver.exe");
				UIFirefoxDriver driver = new UIFirefoxDriver();
				_driver = (RemoteWebDriver) driver;

				break;
			}
			case Chrome: {

				killProcess("chrome.exe");
				killProcess("chromedriverserver.exe");
				System.setProperty("webdriver.chrome.driver", new File(System.getProperty("user.dir")).getParent()
						+ "\\External\\Drivers\\chromedriver_2.28.exe");

				// Chrome Options
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", propertyFile.DownloadPath());
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", chromePrefs);
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				cap.setCapability(ChromeOptions.CAPABILITY, options);

				UIChromeDriver driver = new UIChromeDriver(cap);
				_driver = (RemoteWebDriver) driver;
				break;
			}
			case InternetExplorer: {
				killProcess("iexplore.exe");
				killProcess("iedriverserver.exe");

				System.setProperty("webdriver.ie.driver", new File(System.getProperty("user.dir")).getParent()
						+ "\\External\\Drivers\\IEDriverServer_3.3.exe");
				UIIEDriver driver = new UIIEDriver();
				_driver = (RemoteWebDriver) driver;
				break;
			}
			default:
				break;
			}
			_driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
			// _primaryWindowHandle = _driver.getWindowHandle();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/// <summary>
	/// Navigates browser to specified url
	/// </summary>
	/// <param name="url">Address to navigate to</param>
	public void navigate(String url) {
		if (_driver != null) {
			System.out.println("WebDriver : Driver is not null");
		}
		_driver.navigate().to(url);
		_driver.manage().window().maximize();
	}

	/// <summary>
	/// Navigates back one entry in the browser's history
	/// </summary>
	public void navigateBack() {
		_driver.navigate().back();
	}

	/// <summary>
	/// Navigates forward one entry in the browser's history
	/// </summary>
	public void navigateForward() {
		_driver.navigate().forward();
	}

	/// <summary>
	/// Refreshes the current browser
	/// </summary>
	public void refresh() {
		_driver.navigate().refresh();
	}

	public String url() {
		return _driver.getCurrentUrl();
	}

	public void closeBrowser() {
		_driver.close();
	}

	/// <summary>
	/// Take a screenshot of the displayed desktop window
	/// </summary>
	public String takeScreenshot() {
		String filename = "Screenshot " + new Date().toString() + ".gif";
		return takeScreenshot(filename);
	}

	/// <summary>
	/// Deletes all cookies from browser cache
	/// </summary>
	public void DeleteCookies() {
		_driver.manage().deleteAllCookies();
		_log.info("Cookies have been deleted...");
	}

	/// <summary>
	/// Take a screenshot of the displayed desktop window
	/// </summary>
	/// <param name="filename">@param
	public String takeScreenshot(String filename) {
		String folder = System.getProperty("user.dir") + "\\screenshots";
		new File(folder).mkdirs();
		filename = folder + "\\" + filename;

		if (_driver != null) {
			switch (_browser) {
			case Android: {
				// DSAndroidDriver driver = Driver as DSAndroidDriver;
				// driver.TakeScreenshot(filename);
				break;
			}
			case Chrome: {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\c-tdhanasekaran\\git\\AutomationFramework\\External\\Chrome\\chromedriver.exe");
				UIChromeDriver driver = (UIChromeDriver) _driver;
				driver.takeScreenshot(filename);
				break;
			}
			case Firefox: {
				UIFirefoxDriver driver = (UIFirefoxDriver) _driver;
				driver.takeScreenshot(filename);
				break;
			}
			case InternetExplorer: {
				UIIEDriver driver = (UIIEDriver) _driver;
				driver.takeScreenshot(filename);
				break;
			}
			default: {
				break;
			}
			}
			_log.info("Saving screenshot as " + filename);
		} else {
			_log.info(
					"[WebBrowser.TakeScreenshot(string filename)] - Browser object is null. Unable to take screenshot.");
		}
		return filename;
	}

	/// <summary>
	/// Closes all windows associated with this driver and then quits/disposes
	/// the driver itself
	/// </summary>
	public static void quit() {
		if (_driver != null) {
			_log.info("[WebBrowser.Quit] - Disposing RemoteWebDriver object.");
			_driver.quit();
			// For firefox dispose method is throwing error
			if (_browser != Browser.Firefox && _browser != Browser.FirefoxDefaultProfile
					&& _browser != Browser.FirefoxWithGooglebotProfile) {
				_driver.close();
			}
			_driver = null;
			_browser = Browser.Undefined;
			// _primaryWindowHandle = null;
		}
	}

	/// <summary>
	/// Switches focus for subsequent UI automation to a new window
	/// </summary>
	/// <param name="windowName">Name of the window to switch to</param>
	public void SwitchToWindow(String windowName) {
		_driver.switchTo().window(windowName);
	}

	/// <summary>
	/// Switches focus for required frame
	/// </summary>
	/// <param name="frameName">Name of the frame to switch to</param>
	public void SwitchToFrame(String frameName) {
		_driver.switchTo().frame(frameName);
	}

	/// <summary>
	/// Switches focus for required frame
	/// </summary>
	/// <param name="findType">criteria to search</param>
	/// <param name="value">value for the criteria</param>
	public void SwitchToFrame(By findType, String value) {
		// _driver.switchTo().Frame(_driver.FindElement(new
		// FindBy().GetBy(findType, value)));
	}

	/// <summary>
	/// Switches focus for base browser page
	/// </summary>
	public void SwitchToBasePage() {
		_driver.switchTo().defaultContent();
	}

	/// <summary>
	/// Get alert box text
	/// </summary>
	/// <returns>Alert message</returns>
	public String GetAlertBoxText() {
		try {
			Alert alert = _driver.switchTo().alert();
			if (alert != null) {
				_log.info("Alert message: " + alert.getText());
				return alert.getText();
			} else {
				_log.info("Alert message text not found.");
				return null;
			}
		} catch (Exception ex) {
			// _log.Info("Alert box exception: " + ex.Message);
			return null;
		}

	}

	/// <summary>
	/// Finds the first descendent element based on criteria
	/// </summary>
	/// <param name="findType">What criteria to search from</param>
	/// <param name="value">Value of the criteria</param>
	/// <returns>WebElement representing the searched-for object</returns>
	public WebElement findElement(FindType findType, String value) {
		return findElement(findType, value, 1);
	}

	/// <summary>
	/// Finds the first element based on criteria
	/// </summary>
	/// <param name="findType">What criteria to search from</param>
	/// <param name="value">Value of the criteria</param>
	/// <param name="waitSec">Wait time in second(s)</param>
	/// <returns>WebElement representing the searched-for object</returns>
	public WebElement findElement(FindType findType, String value, int waitSec) {

		try {
			WebDriverWait waitVar = new WebDriverWait(_driver, waitSec);
			return waitVar.until(ExpectedConditions.visibilityOfElementLocated(new FindBy().GetBy(findType, value)));
		} catch (Exception e) {
			_log.info("[UIFramework.WebBrowser.GetElement] Exception caught: " + e.getMessage());
			return null;
		}
	}

	/*******
	 * 
	 * @param findType
	 * @param value
	 * @return
	 */
	public List<WebElement> findElements(FindType findType, String value) {
		return findElements(findType, value, 1);
	}

	/// <summary>
	/// Finds the first elements based on criteria
	/// </summary>
	/// <param name="findType">What criteria to search from</param>
	/// <param name="value">Value of the criteria</param>
	/// <param name="waitSec">Wait time in second(s)</param>
	/// <returns>WebElement representing the searched-for object</returns>
	public List<WebElement> findElements(FindType findType, String value, int waitSec) {
		List<WebElement> lstElement = null;
		try {
			WebDriverWait waitVar = new WebDriverWait(_driver, waitSec);			
			WebElement element = waitVar
					.until(ExpectedConditions.visibilityOfElementLocated(new FindBy().GetBy(findType, value)));
			boolean isElementAvailable = element != null;
			if (isElementAvailable) {

				lstElement = _driver.findElements(new FindBy().GetBy(findType, value));
			}

		} catch (Exception e) {
			_log.info("[UIFramework.WebBrowser.GetElement] Exception caught: " + e.getMessage());
		}
		return lstElement;
	}

	/*
	 * public UIWebElement findElement(FindType findType, String value, int
	 * waitSec) { UIWebElement element = null; //_log.Info(
	 * "Waiting for the element to be found."); try { Boolean isElementAvailable
	 * = true;// WaitForElementToLoad(findType, value, waitSec); if
	 * (isElementAvailable) { element = new UIWebElement(_driver.findElement(new
	 * FindBy().GetBy(findType, value)), _log); } else {
	 * 
	 * element = new UIWebElement(_driver.findElement(new
	 * FindBy().GetBy(findType, value)), _log); String s = (element == null) ?
	 * "Element not found" : "Element was found"; _log.info(
	 * "[WebBrowser.GetElement] - Element '" + value +
	 * "' is not available. Trying to get the element anyway."); _log.info(
	 * "Result of the GetElement: " + s); } } catch (Exception e) { _log.info(
	 * "[UIFramework.WebBrowser.GetElement] Exception caught: " +
	 * e.getMessage()); } return element; }
	 */
	private boolean isProcessRunning(String serviceName) throws Exception {

		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}

		return false;

	}

	private void killProcess(String processName) throws Exception {
		if (isProcessRunning(processName)) {
			Runtime.getRuntime().exec(KILL + processName);
		}
	}

}
