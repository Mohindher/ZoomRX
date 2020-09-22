package javaPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods{
	
	WebDriver driver;
	public Methods(WebDriver driver)
	{
		this.driver = driver;
	}
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBox;
	@FindBy(xpath="//input[@class='nav-input' and @value='Go']")
	WebElement submit;
	@FindBy(xpath="//select[@class='nav-search-dropdown searchSelect']")
	WebElement selectDropDown;
	@FindBy(xpath="//div[@id='issDiv3']")
	WebElement thirdOption;
	@FindBy(xpath="//span[starts-with(@class, 'a-size-medium')]")
	WebElement results;
	public void clickSearch()
	{
		//Clicking on the Search Box
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
	}
	
	public void sendTextInSearchBox()
	{
		//Searching for the value in the search box available and entering the text as Earphones
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys("Earphones");
	}
	
	public void clickSubmit()
	{
		//Clicking on the Submit button
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
	}
	
	public void clickDropDown()
	{
		//Selecting the drop down value as Electronics
		Select objSelect = new Select(selectDropDown);
		objSelect.selectByVisibleText("Electronics");
	}
	
	public void clickOption()
	{
		//Clicking on the third option which is auto suggested
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(thirdOption)).click();
	}	
	
	public void WriteExcelFile() throws IOException {	
		//Create an object of FileInputStream class to read excel file
		FileInputStream file = new FileInputStream("c:\\src\\amazonDataEntry.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Product Name");
        Row row1 = sheet.getRow(0);
        Cell cell1 = row1.createCell(1);
        cell1.setCellValue("Price");
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    	List<WebElement> resultList = driver.findElements(By.xpath("//span[starts-with(@class, 'a-size-medium')]"));
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfAllElements(resultList));
		List<WebElement> resultList1 = driver.findElements(By.xpath("//span[starts-with(@class, 'a-price-whole')]"));
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.visibilityOfAllElements(resultList1));
		int rownum=1, colnum= 0;
		if(resultList.size()>0 && resultList1.size()>0){
			//Setting the values in the CSV File
			java.util.Iterator<WebElement> itr = resultList.iterator();
			java.util.Iterator<WebElement> itr1 = resultList1.iterator();
			while (itr.hasNext() && itr1.hasNext())
			 { 
				 	Row row11 = sheet.createRow(rownum);
			        Cell cell11 = row11.createCell(colnum);
			        cell11.setCellValue(itr.next().getText());
			        System.out.println(itr.next().getText());
			        Row row12 = sheet.getRow(rownum);
			        Cell cell12 = row12.createCell(colnum+1);
			        cell12.setCellValue(itr1.next().getText());
			        System.out.println(itr1.next().getText());
			        rownum = rownum+1;
			 }}
		FileOutputStream fos = new FileOutputStream("c:\\src\\amazonDataEntry.xlsx");
		workbook.write(fos);
		fos.close();
	}
}