package com.SingleExtentReportExample;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class Drag_Drop_Test {
	WebDriver driver = null;

	@Test
	public void DragTest() throws InterruptedException {
		Thread.sleep(3000);

		// Drag and drop can be done using actions class

		Actions actions = new Actions(driver);

		WebElement dragElement = driver.findElement(By.id("draggable"));
		WebElement dropElement = driver.findElement(By.id("droppable"));

		actions.dragAndDrop(dragElement, dropElement).build().perform();
		System.out.println("Drag and drop");
		Thread.sleep(5000);

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
		driver.navigate().to("https://jqueryui.com/resources/demos/droppable/default.html");
	}

	@AfterTest
	public void afterTest() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}

}
