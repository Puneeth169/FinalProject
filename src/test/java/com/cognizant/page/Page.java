package com.cognizant.page;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

public class Page {
	WebDriver driver;
	
	@FindBy(xpath = "//button[@class='css-1b9okar']/child::div[text()='Language']")
	WebElement langDropDown;
	
	@FindBy(xpath="//button[@aria-label='Show more Language options']")
	WebElement showlangOptions;
	
	@FindBy(xpath="//div[@data-testid='search-filter-group-Language']/descendant::span[contains(@id,'cds-react-aria')]")
	List<WebElement> listOfLanguages;
	
	@FindBy(xpath="//button[@class='cds-149 cds-button-disableElevation cds-button-primary css-1fsvlah']")
	WebElement languageViewButton;
	
	@FindBy(xpath="(//button[@class='css-1b9okar'])[6]")
	WebElement levelDropDown;
	
	@FindBy(xpath="//div[@data-testid='search-filter-group-Level']/descendant::span[contains(@id,'cds-react-aria')]")
	List<WebElement> listOfLevels;
	
	@FindBy(xpath="//button[@class='cds-149 cds-button-disableElevation cds-button-primary css-1fsvlah']")
	WebElement levelViewButton;
	
	@FindBy(xpath="//img[@alt='Coursera']")
	WebElement coursera;
	
	public Page(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void displayLanguages() {
		try{
			langDropDown.click();
		}catch(Exception e){
			try {				
				JavascriptExecutor executor=(JavascriptExecutor)driver;
				executor.executeScript("arguments[0].scrollIntoView(true)",showlangOptions);
				showlangOptions.click();
			}
			catch(Exception ex) {
				System.out.println("Cannot detect languages");
			}
		}
		System.out.println("Count of Languages: "+listOfLanguages.size());
		System.out.println("List of Languages: ");
		listOfLanguages.forEach(e->{
			System.out.print("-->"+(e.getText().split(" "))[0]+" ");
		});
		System.out.println();
		try {
			languageViewButton.click();			
		}catch(Exception e) {}
	}
	
	public void displayLevels() {
		try{
			levelDropDown.click();
		}catch(Exception e){}
		System.out.println("Count of Levels: "+listOfLevels.size());
		System.out.println("List of Levels:");
		listOfLevels.forEach(e->{
			System.out.print("-->"+(e.getText().split(" "))[0]+" ");
		});
		System.out.println();
		try {
			levelViewButton.click();			
		}
		catch(Exception e) {}
	}
	
	public MainPage toMainPage(ITestContext context) {
		coursera.click();
		return (MainPage)context.getAttribute("MainPage");
	}

}
