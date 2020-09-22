//Created a reusable functions to show the polymorphism principle

package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusables {

	WebDriver driver;
	public Reusables(WebDriver driver)
	{
		this.driver = driver;
	}
	
/*
 * Using a locator of WebElement, locate a page element and interact with it.
 * If the element cannot be found, the test will not stop. 
 * @constructor
 * @param {WebElement} locator - The locator of the WebElement  
 * @param {String} tableColData - The data to be used during the page element interaction  
 * @param {String} typeOfInteraction - The type of interaction to be done 
 * @returns {void}
 * @throws {nothing}
 */
	
	public void typeOfInteraction(WebElement locator, String typeOfInteraction, String tableColData) {
		 switch(typeOfInteraction){
	        case "Select":
	        	Select objSelect = new Select(locator);
	    		objSelect.selectByVisibleText(tableColData);
	    	break;
	        case "Textbox":
	        case "PasswordBox":
	        case "NumberInput":
	        case "PhoneInput":
	        case "SearchBox":
	        	WebDriverWait wait = new WebDriverWait(driver, 10);
	    		wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(tableColData);
	    	break;	
	        case "Link":
	        case "Label":
	        case "Panel":
	        case "Cell":
	        case "Image":
	        case "TextNode":
	        case "RadioButton":
	        case "Button":
	        case "SubmitButton":
	        	WebDriverWait waitc = new WebDriverWait(driver, 10);
	    		waitc.until(ExpectedConditions.elementToBeClickable(locator)).click();	
	    	break;
	}
}

/*
 * Using a locator of WebElement, clicks on the element in the UI.
 * If the element cannot be found, the test will not stop. 
 * @constructor
 * @param {WebElement} locator - The locator of the WebElement
 * @returns {void}
 * @throws {nothing}
 * @example
 * Example:
 * toClick(searchbox)
 */
	public void toClick(WebElement locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	/*
	 * Using a locator of WebElement, enters the text into the element in the UI.
	 * If the element cannot be found, the test will not stop. 
	 * @constructor
	 * @param {WebElement} locator - The locator of the WebElement
	 * @param {String} tableColData - The data to be used during the page element interaction 
	 * @returns {void}
	 * @throws {nothing}
	 * @example
	 * Example:
	 * toEnterText(amount,"100")
	 */
	
	public void toEnterText(WebElement locator, String tableColData)
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(tableColData);
	}
	
	/*
	 * Using a locator of WebElement, enters the dropdown value into the element in the UI.
	 * If the element cannot be found, the test will not stop. 
	 * @constructor
	 * @param {WebElement} locator - The locator of the WebElement
	 * @param {String} tableColData - The data to be used during the page element interaction 
	 * @returns {void}
	 * @throws {nothing}
	 * @example
	 * Example:
	 * toClickDropDown(dropdown,"Electronics")
	 */
	
	public void toClickDropDown(WebElement locator, String tableColData)
	{
		Select objSelect = new Select(locator);
		objSelect.selectByVisibleText(tableColData);
	}
	
	/*
	 * Using a locator of WebElement, interacts and enters the values into the Excel file.
	 * @constructor
	 * @param {String} filePath - The path of the excel file where the data to be read and written
	 * @param {String} sheetName - The sheet Name of the excel file where the data to be read and written
	 * @param {WebElement} locator - The locator of the WebElement 
	 * @returns {void}
	 * @throws {nothing}
	 * @example
	 * Example:
	 * WriteExcelFile("filepath","Sheet1", WebElement)
	 */
	
	public void WriteExcelFile(String filePath,String sheetName,WebElement locator) throws IOException {	
		FileInputStream file = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.createRow(1);
		Cell cell = row.createCell(1);
		List<WebElement> resultList = driver.findElements((By) locator);
		 for (WebElement result:resultList) {
			 	cell.setCellValue(result.getText());
	            System.out.println(result.getText());               
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		fos.close();
		 }	
	}
}