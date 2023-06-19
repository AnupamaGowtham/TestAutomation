package Anupama.QETechAssessment;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Anupama.pageobjects.HomePageObjects;
import QETechAssessment.data.DataReader;

public class HomePage extends DataReader {
	private WebDriver driver;
	private HomePageObjects homePageObjects;
	
	@BeforeTest 
	public void setUp() {
		 // Set up the ChromeDriver path and initialize the WebDriver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gowth\\Desktop\\Anupama\\TechnicalAssessment\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///C:/Users/gowth/Desktop/Anupama/TechnicalAssessment/QE-index.html");
        homePageObjects = new HomePageObjects(driver);
	}
	
	@Test(dataProvider= "getData",priority = 1)
	public void testEmailAndPasswordInputs(HashMap<String, String> input) {
		// Assert that email address and password inputs are present
		Assert.assertTrue(homePageObjects.isEmailPresent());
		Assert.assertTrue(homePageObjects.isPasswordPresent());
		
		 // Assert that login button is present
        Assert.assertTrue(homePageObjects.isLoginButtonPresent());
        
        // Enter email and password combination
        homePageObjects.enterCreds(input.get("email") ,input.get("password"));
	}
	
	@Test(priority = 2)
    public void testListItems() {
		 // Assert that there are three values in the list group
        Assert.assertEquals(homePageObjects.getListItemCount(),3);
        
        // Assert that the second list item's value is "List Item 2"
        Assert.assertEquals(homePageObjects.getListItemValue(1), "List Item 2");
        
        // Assert that the second list item's badge value is 6
        Assert.assertEquals(homePageObjects.getListBadgeValue(1), "6");
	
	}
	
	@Test(priority = 3)
	public void testDropdownSelection() {
		// Assert that "Option 1" is the default selected value
        Assert.assertEquals(homePageObjects.getDropDownValue(), "Option 1");
        
     // Select "Option 3" from the dropdown list
        homePageObjects.selectDropDown("Option 3");
	}

	@Test(priority = 4)
	public void testButtonStatus() {
		// Assert that the first button is enabled
        Assert.assertTrue(homePageObjects.isButtonEnabled());

        // Assert that the second button is disabled
        Assert.assertFalse(homePageObjects.isButtonDisabled());
	}

	@Test(priority = 5)
	public void testDelayedButtonClick() {
		// Wait for the button to be displayed and then click it
		homePageObjects.waitForButtonAndClick();
		
		// Assert that a success message is displayed
		Assert.assertEquals(homePageObjects.getMessage(),"You clicked a button!");
		
		// Assert that the button is disabled
		Assert.assertFalse(homePageObjects.isPrimaryButtonDisabled());
	}
	
	@Test(priority = 6)
	public void testGridCellValue() {
		// Find the value of the cell at coordinates 2, 2
		String cellValue = homePageObjects.getGridCellValue(2, 2);
		Assert.assertEquals(cellValue, "Ventosanzap");
	}
	
	
	@AfterTest
	public void closeBrowser() {
		// Close the browser and quit the WebDriver
		driver.quit();
	} 
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\QETechAssessment\\data\\Data.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	}
}
