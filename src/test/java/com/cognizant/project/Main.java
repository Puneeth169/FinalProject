package com.cognizant.project;

import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.cognizant.files.ReadExcelData;
import com.cognizant.files.WriteExcelData;
import com.cognizant.listener.ListenerClass;
import com.cognizant.page.MainPage;
import com.cognizant.page.Page;
import com.cognizant.page.ThirdPage;
import com.cognizant.report.ReportsClass;

@Listeners(ListenerClass.class)
public class Main extends BaseClass {
	MainPage mainPage;
	Page page;
	ThirdPage businessPage;
	private int count=1;
	
	@DataProvider(name="ExcelData")
	public Object[][] getData()throws Exception{
		Object[][] data= ReadExcelData.readData("src/test/resources/FullData.xlsx");
		return data;
	}
	
	@Test(dataProvider = "ExcelData")
	public void fullTest(ITestContext context ,String ...args) {
		ReportsClass.test.log(Status.INFO, "Driver successfully created and navigated to the URL");
		req1(context, args[0]);
		req2();
		String data[]=new String[args.length-1];
		for(int i=1;i<args.length;i++) data[i-1]=args[i];
		req3(context,data);
		List<String> outputdata =(List<String>)context.getAttribute("outputdata");
		outputdata.add((String)context.getAttribute("error"));
		WriteExcelData.writeData(outputdata,count);
		ReportsClass.test.log(Status.INFO, "Output Data for Test Case "+count+" has been written into the Excel File");
	}
	
	public void req1(ITestContext context,String course) {
		mainPage=(MainPage)context.getAttribute("MainPage");
		page=mainPage.searchCourses(course);
		page.applyFilters();
		page.displayTopCourses(2,count);
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
			businessPage.displayErrorMsg(context,count++);
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
