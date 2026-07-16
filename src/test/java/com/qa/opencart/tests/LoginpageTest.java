package com.qa.opencart.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ApplicationConstants;
import com.qa.opencart.pages.Homepage;

public class LoginpageTest extends BaseTest {
	
	
	@Test(priority = 1)
	public void LoginpageNavigationTest() {
		loginPage=	homepage.navigateToLoginpage();
		String actuaTitleString =loginPage.getLogingagetitle();
		Assert.assertEquals(actuaTitleString, ApplicationConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void checkforgetpassTest() {
		Assert.assertTrue(loginPage.isforgetpasslinkexist());
		
	}
	
	@Test(priority = 3)
	public  void appLoginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("Username").trim(),prop.getProperty("password").trim()));
		
	}

}
