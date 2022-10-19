package com.qa.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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




@Listeners(ListnerImpl.class)
public class IframeSignUpAndLoginTest {
	static Logger logger = LogManager.getLogger(IframeSignUpAndLoginTest.class);
	private WebDriver driver;
	String verifyLoggedInUser;
	public HomePage hp;
	public CreateAccountPage cap;
	public LoginPage lp;
	Utilites ut;
	Endpoint ep;
	String fName;
	String lName;
	String email;
	
	@BeforeTest()
	public void setUp() {
	
	
		driver = new DriverFactory().getBrowser("Chrome");
		cap= new CreateAccountPage(driver);
		hp= new HomePage(driver);
		lp= new LoginPage(driver);
		ut=new Utilites();
		ep=new Endpoint();

	}

	@Test(description="Creating Account using name fetched from API")
	public void fetchFromAPIAndCreateAccount() throws InterruptedException {
	
	//driver.get("https://magento.softwaretestingboard.com");
	Response rs= ep.getRandomUser();
	System.out.println(rs.asString());
	fName = rs.jsonPath().get("results[0].name.first");
	lName = rs.jsonPath().get("results[0].name.last");	
	email = rs.jsonPath().getString("results[0].email");
	ut.navigateToURL("https://magento.softwaretestingboard.com");	
	

	hp.createAccountBtn().click();
	
	cap.firstNameField().sendKeys(fName);
	cap.lastNameField().sendKeys(lName);
	cap.emailAddressField().sendKeys(email);
	cap.passwordField().sendKeys("Qwerty@123");
	cap.confirmPasswordField().sendKeys("Qwerty@123");
	cap.loginButton().click();
	
	lp.getNameOfLoggedInUser();
	
	//ut.waitTillVisibilityOfElement(lp.verifyNameField(), "Logged in username");
	//verifyLoggedInUser=lp.verifyNameField().getText();
//	System.out.print(verifyLoggedInUser);
	lp.whatsNewLink().click();
	
	ut.mouseHoverOverElement(lp.selectItem(),driver);
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lp.addToCart())); 
	((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

	Thread.sleep(30000);

	}

	@AfterTest
	public void tearDown() {
	
		driver.quit();
	}
}
