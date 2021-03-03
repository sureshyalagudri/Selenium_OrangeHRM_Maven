package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FromExcel_OrangeHRM_Test extends OrangeHRMTestData {
	WebDriver driver = null;

	@Test(dataProvider = "LoginExcelData")
	public void Sign_On(String uname, String password) throws InterruptedException {
		driver.findElement(By.name("txtUsername")).sendKeys(uname);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		// ---------To Verify Logout Function without using
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}

	@BeforeTest
	public void beforeTest() {
		// Launch Chrome browser.
		// Using web driver so that it automatically sets required
		WebDriverManager.chromedriver().setup();
		// This chrome option is to set headless mode.
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);

		// Incognito mode. without cookies.
		// options.addArguments("incognito");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

		// Wait until page loads.
		Common.explicitWaitByID(driver, "btnLogin", 180);
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}
}
