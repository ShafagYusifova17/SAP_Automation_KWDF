package edu.sap.KeywordDrivenFramework.Keywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Keywords {
	String userDir = System.getProperty("user.dir");
	WebDriver driver;

	/**
	 * This method is used to navigate to given url
	 * 
	 * @param driver
	 * @param url
	 * @author syusifov
	 */
	public void enter_URL(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * This method is to locate the element
	 * 
	 * @param driver
	 * @param locatorType
	 * @param locatorName
	 * @param testData
	 * @author syusifov
	 */
	public void type(WebDriver driver, String locatorType, String locatorName, String testData) {
		driver.findElement(this.getObject(locatorType, locatorName)).sendKeys(testData);
	}

/**
 * This method is to locating the elements
 * @param locatorType
 * @param locatorName
 * @return
 * @author syusifov
 */


public By getObject(String locatorType, String locatorName){
	File file = new File(userDir+"src\\main\\java\\edu\\sap\\KeywordDrivenFramework\\ObjectRepo\\ObjectRepo.Properties");
	try {
		FileInputStream fileInput = new FileInputStream(file);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	Properties prop = new Properties();
	
	if(locatorType.equalsIgnoreCase("XPATH")) {
		return By.xpath(prop.getProperty(locatorName));
	}
	
	else if(locatorType.equalsIgnoreCase("CssSelector")) {
		return By.cssSelector(prop.getProperty(locatorName));
	}
	
	else if(locatorType.equalsIgnoreCase("Id")) {
		return By.id(prop.getProperty(locatorName)); 
	}
	 
	else if(locatorType.equalsIgnoreCase("LinkText")) {
		return By.linkText(prop.getProperty(locatorName)); 
	}
	
	else if(locatorType.equalsIgnoreCase("PartialLinkText")) {
		return By.partialLinkText(prop.getProperty(locatorName)); 
	}
	
	else if(locatorType.equalsIgnoreCase("Name")) {
		return By.name(prop.getProperty(locatorName));
	}
	
	else if(locatorType.equalsIgnoreCase("ClassName")) {
		return By.className(prop.getProperty(locatorName));
	}
	
	else if(locatorType.equalsIgnoreCase("TagName")) {
		return By.tagName(prop.getProperty(locatorName));
	}
	
	else {
		
		 System.out.println ("Element not present");
		 return null;
	}
	
}

	/**
	 * This method is to click the elements
	 * 
	 * @param driver
	 * @param locatorType
	 * @param locatorName
	 * @param testData
	 * @author syusifov
	 */

	public void click(WebDriver driver, String locatorType, String locatorName, String testData) {
		driver.findElement(this.getObject(locatorType, locatorName)).click();
	}



}
