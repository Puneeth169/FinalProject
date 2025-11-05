package com.cognizant.project;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.cognizant.exception.UnsupportedBrowserException;
import com.cognizant.page.MainPage;

public class BaseClass {
	public WebDriver driver;
	
	public static Properties loadPropFile() {
		try {
			FileReader read=new FileReader("config.properties");
			Properties props=new Properties();
			props.load(read);
			return props;
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}
	
	@BeforeMethod
	public void createDriver(ITestContext context) {
		Properties props=loadPropFile();
		String browserName="";
		if(Math.round(Math.random())==0){
			browserName=props.getProperty("driver.edge");
		}
		else {
			browserName=props.getProperty("driver.chrome");
		}
		String url=props.getProperty("driver.url");
		try {
			if(browserName.equals("chrome")) {
				driver=new ChromeDriver();
			}
			else if(browserName.equals("edge")) {
				driver=new EdgeDriver();
			}
			else {
				throw new UnsupportedBrowserException("The browser is currently not supported");
			}
			driver.manage().window().maximize();
			driver.get(url);
			context.setAttribute("driver", driver);
			MainPage page=new MainPage(driver);
			context.setAttribute("MainPage", page);
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	@AfterMethod
	public void close() {
		try {			
			driver.quit();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
