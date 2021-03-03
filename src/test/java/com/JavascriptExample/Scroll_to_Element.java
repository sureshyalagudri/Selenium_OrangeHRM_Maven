package com.JavascriptExample;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scroll_to_Element {
	ChromeDriver driver;

	@Test
	public void ByPage() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launch the application
		driver.get("https://stackoverflow.com/");
		Thread.sleep(2000);

		// This will scroll the web page till pixel by pixel.
		js.executeScript("window.scrollTo(0, 1500)");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(1500, 3000)");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(3000, 4500)");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(4500, 6000)");
		Thread.sleep(3000);
		WebElement element = driver.findElementByLinkText("Developer Story");
		element.click();
	}
}
