package com.cognizant.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.cognizant.report.ReportsClass;

public class ListenerClass implements ITestListener,IInvokedMethodListener {
	private int count=1;
	
	@Override
	public void onStart(ITestContext context) {
		ReportsClass.createReport();
	}
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if(testResult.getMethod().getMethodName().equals("fullTest")) {
			ReportsClass.createTest(testResult.getMethod().getMethodName());
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		if(result.getMethod().getMethodName().equals("fullTest")) {
			ReportsClass.test.log(Status.PASS, "Test Passed with test case "+count++);
			WebDriver driver =(WebDriver) result.getTestContext().getAttribute("driver");
			TakesScreenshot takeScreenshot=(TakesScreenshot)driver;
			String screenshot = takeScreenshot.getScreenshotAs(OutputType.BASE64);
			ReportsClass.test.addScreenCaptureFromBase64String(screenshot);	
		}
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		if(result.getMethod().getMethodName().equals("fullTest")) {
			ReportsClass.test.log(Status.FAIL, "Test has Failed");
		}
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ReportsClass.close();
	}
}
