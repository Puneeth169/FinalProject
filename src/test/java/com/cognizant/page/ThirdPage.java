package com.cognizant.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ThirdPage {
	WebDriver driver;
	
	public ThirdPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
