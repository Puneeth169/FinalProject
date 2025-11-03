package com.cognizant.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsClass {
	public static ExtentReports reports;
	public static ExtentTest test;
	
	public static void createReport() {
		reports=new ExtentReports();
		ExtentSparkReporter reporter=new ExtentSparkReporter("test-output/projectReport.html");
		reports.attachReporter(reporter);
	}
	
	public static void createTest(String name) {
		test=reports.createTest(name);
	}
	
	public static void close() {
		reports.flush();
	}
}
