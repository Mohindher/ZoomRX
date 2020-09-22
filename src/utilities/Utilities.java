package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Utilities{
	
	WebDriver driver;
	public Utilities(WebDriver driver)
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
 * @example
 * Example:
 * // Set the text value for a textbox with id "amount" to "100".  This textbox is located within a Desktop dialog box.
 * interaction(amount, "100", "Textbox")
 */
	public void interaction(WebElement locator, String tableColData,String typeOfInteraction) {
		if(tableColData == null){
		    System.out.println("interaction: tableColData was " + tableColData + " so did not search.");
		    return;
		    }
		Reusables reusables = PageFactory.initElements(driver, Reusables.class);
		reusables.typeOfInteraction(locator,tableColData,typeOfInteraction);
	}	
}
