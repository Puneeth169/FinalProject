package com.cognizant.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

public class Page {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//label[contains(.,'Beginner')]")
	WebElement beginnerLevel;
	
	@FindBy(xpath = "//label[contains(.,'English')]")
	WebElement englishLanguage;
	
	@FindBy(css = ".cds-ProductCard-gridCard")
	List<WebElement> courseCards;
	
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
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	public void applyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(beginnerLevel)).click();
        wait.until(ExpectedConditions.elementToBeClickable(englishLanguage)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cds-ProductCard-gridCard")));
    }
 
    public void displayTopCourses(int count) {
        for (int i = 0; i < Math.min(count, courseCards.size()); i++) {
            WebElement course = courseCards.get(i);
            String title = course.findElement(By.cssSelector(".cds-CommonCard-title")).getText();
            String hours = course.findElement(By.className("cds-CommonCard-metadata")).getText();
            String rating = course.findElement(By.cssSelector(".cds-RatingStat-meter")).getText();
 
            System.out.println("Course " + (i + 1) + ":");
            System.out.println("Title: " + title);
            System.out.println(hours);
            System.out.println("Rating: " + rating);
        }
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
