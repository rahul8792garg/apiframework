package com.qa.utlis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.punchh.server.utilities.TestListeners;



public class Utilites {
	
	WebDriver driver;
	static Logger logger = LogManager.getLogger(Utilites.class);
	
	// Scroll to a Element
	
	public void scrollToElement(WebElement ele) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			logger.info("Error in scrolling to element " + e);
		}
	}
	
	// *********** Wait related Utilities **********

	// Implicit wait method
	
	public void waitTillElementToBeClickable(WebElement WebElement) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.elementToBeClickable(WebElement));
		} catch (Exception e) {
			logger.error("WebElement failed to load/appear: " + e);
		//	ListnerImpl.extentTest.get().fail("WebElement failed to load/appear: " + e);
		}
	}
	
	public void waitTillVisibilityOfElement(WebElement element, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			//ListnerImpl.extentTest.get().fail(elementName + " present and webelement is " + element + e);
			logger.error(elementName + " is not present and webelement is " + element);
		}
	}

	// Mouse actions related Utilities

	public void mouseHoverOverElement(WebElement element,WebDriver driver) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			logger.error("Error in mouse hover over element, Exception: " + e);
			//TestListeners.extentTest.get().fail("Error in mouse hover over element, Exception: " + e);
		}
	}
	
	public void navigateToURL(String url) {
		try {
		
			driver.get(url);
			logger.info("Navigated to instance login page");
		
		} catch (Exception e) {
			logger.error("Error in navigating to instance login page: " + e);
		
		}
	}

}
