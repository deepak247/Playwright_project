package com.qa.opencart.pages;

import java.awt.print.Pageable;
import java.security.PrivateKey;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	private String emailId ="//*[@id='input-email']";
	private String paswordString ="//*[@id='input-password']";
	private String login_btn ="//input[@value='Login']";
	private String forgotpassString = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
	private String logoutLink ="//*[@id=\"column-right\"]/div/a[13]";
	//span[normalize-space()='My Account']
	private String myAccountString ="//span[normalize-space()='My Account']";
	
	//2 page constructor
	
	public LoginPage (Page page) {
		this.page =page;
	}
	
	// 3. page actions/method
	
	
	public String getLogingagetitle() {
		return page.title();
	}
	
	public boolean isforgetpasslinkexist() {
		return page.isVisible(forgotpassString);
	}
	
	public boolean doLogin(String username ,String apppasswordString  ) {
		
		System.out.println("App creds :" + username + ":" + apppasswordString);
		page.fill(emailId, username);
		page.fill(paswordString, apppasswordString);
		page.click(login_btn);
		page.isVisible(logoutLink);
		if(page.isVisible(logoutLink)) {
			System.out.println("Login Succcess");
			return true;
		}
		return false;
		
	}
	
	

}
