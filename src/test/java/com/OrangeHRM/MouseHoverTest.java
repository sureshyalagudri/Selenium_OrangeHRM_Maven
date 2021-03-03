package com.OrangeHRM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHoverTest {
	ChromeDriver driver = null;

	@Test
	public void f() {
	}
	
	@Test
	public void Login() {
		try {
			Thread.sleep(9000);

			// Implicit wait. More organized is to use explicit wait instead of
			// implicit wait.
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.findElementByName("txtUsername").sendKeys("Admin");
			driver.findElementByName("txtPassword").sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.linkText("Dashboard")).isDisplayed();
			
			Thread.sleep(2000);
			
			WebElement admin = driver.findElementById("menu_admin_viewAdminModule");
	        Actions action = new Actions(driver);
	        action.moveToElement(admin).build().perform();
	        WebElement usermanagement = driver.findElementByLinkText("User Management");
	        action.moveToElement(usermanagement).build().perform();
	        Thread.sleep(5000);
	        driver.findElementByLinkText("Users").click();

			// -------------------- Navigate --------------------------//
			// driver.navigate().back();
			// System.out.println(driver.getCurrentUrl());
			//
			// driver.navigate().forward();
			// System.out.println(driver.getCurrentUrl());
	        
	        // -------------------- Navigate --------------------------//
	        
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
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
		//options.addArguments("incognito");
		
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
