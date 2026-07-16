package com.qa.opencart.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;

import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.util.Properties;
import com.microsoft.playwright.Page;
import com.qa.opencart.pages.Homepage;
import com.qa.opencart.pages.LoginPage;

import com.qa.opencartfactory.playwrightFactory;

public class BaseTest {
	
	playwrightFactory pf;
	Page page;
	
	protected Properties prop;
	 protected Homepage homepage;
	 protected LoginPage loginPage;
	
//	@BeforeClass
//	public void setup() throws IOException {
//		
//	  pf = new playwrightFactory();
//	  // initialize and assign properties returned by the factory
//	  prop = pf.init_prop();
//	  page = pf.initBrowser(prop);
//	  homepage = new Homepage(page);
//	  
//	 }
	 
	@Parameters({"browser"})
		@BeforeTest
		public void setup(String browserName) throws IOException {
			pf = new playwrightFactory();

			prop = pf.init_prop();

			if (browserName != null) {
				prop.setProperty("browser", browserName);
			}

			page = pf.initBrowser(prop);
			homepage = new Homepage(page);
		}

	
	
	@AfterClass
	public void teardown() {
		page.context().browser().close();
		
	}

}
