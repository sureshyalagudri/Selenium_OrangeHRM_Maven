package com.OrangeHRM;

import static org.junit.Assert.fail;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_HDFC_Alert {
	ChromeDriver driver = null;

	@BeforeTest
	public void launchBrowser() {
		// Launch Chrome browser.
		// Using web driver so that it automatically sets required
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://netbanking.hdfcbank.com/netbanking/");
	}
	
	@Test
	public void Login_Test() {
		try {
			Thread.sleep(2000);
			driver.switchTo().frame("login_page");
			driver.findElementByName("fldLoginUserId").sendKeys("");
			//driver.findElementByXPath("//table[@class='lForm']//img").click();
			driver.findElementByCssSelector("td:nth-child(2) > a:nth-child(1) > img").click();

			String alertText = driver.switchTo().alert().getText();
			Assert.assertEquals(alertText, "Customer ID  cannot be left blank.");
			driver.switchTo().alert().accept();
			
			System.out.println("Login successs");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}
}
