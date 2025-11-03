package com.cognizant.project;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

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
	
	@BeforeTest
	public void createDriver(ITestContext context) {
		Properties props=loadPropFile();
		String browserName=props.getProperty("driver.name");
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
	
	@AfterTest
	public void close() {
		try {			
			driver.quit();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
