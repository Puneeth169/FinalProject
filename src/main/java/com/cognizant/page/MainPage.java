package com.cognizant.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "search-autocomplete-input")
	WebElement searchBox;
	
	@FindBy(xpath="(//li[@class='css-1dswftd'])[2]")
	WebElement businesspage;
	
	public MainPage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(this.driver,this);
	}
 
    public Page searchCourses(String keyword) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.sendKeys(keyword, Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(.,'Beginner')]")));
        return new Page(driver);
    }
 	
	public ThirdPage businessPage() {
		businesspage.click();
		return new ThirdPage(driver);
	}
}
