package com.qa.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.Config.Endpoint;
import com.qa.pages.CreateAccountPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utlis.DriverFactory;
import com.qa.utlis.ListnerImpl;
import com.qa.utlis.Utilites;

import io.restassured.response.Response;
import junit.framework.Assert;

@Listeners(ListnerImpl.class)
public class FetchDetailsFromAPIAndLogin {

	private static final String LOG_FILE = "log4j.properties";
	static Logger logger = Logger.getLogger(FetchDetailsFromAPIAndLogin.class);
	Properties prop = new Properties();

	private WebDriver driver;
	String verifyLoggedInUser;
	public HomePage hp;
	public CreateAccountPage cap;
	public LoginPage lp;
	Utilites ut;
	Endpoint ep;
	String fName, lName, email, countryName;

	String cityName, stateName, postCode, streetAddress, phoneNumber;
	String expectedTitle = "My Account Magento Commerce - website to practice selenium | demo website for automation testing | selenium practice sites";
	String expectedMsgAfterPurchase = "Thank you for your purchase!";

	WebDriverWait wait;

	@BeforeTest()
	public void setUp() {

		driver = new DriverFactory().getBrowser("Chrome");
		cap = new CreateAccountPage(driver);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		ut = new Utilites();
		ep = new Endpoint();

	}

	@Test(description = "Creating Account using name fetched from API", priority = 0)
	public void fetchFromAPIAndCreateAccount() throws InterruptedException {
		try {
			prop.load(new FileInputStream(LOG_FILE));
			PropertyConfigurator.configure(prop);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Response rs = ep.getRandomUser();
		logger.info(rs.asString());

		fName = rs.jsonPath().get("results[0].name.first");
		logger.info("First Name fetch from API is " + fName);

		lName = rs.jsonPath().get("results[0].name.last");
		logger.info("Last Name fetch from API is " + lName);

		email = rs.jsonPath().getString("results[0].email");
		logger.info("Email Name fetch from API is " + email);

		cityName = rs.jsonPath().getString("results[0].location.city");
		stateName = rs.jsonPath().get("results[0].location.state");
		countryName = rs.jsonPath().get("results[0].location.country");
		postCode = rs.jsonPath().get("results[0].location.postcode").toString();
		streetAddress = rs.jsonPath().get("results[0].location.street.name");
		phoneNumber = rs.jsonPath().get("results[0].phone");

		ut.navigateToURL("https://magento.softwaretestingboard.com", driver);
		logger.debug("opening website");

		/* Click on Create Account link */
		hp.createAccountBtn().click();
		logger.debug("Clicking on Create Account button");

		/* Enter data on Create Account link */
		cap.firstNameField().sendKeys(fName);
		logger.debug("Entering First Name");
		
		cap.lastNameField().sendKeys(lName);
		logger.debug("Entering Last Name");
		
		cap.emailAddressField().sendKeys(email);
		logger.debug("Entering Email Name");
		
		cap.passwordField().sendKeys("Qwerty@123");
		logger.debug("Entering Password ");
		
		cap.confirmPasswordField().sendKeys("Qwerty@123");
		logger.debug("Entering confirm Password ");
		
		cap.loginButton().click();
		logger.debug("Clicking on Login Button");

		/* Verify title on login page */
		logger.debug(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), expectedTitle);

		/* Add item into Cart after clicking on Whats New link */
		lp.whatsNewLink().click();
		ut.mouseHoverOverElement(lp.selectItem(), driver);
		wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lp.addToCart()));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		ut.waitforPageLoad(driver);
		wait.until(ExpectedConditions.elementToBeClickable(lp.selectSize()));
		ut.jsClick(lp.selectSize(), driver);

		wait.until(ExpectedConditions.elementToBeClickable(lp.selectColor()));
		ut.jsClick(lp.selectColor(), driver);
		ut.jsClick(lp.addToCartFinal(), driver);
		ut.longWait(10000);

		/* Verify success msg after adding item into cart */
		logger.debug(lp.successMsg().getText());
		Assert.assertEquals("You added Phoebe Zipper Sweatshirt to your shopping cart.", lp.successMsg().getText());

		wait.until(ExpectedConditions.elementToBeClickable(lp.showCart()));
		lp.showCart().click();

		wait.until(ExpectedConditions.elementToBeClickable(lp.proceedCheckOut()));
		lp.proceedCheckOut().click();

	}

	@Test(description = "Purchase order after adding it to cart", priority = 1)
	public void purchaseOrder() throws InterruptedException {

		ut.waitforPageLoad(driver);

		// StreetAddress
		ut.waitTillVisibilityOfElement(lp.address(), "Street Address", driver);
		lp.address().sendKeys(streetAddress);

		// Country
		lp.selectValueFromDropdown(lp.country(), driver, countryName);

		// State
		lp.selectValueFromDropdown(lp.state(), driver, stateName);

		// City
		lp.city().sendKeys(cityName);

		/* Enter postcode */
		lp.postCode().sendKeys(postCode);

		// PhoneNumber
		lp.phoneNumber().sendKeys(phoneNumber);

		// Click on Next button
		ut.longWait(10000);
		ut.jsClick(lp.nextBtn(), driver);

		/* Place Order */
		// ut.waitforPageLoad(driver);
		// ut.waitTillElementToBeClickable(lp.placeOrder(), driver);
		ut.longWait(15000);
		lp.placeOrder().click();

		/* Verify Success Message */
		// ut.waitTillVisibilityOfElement(lp.orderMsg(), "Order Msg", driver);
		ut.longWait(10000);
		Assert.assertEquals(lp.orderMsg().getText(), expectedMsgAfterPurchase);
		logger.debug(lp.orderMsg().getText());

	}

	@AfterTest
	public void tearDown() {

		driver.quit();
		logger.debug("Closed the browser");

	}
}
