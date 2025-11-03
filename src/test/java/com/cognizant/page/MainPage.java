package com.cognizant.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
	WebDriver driver;
	@FindBy(xpath="(//li[@class='css-1dswftd'])[2]")
	WebElement businesspage;
	
	public MainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(this.driver,this);
	}
	
	public ThirdPage businessPage() {
		businesspage.click();
		return new ThirdPage(driver);
	}
}
