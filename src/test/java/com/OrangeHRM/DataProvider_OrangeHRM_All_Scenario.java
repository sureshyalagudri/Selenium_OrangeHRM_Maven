package com.OrangeHRM;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProvider_OrangeHRM_All_Scenario extends OrangeHRMTestData {
	WebDriver driver = null;

	@Test(dataProvider = "LoginScenario")
	public void LoginToOrangeHRM(String userName, String password, String expectedMessage) throws InterruptedException {

		try {
			// Wait until page loads.
			Common.explicitWaitByID(driver, "btnLogin", 30);

			driver.findElement(By.name("txtUsername")).clear();
			driver.findElement(By.name("txtUsername")).sendKeys(userName);
			driver.findElement(By.name("txtPassword")).clear();
			driver.findElement(By.name("txtPassword")).sendKeys(password);
			driver.findElement(By.id("btnLogin")).click();

			boolean dashboard = false;
			try {
				dashboard = driver.findElement(By.linkText("Dashboard")).isDisplayed();
			} catch (Exception e) {
				dashboard = false;
			}

			if (dashboard) {
				String text = driver.findElement(By.linkText("Dashboard")).getText();
				Assert.assertEquals(text, expectedMessage);
				Logout();
			} else {
				String text = driver.findElement(By.id("spanMessage")).getText();
				Assert.assertEquals(expectedMessage, text);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	// @Test(priority = 2)
	public void Logout() {
		driver.findElement(By.id("welcome")).click();

		// Explicit Wait example
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
		String ActText = element.getText();
		System.out.println(ActText);
		element.click();
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
