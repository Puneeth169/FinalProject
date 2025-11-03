package com.cognizant.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
	WebDriver driver;
	
	public MainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver,this);
	}
	
//	public ThirdPage businessPage() {
//		businesspage.click();
//		return new ThirdPage(driver);
//	}
}
