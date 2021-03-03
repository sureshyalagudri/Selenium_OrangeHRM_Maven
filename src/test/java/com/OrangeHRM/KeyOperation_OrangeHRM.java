package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KeyOperation_OrangeHRM {
	WebDriver driver = null;

	@Test
	public void Login() {
		Common.explicitWaitByID(driver, "btnLogin", 180);
		driver.findElement(By.name("txtUsername")).sendKeys("Admin",Keys.TAB,"admin123",Keys.ENTER);
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
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
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}
}
