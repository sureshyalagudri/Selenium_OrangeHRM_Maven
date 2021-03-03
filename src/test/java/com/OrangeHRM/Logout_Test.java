package com.OrangeHRM;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Logout_Test {

	@Test
	public void Login_Test() {
		ChromeDriver driver = null;
		try {
			// Launch Chrome browser.
			// Using web driver so that it automatically sets required
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			//
			driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");

			Thread.sleep(9000);

			driver.findElementByName("txtUsername").sendKeys("Admin");
			driver.findElementByName("txtPassword").sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.linkText("Dashboard1")).isDisplayed();
			System.out.println("Login successs");
			driver.quit();
			// driver.close();
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

}
