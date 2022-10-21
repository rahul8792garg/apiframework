package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

	WebDriver driver;

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "firstname")
	WebElement firstNameField;

	public WebElement firstNameField() {
		return firstNameField;
	}

	@FindBy(id = "lastname")
	WebElement lastNameField;

	public WebElement lastNameField() {
		return lastNameField;
	}

	@FindBy(id = "email_address")
	WebElement emailAddressField;

	public WebElement emailAddressField() {
		return emailAddressField;
	}

	@FindBy(id = "password")
	WebElement passwordField;

	public WebElement passwordField() {
		return passwordField;
	}

	@FindBy(id = "password-confirmation")
	WebElement confirmPasswordField;

	public WebElement confirmPasswordField() {
		return confirmPasswordField;
	}

	@FindBy(xpath = "//button[@title='Create an Account']")
	WebElement loginButton;

	public WebElement loginButton() {
		return loginButton;
	}

}
