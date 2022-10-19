package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	@FindBy(xpath="//div[@class='panel header']//a[contains(text(),'Create an Account')]")
	WebElement createAccountLink;
	
	
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}
	
	//return webElement
		public WebElement createAccountBtn(){
		 return	createAccountLink;
		}
		

}


