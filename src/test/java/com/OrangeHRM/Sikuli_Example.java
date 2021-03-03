package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sikuli_Example {
	WebDriver driver;

	@Test
	public void fileUploadUsingSikuli() throws InterruptedException, FindFailed {

		String filePath = System.getProperty("user.dir") + "\\Sikuli_Objects\\";

		Screen screen = new Screen();
		Pattern fileInputTextBox = new Pattern(filePath + "FileTextBox.PNG");
		Pattern openButton = new Pattern(filePath + "OpenButton.png");

		driver.get("https://gofile.io/uploadFiles");
		Thread.sleep(20000);

		// Click on Browse button and handle windows pop up using Sikuli
		driver.findElement(By.xpath("//button[@id='dropZoneBtnSelect']")).click();
		screen.wait(fileInputTextBox, 20);
		screen.type(fileInputTextBox, filePath + "image_002.png");
		screen.click(openButton);
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
