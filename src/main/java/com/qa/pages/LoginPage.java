package com.qa.pages;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

	static Logger logger = Logger.getLogger(LoginPage.class);
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//*[@class='logged-in'])[1]")
	WebElement verifyNameField;

	public WebElement verifyNameField() {
		return verifyNameField;
	}

	@FindBy(id = "ui-id-3")
	WebElement whatsNewLink;

	public WebElement whatsNewLink() {
		return whatsNewLink;
	}

	@FindBy(xpath = "//ol[@class='product-items widget-product-grid']//li")
	WebElement selectItem;

	public WebElement selectItem() {
		return selectItem;
	}

	@FindBy(xpath = "(//button[@title='Add to Cart'])[1]")
	WebElement addToCart;

	public WebElement addToCart() {
		return addToCart;
	}

	@FindBy(xpath = "//*[@option-label='XS']")
	WebElement selectSize;

	public WebElement selectSize() {
		return selectSize;
	}

	@FindBy(xpath = "//*[@id='option-label-color-93-item-52']")
	WebElement selectColor;

	public WebElement selectColor() {
		return selectColor;
	}

	@FindBy(xpath = "//*[@title='Add to Cart']")
	WebElement addToCartFinal;

	public WebElement addToCartFinal() {
		return addToCartFinal;
	}

	@FindBy(xpath = "//a[@class='action showcart']")
	WebElement showCart;

	public WebElement showCart() {
		return showCart;
	}

	@FindBy(xpath = "//*[@id='top-cart-btn-checkout']")
	WebElement proceedCheckOut;

	public WebElement proceedCheckOut() {
		return proceedCheckOut;
	}

	@FindBy(xpath = "//*[@name='firstname']")
	WebElement firstName;

	public WebElement firstName() {
		return firstName;
	}

	@FindBy(xpath = "//*[@name='lastname']")
	WebElement lastName;

	public WebElement lastName() {
		return lastName;
	}

	@FindBy(xpath = "//*[@name='street[0]']")
	WebElement address;

	public WebElement address() {
		return address;
	}

	@FindBy(xpath = "//*[@name='country_id']")
	WebElement country;

	public WebElement country() {
		return country;
	}

	@FindBy(xpath = "//*[@name='region_id']")
	WebElement state;

	public WebElement state() {
		return state;
	}

	@FindBy(xpath = "//*[@name='city']")
	WebElement city;

	public WebElement city() {
		return city;
	}

	@FindBy(xpath = "//*[@name='postcode']")
	WebElement postCode;

	public WebElement postCode() {
		return postCode;
	}

	@FindBy(xpath = "//*[@name='telephone']")
	WebElement phoneNumber;

	public WebElement phoneNumber() {
		return phoneNumber;
	}

	@FindBy(xpath = "//*[@data-role='opc-continue']")
	WebElement nextBtn;

	public WebElement nextBtn() {
		return nextBtn;
	}

	@FindBy(xpath = "//*[@data-ui-id='message-success']")
	WebElement successMsg;

	public WebElement successMsg() {
		return successMsg;
	}

	@FindBy(xpath = "//*[@title='Place Order']")
	WebElement placeOrder;

	public WebElement placeOrder() {
		return placeOrder;
	}

	@FindBy(xpath = "//*[@data-ui-id='page-title-wrapper']")
	WebElement orderMsg;

	public WebElement orderMsg() {
		return orderMsg;
	}

	public String getNameOfLoggedInUser() throws InterruptedException {
		String str = "";

		try {
			str = verifyNameField.getText();
			System.out.print(str);

		} catch (Exception e) {

			logger.info("Element is not present");
		}
		return str;
	}

	public void selectValueFromDropdown(WebElement e, WebDriver driver, String text) throws InterruptedException {

		try {
			Select s = new Select(e);
			s.selectByVisibleText(text);
		} catch (Exception ex) {

			logger.info("Element is not present");
		}

	}

}
