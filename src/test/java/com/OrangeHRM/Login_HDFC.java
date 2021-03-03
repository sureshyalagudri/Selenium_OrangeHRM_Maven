package com.OrangeHRM;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_HDFC {
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
	public void Login_HDFC_Test() {
		try {
			Thread.sleep(9000);
			driver.switchTo().frame("login_page");
			driver.findElementByName("fldLoginUserId").sendKeys("Admin");
			//driver.findElementByXPath("//table[@class='lForm']//img").click();
			driver.findElementByCssSelector("td:nth-child(2) > a:nth-child(1) > img").click();
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			driver.findElementByLinkText("Terms and Conditions").click();
			
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
