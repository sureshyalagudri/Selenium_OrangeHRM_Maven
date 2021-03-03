package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Parameters_OrangeHRM_Login {
	WebDriver driver = null;

	@Test(priority = 1)
	@Parameters({ "url", "userName", "password" })
	public void LoginToOrangeHRM(String url, String userName, String password) throws InterruptedException {

		driver.navigate().to(url);

		// Wait until page loads.
		Common.explicitWaitByID(driver, "btnLogin", 180);

		driver.findElement(By.name("txtUsername")).sendKeys(userName);
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		System.out.println("Login successs");
	}

	@Test(priority = 2)
	public void Logout() {
		driver.findElement(By.id("welcome")).click();

		// Thread.sleep(2000);
		// Explicit Wait example
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
		String ActText = element.getText();
		System.out.println(ActText);
		element.click();
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
