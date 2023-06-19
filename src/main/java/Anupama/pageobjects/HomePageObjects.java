package Anupama.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// Defines all the objects in the HomePage
public class HomePageObjects {
	
	private WebDriver driver;

// Constructor to initialize the WebDriver	
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
// Finding the web elements in the page
// PageFactory
	
	@FindBy(xpath="//input[@id='inputEmail']")
	private WebElement email;
	
	@FindBy(css="#inputPassword")
	private WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement submit;
	
	@FindBy(css="#test-2-div")
	private WebElement test2div;
	
	@FindBy(xpath="//div[@id='test-2-div']//ul[@class='list-group']/li")
	private List<WebElement> listItems;
	
	@FindBy(xpath="//ul[@class='list-group']/li[1]")
	private WebElement secondItem;
	
	@FindBy(xpath="//ul[@class='list-group']/li[1]/span[@class='badge badge-pill badge-primary']")
	private WebElement badge;
	
	@FindBy(xpath="//div[@id='test-3-div']//button[@id='dropdownMenuButton']")
	private WebElement dropdownMenuOption;
	
	@FindBy(xpath="//*[@id='test-3-div']/div/div/a")
	private List<WebElement> dropdownOptions;
	
	@FindBy(xpath="//div[@id='test-4-div']//button[1]")
	private WebElement firstButton;
	
	@FindBy(xpath="//div[@id='test-4-div']//button[2]")
	private WebElement secondButton;
	
	@FindBy(xpath="//button[@id='test5-button']")
	private WebElement test5DivButton;

	@FindBy(xpath="//div[@id='test-5-div']//div[@id='test5-alert']")
	private WebElement successMessage;
	
	@FindBy(xpath="//div[@id='test-6-div']")
	private WebElement test6Div;
	
	@FindBy(xpath="//div[@id='test-6-div']//table")
	private WebElement table;
	
	
		
// Method to check if the email input element is present on the page
	public boolean isEmailPresent() {
		return email.isDisplayed();	
	}
	
// Method to check if the password input element is present on the page
	public boolean isPasswordPresent() {
		return password.isDisplayed();
	}
	
// Method to check if the login button is present on the page
	public boolean isLoginButtonPresent() {
		return submit.isDisplayed();
	}
	
// Method to enter email and pwd into respective fields
	public void enterCreds(String emailId,String pwd) {
		email.sendKeys(emailId);
		password.sendKeys(pwd);
	}
	
// Method to check no.of items in the list
	public int getListItemCount() {
		return listItems.size();
	}

// Method to get the name of the second item in the list
	public String getListItemValue(int index) {
		String value = listItems.get(index).getText();
		return value.substring(0, 11);
	}

// Method to get the value of the badge in the second item
	public String getListBadgeValue(int index) {
		WebElement badgeValue = listItems.get(index).findElement(By.xpath(".//span"));
		return badgeValue.getText();		
	}
	
// Method to check if option1 is the default value
	public String getDropDownValue() {
		return dropdownMenuOption.getText();
		
	}
	
// Method to select Option 3 from the dropdown
	public void selectDropDown(String option) {
		dropdownMenuOption.click();
		for (int i=0; i< dropdownOptions.size();i++) {
			if(dropdownOptions.get(i).getText().equalsIgnoreCase(option)) {
				dropdownOptions.get(i).click();
			}
		}
	}
	
// Method to check if Button1 is disabled
	public boolean isButtonEnabled() {
		return firstButton.isEnabled();
	}
	
// Method to check if Button2 is disabled
		public boolean isButtonDisabled() {
			return secondButton.isEnabled();
		}
		
// Method to click the Button
		public void waitForButtonAndClick() {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
			wait.until(ExpectedConditions.visibilityOf(test5DivButton));
			test5DivButton.click();
		}
		
// Method to check the message
		public String getMessage() {
			return successMessage.getText();
		}
		
// Method to check if the Button is disabled
		public boolean isPrimaryButtonDisabled(){
			return test5DivButton.isEnabled();
		}
		
// Method to get cell value in the table
		public String getGridCellValue(int row, int column) {
			WebElement cell = driver.findElement(By.xpath("//div[@id='test-6-div']/div/table/tbody/tr["+ (row+1) +"]/td["+( column+1) +"]"));
			return cell.getText();
		}
	
	
	
	
	
	
	
	
	

}
