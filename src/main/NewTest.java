package main;

import org.testng.annotations.Test;
import javaPackage.Methods;
import utilities.Reusables;
import utilities.Utilities;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

public class NewTest {
	
	 public String url = "https://www.amazon.in/";
	 public WebDriver driver ; 
	
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", "c:\\src\\chromedriver.exe");
	  driver=new ChromeDriver();
	  // Pass application
	  driver.get(url);
	  // Maximize window
	  driver.manage().window().maximize();
	  }

  @Test(priority = 1, groups = "FunctionalTests")
  public void Test1() throws IOException {
	  	//Creating an Object using PageFactory to call the different reusable methods
	    Methods methods = PageFactory.initElements(driver, Methods.class);
	    //Calling the methods created for various interactions in the amazon web application
		methods.clickSearch();
		methods.clickDropDown();
		methods.clickSubmit();
		methods.sendTextInSearchBox();
		methods.clickOption();
		methods.WriteExcelFile();
  }

  @Test(priority = 2, groups = "FunctionalTests", enabled = false)
  public void Test2() throws IOException {
	  	File src=new File("C:\\Users\\91949\\eclipse-workspace\\ZoomRX\\ObjectRepo.properties");
		// Create FileInputStream object
		FileInputStream objfile=new FileInputStream(src);
		// Create Properties class object to read properties file
		Properties obj=new Properties();
		// Load file so we can use into our script
		obj.load(objfile);
		//getProperty Fetches the value of target key from the properties file
		WebElement searchBox = driver.findElement(By.xpath(obj.getProperty("amazon.searchBox.xpath")));
		WebElement submit = driver.findElement(By.xpath(obj.getProperty("amazon.submit.xpath")));
		WebElement dropDown = driver.findElement(By.xpath(obj.getProperty("amazon.dropDown.xpath")));
		Reusables reusables = PageFactory.initElements(driver, Reusables.class);
		reusables.toClick(searchBox);
		reusables.toClickDropDown(dropDown, "Electronics");
		reusables.toClick(submit);
		reusables.toEnterText(searchBox, "Earphones");
		WebElement thirdOption = driver.findElement(By.xpath(obj.getProperty("amazon.thirdOption.xpath")));
		reusables.toClick(thirdOption);
  }
  
  @Test(priority = 3, groups = "FunctionalTests", enabled = false)
  public void Test3() throws IOException {
	  	File src=new File("C:\\Users\\91949\\eclipse-workspace\\ZoomRX\\ObjectRepo.properties");
		FileInputStream objfile=new FileInputStream(src);
		Properties obj=new Properties();
		obj.load(objfile);
		WebElement searchBox = driver.findElement(By.xpath(obj.getProperty("amazon.searchBox.xpath")));
		WebElement submit = driver.findElement(By.xpath(obj.getProperty("amazon.submit.xpath")));
		WebElement dropDown = driver.findElement(By.xpath(obj.getProperty("amazon.dropDown.xpath")));
		Utilities utilities = PageFactory.initElements(driver, Utilities.class);
		utilities.interaction(searchBox, "Button", "Click");
		utilities.interaction(dropDown, "Select", "Electronics");
		utilities.interaction(submit, "Button", "Click");
		utilities.interaction(searchBox, "Textbox", "Electronics");
		WebElement thirdOption = driver.findElement(By.xpath(obj.getProperty("amazon.thirdOption.xpath")));
		utilities.interaction(thirdOption, "Button", "Click");
  }
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
