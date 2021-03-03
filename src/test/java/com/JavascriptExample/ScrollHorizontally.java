package com.JavascriptExample;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollHorizontally {
	ChromeDriver driver;
    @Test
    public void ScrollHorizontally() throws InterruptedException {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Launch the application		
        driver.get("https://stackoverflow.com/");
        		
        WebElement Element = driver.findElementByLinkText("Developer Story");
        Thread.sleep(2000);
        //This will scroll the page Horizontally till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(2000);
        Element.click();
    }
}
