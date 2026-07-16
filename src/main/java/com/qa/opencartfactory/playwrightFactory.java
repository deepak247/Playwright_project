package com.qa.opencartfactory;

import java.awt.print.Pageable;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Playwright.CreateOptions;

public class playwrightFactory {
	
	
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;
//	initialize wit thread local 
	
	private static ThreadLocal<Browser> tlBrowser= new ThreadLocal<Browser>();
	private  static ThreadLocal<BrowserContext> tlbrowserContext= new ThreadLocal<>();
	private static ThreadLocal<Page> tlpage= new ThreadLocal<>();
	private static ThreadLocal<Playwright> tlPlaywright= new ThreadLocal<>();
	
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	
	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	
	public static Page getPage() {
		return tlpage.get();
	}
	
	public static BrowserContext getBrowserContext() {
		return tlbrowserContext.get();
	}
	
	public Page initBrowser(Properties prop) {
		
	String browsername=	prop.getProperty("browser").trim();
		System.out.println("browser Name:" + browsername);
		
//		 playwright= Playwright.create();
		tlPlaywright.set(Playwright.create());
		switch (browsername.toLowerCase()) {
		case "chromium":
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "firefox":
			tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "safari":
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			tlBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		case "edge":
			tlBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			break;	

		default:
			System.out.println("please pass the right browser name......");
			break;
		}
		tlbrowserContext.set(getBrowser().newContext());
		tlpage.set(getBrowserContext().newPage());
//		browserContext = browser.newContext();
//		page = browserContext.newPage();
		getPage().navigate(prop.getProperty("Url").trim());
		return getPage();
	}
	/**
	 * this method is used to initialize properties class
	 * @throws IOException 
	 */
	
	public Properties  init_prop() throws IOException {
		FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
		prop= new Properties();
		prop.load(ip);
		
		return prop;
		
		
		
	}
	
	public static String takescreenshot() {
		String path =System.getProperty("user.dir") + "/Screenshot/" + System.currentTimeMillis()+ ".png";
		
		getPage().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get(path))
				.setFullPage(true));
		return path;
		
	}

}
