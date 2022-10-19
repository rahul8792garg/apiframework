package com.qa.utlis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;



// The Class BrowserUtilities.
@Listeners(ListnerImpl.class)
public class DriverFactory {


	//private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	public WebDriver driver;
	
		static Logger logger = LogManager.getLogger(DriverFactory.class);
		public  WebDriver getBrowser(String browserName) {
		

			switch (browserName) {
			case "Firefox":
			//	driver = drivers.get("Firefox");
				if (driver == null) {
					driver = new FirefoxDriver();
					//drivers.put("Firefox", driver);
				}
				break;

			case "Chrome":
				//driver = drivers.get("Chrome");
				if (driver == null) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					driver.manage().deleteAllCookies();
				//	drivers.put("Chrome", driver);
				}
				break;
			}
			return driver;
		}
	}
	

	
