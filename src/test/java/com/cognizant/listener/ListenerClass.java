package com.cognizant.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.cognizant.report.ReportsClass;

public class ListenerClass implements ITestListener {
		
	@Override
	public void onStart(ITestContext context) {
		ReportsClass.createReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ReportsClass.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		ReportsClass.test.log(Status.PASS, "Test Passed");
		WebDriver driver =(WebDriver) result.getTestContext().getAttribute("driver");
		TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
		String screenshot = takeScreenshot.getScreenshotAs(OutputType.BASE64);
		ReportsClass.test.addScreenCaptureFromBase64String(screenshot);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ReportsClass.test.log(Status.FAIL, "Test has Failed");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ReportsClass.close();
	}
}
