package com.OrangeHRM;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login_Test {
	ChromeDriver driver = null;

	@Before
	public void launchBrowser() {
		// Launch Chrome browser.
		// Using web driver so that it automatically sets required
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@After
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}

	@Test
	public void Login_Test() {
		try {
			Thread.sleep(9000);
			driver.findElementByName("txtUsername").sendKeys("Admin");
			driver.findElementByName("txtPassword").sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.linkText("Dashboard")).isDisplayed();
			System.out.println("Login successs");

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
