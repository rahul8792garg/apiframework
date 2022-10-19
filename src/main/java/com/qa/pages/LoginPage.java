package com.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	static Logger logger = LogManager.getLogger(LoginPage.class);
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		//This initElements method will create  all WebElements
		PageFactory.initElements(driver, this);
	}

	

	
	@FindBy(xpath="(//*[@class='logged-in'])[1]")
	WebElement verifyNameField;
	public WebElement verifyNameField(){
		 return	verifyNameField;
		}
	
	
	@FindBy(id="ui-id-3")
	WebElement whatsNewLink;
	public WebElement whatsNewLink(){
		 return	whatsNewLink;
		}
	
	@FindBy(xpath="//ol[@class='product-items widget-product-grid']//li")
	WebElement selectItem;
	public WebElement selectItem(){
		 return	selectItem;
		}
	
	
	@FindBy(xpath="(//button[@title='Add to Cart'])[1]")
	WebElement addToCart;
	public WebElement addToCart(){
		 return	addToCart;
		}
	
	public String getNameOfLoggedInUser() throws InterruptedException {
		String str = "";

			try {
				str = verifyNameField.getText();
				System.out.print(str);
				
			} catch (Exception e) {
				
				logger.info("Element is not present or Campaign Name did not matched");
			}
			
		
		return str;
	}
}
