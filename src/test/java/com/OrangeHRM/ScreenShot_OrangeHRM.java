package com.OrangeHRM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenShot_OrangeHRM {
	WebDriver driver = null;
	Properties property = new Properties();

	String Relativepath_failure = System.getProperty("user.dir") + "\\Screenshot_Failure";
	String filePath_Success = System.getProperty("user.dir") + "\\Screenshot_Success";

	@Test
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

	@Test
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

	@AfterMethod
	public void storeScreenShot(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(Browserscreenshot, new
			// File(Relativepath_failure + "\\Login.png"));
			FileUtils.copyFile(Browserscreenshot, new File(
					Relativepath_failure + "\\" + result.getName() + "_" + System.currentTimeMillis() + ".png"));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(Browserscreenshot, new File(filePath_Success +
			// "\\Login.png"));
			FileUtils.copyFile(Browserscreenshot,
					new File(filePath_Success + "\\" + result.getName() + "_" + System.nanoTime() + ".png"));
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
