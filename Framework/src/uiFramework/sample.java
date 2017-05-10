package uiFramework;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import common.ReadPropertyFile;

public class sample {

	@Test
	public void readConfig() throws Exception{
		System.setProperty("webdriver.chrome.driver",new File(System.getProperty("user.dir")).getParent() + "\\External\\Drivers\\chromedriver_2.28.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http:\\www.amazon.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[@id='nav-tools']/a[1]/span[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ap_email")).sendKeys("adfsdfsdf");
		Thread.sleep(4000);
		driver.findElement(By.id("ap_password")).sendKeys("test@gmail.com");
		driver.findElement(By.id("signInSubmit")).click();
		Thread.sleep(4000);
		String s = driver.findElement(By.className("a-list-item")).getText();
		System.out.println(s);
	}
}
