package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.ApplicationConstants;


public class HomeTest  extends BaseTest{
	

	 
	@Test(priority = 1)
	public void homePageTitleTest() {
		String actualTitle =homepage.getHomepageTitle();
		Assert.assertEquals(actualTitle, ApplicationConstants.HOME_PAGE_TITLE);
		
	}
	
	@Test(priority = 2)
	public void homePageUrlTest() {
		String actualUrl =homepage.getHomepageUrl();
		Assert.assertEquals(actualUrl, prop.getProperty("Url"));
		
	}
	
	@Test(priority = 3,dataProvider = "Getdatprovider")
	public void homePageHeaderTest(String product) {
		String Header =homepage.doSearch(product);
		Assert.assertEquals(Header, "Search - " + product);
		
	}
	
  @DataProvider
  public Object[][] Getdatprovider(){
	  return  new Object[][] {
		  {"mackbook"},{"imac"}
		  
	  };
  }
	

}
