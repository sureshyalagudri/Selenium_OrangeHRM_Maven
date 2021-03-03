package com.OrangeHRM;

import org.testng.annotations.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleProxy_Test {
	WebDriver driver;

	@Test
	public void HTTPAuth() throws InterruptedException {
		System.out.println("Proxy Setting Done");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
	}

	@BeforeTest
	public void LaunchBrowser() {
		// Create proxy class object
		Proxy p = new Proxy();
		// Set HTTP Port to 8080
		p.setHttpProxy("localhost:8080");
		// Create desired Capability object
		DesiredCapabilities cap = new DesiredCapabilities();
		// Pass proxy object p
		cap.setCapability(CapabilityType.PROXY, p);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(cap);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void CloseBrowser() {
		// driver.quit();
	}
}
