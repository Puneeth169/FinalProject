package com.cognizant.project;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cognizant.files.ReadExcelData;
import com.cognizant.listener.ListenerClass;
import com.cognizant.page.MainPage;
import com.cognizant.page.Page;
import com.cognizant.page.ThirdPage;

@Listeners(ListenerClass.class)
public class Main extends BaseClass {
	MainPage mainPage;
	Page page;
	ThirdPage businessPage;
	
	@DataProvider(name="ExcelData")
	public Object[][] getData()throws Exception{
		Object[][] data= ReadExcelData.readData("C:\\Users\\2440637\\OneDrive - Cognizant\\Documents\\Excel sheets\\FullData.xlsx");
		return data;
	}
	
	@Test(dataProvider = "ExcelData")
	public void fullTest(ITestContext context ,String ...args) {
		req1(context, args[0]);
		req2();
		String data[]=new String[args.length-1];
		for(int i=1;i<args.length;i++) data[i-1]=args[i];
		req3(context,data);
	}
	
	public void req1(ITestContext context,String course) {
		mainPage=(MainPage)context.getAttribute("MainPage");
		page=mainPage.searchCourses(course);
		page.applyFilters();
		page.displayTopCourses(2);
	}
	
	
	public void req2() {
		page.displayLanguages();
		page.displayLevels();
	}
	
	
	public void req3(ITestContext context,String[] data) {
		try {
			mainPage=page.toMainPage(context);
			businessPage = mainPage.businessPage();
			businessPage.submitForm(data);
			businessPage.displayErrorMsg();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
