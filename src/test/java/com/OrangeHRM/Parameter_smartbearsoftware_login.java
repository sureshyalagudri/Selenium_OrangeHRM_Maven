package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parameter_smartbearsoftware_login {
	WebDriver driver = null;

	@Test(priority = 1)
	@Parameters({ "url", "userName", "password" })
	public void Login(String url, String userName, String password) throws InterruptedException {

		driver.navigate().to(url);

		// Wait until page loads.
		Common.explicitWaitByID(driver, "ctl00_MainContent_username", 30);

		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(userName);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		// Wait until page loads.
		Common.explicitWaitByID(driver, "ctl00_logout", 30);
		System.out.println("Login successs");
	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {
		driver.findElement(By.id("ctl00_logout")).click();
	}

	@BeforeTest
	public void beforeTest() throws InterruptedException {
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
		Thread.sleep(2000);
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}
}
