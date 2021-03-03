package com.OrangeHRM;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Add_Search_Delete_User_from_webtable {
	ChromeDriver driver = null;
	String userName = "";

	@Test(priority=2)
	public void AddUser() throws InterruptedException {
		Thread.sleep(2000);
		
		WebElement admin = driver.findElementById("menu_admin_viewAdminModule");
        Actions action = new Actions(driver);
        action.moveToElement(admin).build().perform();
        WebElement usermanagement = driver.findElementByLinkText("User Management");
        action.moveToElement(usermanagement).build().perform();
        Thread.sleep(2000);
        driver.findElementByLinkText("Users").click();
        driver.findElementById("btnAdd").click();
        
		Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
		SelectPass.selectByValue("1");
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");
		
        Random random = new Random();
        int number = random.nextInt();
        userName = "suresh" + number;
        driver.findElementById("systemUser_userName").sendKeys(userName);
        
//        Select selectStatus = new Select(driver.findElementById("systemUser_status"));
//        selectStatus.selectByVisibleText("Enabled");
        
        driver.findElementById("systemUser_password").sendKeys("Give@5623");
        driver.findElementById("systemUser_confirmPassword").sendKeys("Give@5623");
        Thread.sleep(2000);
        driver.findElementById("btnSave").click();
        
		Thread.sleep(5000);
		//driver.findElementByName("btnDelete").isDisplayed();
		
		// Get the text of new user and compare.
		String name = driver.findElementByXPath("//a[text()='" + userName + "']").getText();
		Assert.assertEquals(name, userName);
		
	}
	
	@Test (priority=3)
	public void DeleteUser() throws InterruptedException{
		// Select the right check box by xpath. 
		// parent and sibling are important here.
		driver.findElementByXPath("//a[text()='" + userName + "']/parent::td/preceding-sibling::td/input").click();

		// This will also work.
		// double dot ".." is to look for anywhere up or down in the sibling.
		//driver.findElementByXPath("//a[text()='" + userName + "']/parent::td/../td/input").click();
		
		// To compare role as admin
		//driver.findElementByXPath("//a[text()='Aparna']/parent::td/../td[text()='Admin']/../td/input").click();
		
		// Delete Added User
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		Thread.sleep(4000);
	}
	
	@Test (priority=1)
	public void Login() throws InterruptedException {
		Common.explicitWaitByID(driver, "btnLogin", 180);
		driver.findElementByName("txtUsername").sendKeys("Admin");
		driver.findElementByName("txtPassword").sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		System.out.println("Login successs");
	}

	@Test(priority=5)
	public void Logout() {
		driver.findElementById("welcome").click();

		// Thread.sleep(2000);
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
