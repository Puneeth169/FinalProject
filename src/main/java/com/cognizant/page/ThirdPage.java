package com.cognizant.page;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

public class ThirdPage {
	WebDriver driver;
	public String titleText;
	
	@FindBy(tagName="title")
	WebElement title;	
	@FindBy(id = "FirstName")
	WebElement firstName ;
	@FindBy(id = "LastName")
	WebElement lastName;
	@FindBy(id = "Email")
	WebElement email;
	@FindBy(id = "Phone")
	WebElement phoneNo;
	@FindBy(id = "rentalField9")
	WebElement organization;
	@FindBy(id = "Title")
	WebElement jobTitle;
	@FindBy(id = "Company")
	WebElement companyName;
	@FindBy(id = "Employee_Range__c")
	WebElement companySize;
	@FindBy(id = "What_the_lead_asked_for_on_the_website__c")
	WebElement describeNeeds;
	@FindBy(id = "Country")
	WebElement country;
	@FindBy(id = "State")
	WebElement state;
	@FindBy(className = "mktoButton")
	WebElement submitBtn;
	@FindBy(className ="mktoError")
	WebElement errorMsg;
	
	public ThirdPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void submitForm(String[] data) {
		titleText=title.getText();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", firstName);
		firstName.sendKeys(data[0]);
		lastName.sendKeys(data[1]);
		email.sendKeys(data[2]);
		phoneNo.sendKeys(data[3]);
		Select s = new Select(organization);
		s.selectByVisibleText(data[4]);
		jobTitle.sendKeys(data[5]);
		companyName.sendKeys(data[6]);
		Select s1 = new Select(companySize);
		s1.selectByValue(data[7]); 
		Select s2 = new Select(describeNeeds);
		s2.selectByValue(data[8]);
		Select s3 = new Select(country);
		s3.selectByValue(data[9]);
		Select s4 = new Select(state);
		s4.selectByValue(data[10]);
		submitBtn.click();
	}
	public void displayErrorMsg(ITestContext context,int noOfTestCase) {
		System.out.println("Error Message: ");
		context.setAttribute("error", "Error Message: "+errorMsg.getText()+"\n");
		System.out.println(errorMsg.getText());
		try {
	        	TakesScreenshot ts=(TakesScreenshot)driver;
	        	File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
	        	Path path=Paths.get("output/screenshots/error"+noOfTestCase+".png");
	        	Files.copy(screenshotAs.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
	    }
	    catch(Exception e) {
	    		System.out.println(e.getLocalizedMessage());
	    }
	}
}
