package com.qa.utlis;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilites {

	WebDriver driver;
	static Logger logger = Logger.getLogger(Utilites.class);

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
			// ListnerImpl.extentTest.get().fail("WebElement failed to load/appear: " + e);
		}
	}

	public void waitTillVisibilityOfElement(WebElement element, String elementName, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// ListnerImpl.extentTest.get().fail(elementName + " present and webelement is "
			// + element + e);
			logger.error(elementName + " is not present and webelement is " + element);
		}
	}

	// Mouse actions related Utilities

	public void mouseHoverOverElement(WebElement element, WebDriver driver) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			logger.error("Error in mouse hover over element, Exception: " + e);
			// TestListeners.extentTest.get().fail("Error in mouse hover over element,
			// Exception: " + e);
		}
	}

	public void navigateToURL(String url, WebDriver driver) {
		try {

			driver.get(url);
			logger.info("Navigated to instance login page");

		} catch (Exception e) {
			logger.error("Error in navigating to instance login page: " + e);

		}
	}

	public void waitforPageLoad(WebDriver driver) {
		try {
			new WebDriverWait(driver, 300).until(webDriver -> ((JavascriptExecutor) webDriver)
					.executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			logger.error("Error in waitng for page load" + e);
		}
	}

	public void jsClick(WebElement element, WebDriver driver) {
		if (element.isDisplayed()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public void longWait(long waitDuration) {

		try {
			Thread.sleep(waitDuration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitTillElementToBeClickable(WebElement WebElement, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(WebElement));
		} catch (Exception e) {
			logger.error("WebElement failed to load/appear: " + e);

		}
	}

}
