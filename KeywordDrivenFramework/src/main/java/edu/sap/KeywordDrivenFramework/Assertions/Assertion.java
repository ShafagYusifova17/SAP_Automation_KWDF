package edu.sap.KeywordDrivenFramework.Assertions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Assertion {
	String userDir = System.getProperty("user.dir");
	WebDriver driver;

	/**
	 * This method is to verify the element presence.
	 * 
	 * @param driver
	 * @param assertionType
	 * @param locatorName
	 * @param locatorType
	 * @author syusifov
	 */
	public void assertElement(WebDriver driver, String assertionType, String locatorName, String locatorType) {
		switch (assertionType) {
		case "Displayed":
			Assert.assertEquals(driver.findElement(getObject(locatorType, locatorName)).isDisplayed(), true);
			break;
		case "Enabled":
			Assert.assertEquals(driver.findElement(getObject(locatorType, locatorName)).isEnabled(), true);
			break;
		case "Selected":
			Assert.assertEquals(driver.findElement(getObject(locatorType, locatorName)).isSelected(), true);
			break;

		}
	}

	/**
	 * This method is to verify text presence
	 * 
	 * @param driver
	 * @param assertionType
	 * @param locatorName
	 * @param locatorType
	 * @param expectedText
	 * @author syusifov
	 */

	public void assertText(WebDriver driver, String assertionType, String locatorName, String locatorType,
			String expectedText) {
		switch (assertionType) {
		case "Eqauls":
			Assert.assertEquals(driver.findElement(getObject(locatorType, locatorName)).getText(), expectedText);
			break;
		case "Contains":
			Assert.assertTrue((driver.findElement(getObject(locatorType, locatorName)).getText()).contains(expectedText));
			break;
		}
	}

	/**
	 * This method is to get the webelEments
	 * 
	 * @param locatorType
	 * @param locatorName
	 * @return WebElements
	 * @author syusifov
	 */

	public By getObject(String locatorType, String locatorName) {
		File file = new File(
				userDir + "src\\main\\java\\edu\\sap\\KeywordDrivenFramework\\ObjectRepo\\ObjectRepo.Properties");
		try {
			FileInputStream fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();

		if (locatorType.equalsIgnoreCase("XPATH")) {
			return By.xpath(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("CssSelector")) {
			return By.cssSelector(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("Id")) {
			return By.id(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("LinkText")) {
			return By.linkText(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("PartialLinkText")) {
			return By.partialLinkText(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("Name")) {
			return By.name(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("ClassName")) {
			return By.className(prop.getProperty(locatorName));
		}

		else if (locatorType.equalsIgnoreCase("TagName")) {
			return By.tagName(prop.getProperty(locatorName));
		}

		else {

			System.out.println("Element not present");
			return null;
		}

	}

}
