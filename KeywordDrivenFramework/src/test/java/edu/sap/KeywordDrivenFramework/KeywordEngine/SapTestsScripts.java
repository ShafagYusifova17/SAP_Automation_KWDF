package edu.sap.KeywordDrivenFramework.KeywordEngine;

import org.openqa.selenium.*;

import com.aventstack.extentreports.ExtentReports;

import org.junit.*;
import edu.sap.KeywordDrivenFramework.Keywords.*;
import edu.sap.KeywordDrivenFramework.Assertions.*;
import edu.sap.KeywordDrivenFramework.Base.Base;
import edu.sap.KeywordDrivenFramework.Base.EnvinormentConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SapTestsScripts {
	WebDriver driver;
	String dirPath = System.getProperty("user.dir");
	Keywords keyword = new Keywords();
	Assertion assertion = new Assertion();
	Base baseObj = new Base();
	EnvinormentConfiguration enviObj= new EnvinormentConfiguration();
	String browser = enviObj.getEnvProperty("browserName");
	ExtentReports extent;
	
	
	@Test
	public void readExcelAndExecute() throws IOException {
		
		FileInputStream excel = null;
		String excelSheetPath = dirPath
				+ "\\src\\test\\java\\edu\\sap\\KeywordDrivenFramework\\KeywordEngine\\TestScenarios\\SAP Scenarios.xlsx";

		try {
			excel = new FileInputStream(excelSheetPath);
			XSSFWorkbook workbook = new XSSFWorkbook(excel);
			int sheetsCount = workbook.getNumberOfSheets();
			// System.out.println("Number of present are " +sheetsCount);
			for (int sheetNumber = 0; sheetNumber <= sheetsCount - 1; sheetNumber++) {	
				extent = baseObj.intiExtentReport();
				driver = baseObj.initDriver(browser);
				XSSFSheet sheet = workbook.getSheetAt(sheetNumber);
				extent.createTest(workbook.getSheetName(sheetNumber));
				int rowCount = sheet.getLastRowNum();
				int columnCount = sheet.getRow(1).getLastCellNum();
				// System.out.println("Number of rows present are " + rowCount);
				// System.out.println("Number of columns present are " + columnCount);
				for (int i = 1; i <= rowCount; i++) {
					LinkedList<String> testExecution = new LinkedList<>();
					for (int j = 0; j <= columnCount - 1; j++) {
						Cell cellValue = sheet.getRow(i).getCell(j);
						if (cellValue != null) {
							String cellData = cellValue.getStringCellValue();
							System.out.println("Keyword value " + cellData);
							testExecution.add(cellData);
						}
					}
					String testSteps = testExecution.get(0);
					String locatorName = testExecution.get(1);
					String locatorType = testExecution.get(2);
					String testData = testExecution.get(3);
					String assertionType = testExecution.get(4);
					String expected = testExecution.get(5);
					String actual = testExecution.get(6);
					execute(testSteps, locatorName, locatorType, testData, assertionType,expected, actual);
				}
				driver.close();
				System.out.println("****************Test Case " + sheet.getSheetName()+ " is executed");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found exception occured" + ex.getMessage());
		} catch (IOException ioEx) {
			System.out.println("IO exception occured" + ioEx.getMessage());
		} finally {
			if (excel != null) {
				excel.close();
			}
		}
	}
	/**
	 * This method is to execute the steps
	 * @param step
	 * @param locatorName
	 * @param locatorType
	 * @param testData
	 * @param assertionType
	 * @param expeceted
	 * @param actual
	 * @author syusifov
	 */

	public void execute(String step, String locatorName, String locatorType, String testData, String assertionType,
			String expeceted, String actual) {
		switch (step) {

		case "enter_URL":
			keyword.enter_URL(driver, testData);
			break;

		case "type":
			keyword.type(driver, locatorType, locatorName, testData);
			break;
		case "click":
			keyword.click(driver, locatorType, locatorName, testData);
			break;
		default:
			break;
		}
		if(step.contains("assertElement")) {
			assertion.assertElement(driver, assertionType, locatorName, locatorType);
		}
		if(step.contains("assertText")) {
			assertion.assertText(driver, assertionType, locatorName, locatorType, actual);
		}
			
	}

}
