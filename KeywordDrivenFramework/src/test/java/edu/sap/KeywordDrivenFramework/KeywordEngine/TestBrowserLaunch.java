package edu.sap.KeywordDrivenFramework.KeywordEngine;

import edu.sap.KeywordDrivenFramework.Base.Base;
import edu.sap.KeywordDrivenFramework.Base.EnvinormentConfiguration;

import org.junit.Test;
import org.openqa.selenium.*;
import org.testng.*;
//import org.testng.annotations.Test;


public class TestBrowserLaunch extends Base{
	WebDriver driver;
	
	//@Test
	public void launchBrowser() {
	
	Base baseObj = new Base();
	EnvinormentConfiguration enviObj= new EnvinormentConfiguration();
	String browser = enviObj.getEnvProperty("browserName");
	driver = baseObj.initDriver(browser);
	
	}

}
