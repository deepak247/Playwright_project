package com.qa.opencart.pages;

// ...existing code...
import com.qa.opencart.pages.LoginPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Homepage {
	  private Page page;
//	1. String Locators - OR
	
	private String search ="input[name='search']";
	private String searchIconString ="div#search button";
	private String  searchpageHeaderString ="div#content h1";
	private String loginLink  ="//a[normalize-space()='Login']";
	private String myAccountString ="//span[normalize-space()='My Account']";
	
	
	
	//2. page constructor:
	
	
	public Homepage(Page page) {
		
		this.page =page;
		
	}
	
	//3. page actions/methos:
	
	public String getHomepageTitle(){
		String titleString = page.title();
		System.out.println("Title :" +titleString );
		return titleString;
		
	}
	
	public String getHomepageUrl() {
		String url= page.url();
		System.out.println("Url :" +url );
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(search,productName);
		page.click(searchIconString);
//		page.textContent(searchpageHeaderString);
		
		String header = page.locator(searchpageHeaderString).textContent();
		System.out.println("HEADER" + header);
		return header;
	}
	
	public LoginPage navigateToLoginpage() {
		page.click(myAccountString);
		page.click(loginLink);
		return new LoginPage(page);
		
	}

}
