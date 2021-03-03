package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {

	public static WebElement GetElement(ChromeDriver driver, String elementText) {
		WebElement element = null;

		try {
			element = driver.findElementById(elementText);
		} catch (Exception e) {
			try {
				element = driver.findElementByLinkText(elementText);
			} catch (Exception e1) {
				try {
					element = driver.findElementByName(elementText);
				} catch (Exception e2) {
					try {
						element = driver.findElementByCssSelector(elementText);
					} catch (Exception e3) {
						try {
							element = driver.findElementByXPath(elementText);
						} catch (Exception e4) {
							element = null;
						}
					}
				}
			}
		}
		return element;
	}

	public static WebElement GetElement(WebDriver driver, String elementText) {
		WebElement element = null;

		try {
			element = driver.findElement(By.id(elementText));
		} catch (Exception e) {
			try {
				element = driver.findElement(By.linkText(elementText));
			} catch (Exception e1) {
				try {
					element = driver.findElement(By.name(elementText));
				} catch (Exception e2) {
					try {
						element = driver.findElement(By.cssSelector(elementText));
					} catch (Exception e3) {
						try {
							element = driver.findElement(By.xpath(elementText));
						} catch (Exception e4) {
							element = null;
						}
					}
				}
			}
		}
		return element;
	}
	
	public static WebElement explicitWaitByID(ChromeDriver driver, String text, int seconds) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.id(text)));
			return element;
		} catch (Exception e) {
			element = null;
		}
		return element;
	}

	public static WebElement explicitWaitByID(WebDriver driver, String text, int seconds) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.id(text)));
			return element;
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
	
	public static WebElement explicitWaitByLinkText(ChromeDriver driver, String text, int seconds) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
			return element;
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
	
	public static WebElement explicitWaitByXPath(ChromeDriver driver, String text, int seconds) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
			return element;
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
	
	public static WebElement explicitWaitByLinkText(WebDriver driver, String text, int seconds) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
			return element;
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
	
	public static WebElement explicitWaitByXPath(WebDriver driver, String text, int seconds) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
			return element;
		} catch (Exception e) {
			element = null;
		}
		return element;
	}
}
