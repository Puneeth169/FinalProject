package com.cognizant.project;

import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cognizant.listener.ListenerClass;
import com.cognizant.page.MainPage;
import com.cognizant.page.Page;
import com.cognizant.page.ThirdPage;

@Listeners(ListenerClass.class)
public class Main extends BaseClass {
	MainPage mainPage;
	Page page;
	ThirdPage businessPage;
	
	@Test
	public void req1(ITestContext context) {
		mainPage=(MainPage)context.getAttribute("MainPage");
		page=mainPage.searchCourses("Web Development");
		page.applyFilters();
		page.displayTopCourses(2);
	}
	
	@Test
	public void req2(ITestContext context) {
		page.displayLanguages();
		page.displayLevels();
	}
	
	@Test
	public void req3(ITestContext context) {
		try {
			mainPage=page.toMainPage(context);
			businessPage = mainPage.businessPage();
			businessPage.submitForm();
			businessPage.displayErrorMsg();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
