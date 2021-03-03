package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowser_Testing extends OrangeHRMTestData {
	WebDriver driver = null;

	@Test(dataProvider = "Login")
	public void LoginToOrangeHRM(String userName, String password) throws InterruptedException {

		// Wait until page loads.
		Common.explicitWaitByID(driver, "btnLogin", 180);

		driver.findElement(By.name("txtUsername")).clear();
		driver.findElement(By.name("txtUsername")).sendKeys(userName);
		driver.findElement(By.name("txtPassword")).clear();
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		System.out.println("Login successs");

		Thread.sleep(2000);

		Logout();
	}

	// @Test(priority = 2)
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

	// @Test(priority = 1)
	// public void Login() throws InterruptedException {
	// Common.explicitWaitByID(driver, "btnLogin", 180);
	// driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	// driver.findElement(By.name("txtPassword")).sendKeys("admin123");
	// driver.findElement(By.id("btnLogin")).click();
	// driver.findElement(By.linkText("Dashboard")).isDisplayed();
	// System.out.println("Login successs");
	// }
	//
	// @Test(priority = 2)
	// public void Logout() {
	// driver.findElement(By.id("welcome")).click();
	//
	// // Explicit Wait example
	// WebDriverWait wait = new WebDriverWait(driver, 60);
	// WebElement element =
	// wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
	// String ActText = element.getText();
	// System.out.println(ActText);
	// element.click();
	// }

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// This chrome option is to set headless mode.
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(false);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

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
