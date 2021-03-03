package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import static org.junit.Assert.fail;

import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.text.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;

public class Windchill_Test {
	WebDriver driver = null;

	@Test
	public void OpenInCreoView() {
		try {
			// Wait for search field to appear.
			Common.explicitWaitByID(driver, "gloabalSearchField", 180);

			//
			driver.findElement(By.id("gloabalSearchField")).sendKeys("ly100787.asm");
			driver.findElement(By.xpath("//img[@id='ext-gen29']")).click();

			// Wait for search results to appear.
			Common.explicitWaitByID(driver, "saveThisSearchLabel", 180);
			driver.findElement(By.cssSelector("img[src='netmarkets/images/details.gif']")).click();
			// Thread.sleep(10000);
			// driver.findElementByName("Actions").click();

			Common.explicitWaitByLinkText(driver, "Structure", 180);

			// Click on Actions menu.
			driver.findElement(By
					.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[4]/form[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[2]/td[2]/em[1]/button[1]"))
					.click();

			// GetElement("Actions").click();

			// Click on "Open In"
			// driver.findElementByXPath("//a[@id='object_epm docs actions
			// open_infoPagedetailsPageActionsMenu_menu']")
			// .click();

			Common.explicitWaitByLinkText(driver, "Open In", 180);

			WebElement openInElement = Common.GetElement(driver, "Open In");
			Actions action = new Actions(driver);
			action.moveToElement(openInElement).build().perform();
			Thread.sleep(1000);
			// Click on "Open in Creo View" menu.
			Common.GetElement(driver, "Open in Creo View").click();
			System.out.println("Done with open in creo view");

			Thread.sleep(2000);

			// String text = driver.switchTo().alert().getText();
			// driver.switchTo().alert().accept();
			sikuliTest();
//			//
//			Robot robot = new Robot();
//			robot.delay(1000);
//			robot.keyPress(KeyEvent.VK_TAB);
//			robot.keyPress(KeyEvent.VK_ENTER);
//
//			robot.keyRelease(KeyEvent.VK_TAB);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//			robot.delay(1000);

			// // Get Structure tab to appear
			// Common.explicitWaitByLinkText(driver, "Structure", 180);
			// WebElement structure = Common.GetElement(driver, "Structure");
			// structure.click();
			//
			// // Wait for structure tab to load.
			// // Added sleep so that frame can switch correctly.
			// Thread.sleep(5000);
			// driver.switchTo().frame(1);
			// String savedFilters = "//button[text()='Saved Filters']";
			// Common.explicitWaitByXPath(driver, savedFilters, 240);
			//
			// // Click on Saved filters button.
			// Common.GetElement(driver, savedFilters).click();

			System.out.println("Windchill test is Done!");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void sikuliTest() {
		try {
			String filePath = System.getProperty("user.dir") + "\\Sikuli_Objects\\";
			Screen screen = new Screen();
			Pattern openButton = new Pattern(filePath + "Windchill.png");

			screen.wait(openButton, 20);
			screen.click(openButton);
			
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@BeforeMethod
	public void beforeMethod() {
		// Launch Chrome browser.
		// Using web driver so that it automatically sets required
		WebDriverManager.chromedriver().setup();

		// This chrome option is to set headless mode.
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);

		driver = new ChromeDriver(options);
		// WebDriverManager.edgedriver().setup();
		// driver = new EdgeDriver();

		driver.manage().window().maximize();
		driver.navigate().to("http://agwcqa2.tal.deere.com/Windchill/");
	}

	@AfterMethod
	public void afterMethod() {
		if (driver != null) {
			driver.quit();
			// driver.close();
		}
	}

}
