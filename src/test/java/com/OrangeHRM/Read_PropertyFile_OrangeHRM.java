package com.OrangeHRM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Read_PropertyFile_OrangeHRM {
	WebDriver driver = null;
	Properties property = new Properties();

	@Test(priority = 1)
	public void Login() throws InterruptedException, IOException {

		File file = new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\Resource\\ObjectRepository.properties ");
		FileInputStream inStream = new FileInputStream(file);
		property.load(inStream);

		driver.navigate().to(property.getProperty("URL"));

		Common.explicitWaitByID(driver, property.getProperty("Login"), 180);
		driver.findElement(By.name(property.getProperty("UserName"))).sendKeys("Admin");
		driver.findElement(By.name(property.getProperty("Password"))).sendKeys("admin123");
		driver.findElement(By.id(property.getProperty("Login"))).click();
		driver.findElement(By.linkText(property.getProperty("Dashboard"))).isDisplayed();
		System.out.println("Login successs");
	}

	@Test(priority = 5)
	public void Logout() {
		driver.findElement(By.id(property.getProperty("Welcom"))).click();

		// Thread.sleep(2000);
		// Explicit Wait example
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait
				.until(ExpectedConditions.elementToBeClickable(By.linkText(property.getProperty("Logout"))));
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

	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}
}
