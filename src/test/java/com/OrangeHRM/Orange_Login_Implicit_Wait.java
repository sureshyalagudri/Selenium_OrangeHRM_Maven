package com.OrangeHRM;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Orange_Login_Implicit_Wait {
	ChromeDriver driver = null;

	@Test
	public void Login() {
		try {
			// Thread.sleep(9000);

			// Implicit wait. More organized is to use explicit wait instead of
			// implicit wait.
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// Always frefer to use CSS then xpath.
			// Xpath script:
			driver.findElementByXPath("//input[@name='txtUsername']").sendKeys("Admin");

			// CSS example.
			driver.findElementByCssSelector("input[name='txtPassword']").sendKeys("admin123");

			// driver.findElementByName("txtUsername").sendKeys("Admin");
			// driver.findElementByName("txtPassword").sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();
			driver.findElement(By.linkText("Dashboard")).isDisplayed();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void Logout() {
		try {

			driver.findElementById("welcome").click();

			// Thread.sleep(2000);
			// Explicit Wait example
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
			String ActText = element.getText();
			System.out.println(ActText);
			element.click();

			// driver.findElementByLinkText("Logout").click();

			// To get the text and verify.
			String text = driver.findElementById("logInPanelHeading").getText();
			AssertJUnit.assertEquals(text, "LOGIN Panel");

			// To get the url and verify.
			AssertJUnit.assertEquals(driver.getCurrentUrl(),
					"https://opensource-demo.orangehrmlive.com/index.php/auth/login");

			// verify title.
			AssertJUnit.assertEquals(driver.getTitle(), "OrangeHRM");

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
