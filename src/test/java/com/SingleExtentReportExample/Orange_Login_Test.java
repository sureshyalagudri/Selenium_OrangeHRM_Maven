package com.SingleExtentReportExample;

import org.testng.annotations.Test;

import com.OrangeHRM.Common;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Orange_Login_Test {
	ChromeDriver driver = null;

	@Test
	public void Login() {
		try {
			// Wait until page loads.
			Common.explicitWaitByID(driver, "btnLogin", 180);
			
			// Always frefer to use CSS then xpath.
			// Xpath script:
			driver.findElementByXPath("//input[@name='txtUsername']").sendKeys("Admin");
			
			//CSS example.
			driver.findElementByCssSelector("input[name='txtPassword']").sendKeys("admin123");
			
			//driver.findElementByName("txtUsername").sendKeys("Admin");
			//driver.findElementByName("txtPassword").sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.linkText("ashboard")).isDisplayed();	
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void Logout() {
		try {
			// Following is another example of partial link text
			driver.findElementByPartialLinkText("Welcome").click();
			
			//driver.findElementById("welcome").click();
			Thread.sleep(2000);
			driver.findElementByLinkText("Logout").click();
			
			//To get the text and verify.
			String text = driver.findElementById("logInPanelHeading").getText();
			AssertJUnit.assertEquals(text,"LOGIN Panel");
			
			//To get the url and verify.
			AssertJUnit.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/index.php/auth/login");
			
			//verify title.
			AssertJUnit.assertEquals(driver.getTitle(),"OrangeHRM");
			
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@BeforeTest
	public void beforeTest() {
		// Launch Chrome browser.
		// Using web driver so that it automatically sets required
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}

}
