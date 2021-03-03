package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTTPAuth_Test {
	WebDriver driver;

	@Test
	public void HTTPAuth() throws InterruptedException {
		driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Thread.sleep(15000);
		driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Thread.sleep(5000);
		String confmsg = driver.findElement(By.xpath("//*[@id='content']/div/p")).getText();
		System.out.println(confmsg);

	}

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}
