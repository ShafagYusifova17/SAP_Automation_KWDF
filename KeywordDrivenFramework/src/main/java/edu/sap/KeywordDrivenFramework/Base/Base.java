package edu.sap.KeywordDrivenFramework.Base;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Properties;
import java.io.*;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

public class Base{

	public WebDriver driver;
	public static Properties prop;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	String dirPath = System.getProperty("user.dir");

	/**
	 * This method is to initialize the webDriver
	 * 
	 * @param browserName
	 * @return WebDriver
	 * @author syusifov
	 */
	public WebDriver initDriver(String browserName) {
		String fullPath = dirPath + "\\src\\main\\java\\edu\\sap\\KeywordDrivenFramework\\Drivers\\";
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", fullPath + "chromedriver.exe");
			driver = new ChromeDriver();
		}

		else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", fullPath + "geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}
	/**
	 * This method is to initialize properties
	 * @return Properties
	 * @author syusifov
	 */
	public Properties initProperties() {
		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream(dirPath + "\\src\\main\\java\\edu\\sap\\KeywordDrivenFramework\\Resources\\config.properties");
			prop.load(ip);
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	/**
	 * This method is to initialize extent report
	 * @author syusifov
	 */
	public ExtentReports intiExtentReport() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());	
        String path = dirPath+ "\\src\test\\java\\edu\\sap\\KeywordDrivenFramework\\KeywordEngine\\TestResult\\AutomationResult_"+date+".html";
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);	
		return extentReports;		
	}

}
