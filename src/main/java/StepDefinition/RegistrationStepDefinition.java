


package StepDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegistrationStepDefinition 
{
	//driver is a WebDriver Instance
	WebDriver driver;
		
	//Application website=-URL
	String WebSite = "https://www.myntra.com";
		
	//This Given method is to invoke a browser
	@Given("^User is on the application Web Site$")
	public void user_is_on_the_application_Web_Site()  
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Backup\\Eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();    
	}

	//Tpo reach sign up Link
	@When("^User Opens the SignUp Link$")
	public void user_Opens_the_SignUp_Link()  
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//span[text()=\"Profile\"]"))).build().perform();

		WebElement SignUp = driver.findElement(By.xpath("//a[text()=\"Sign up\"]"));
		SignUp.click();
	    
	}

	@Then("^User Enters Email Id and Name and Mobile Number, Selects the Gender Category and Clicks the \"([^\"]*)\" Button$")
	public void user_Enters_Email_Id_and_Name_and_Mobile_Number_Selects_the_Gender_Category_and_Clicks_the_Button(String arg1) throws IOException 
	{
		// Instances of Excel Workbook and Sheet
		InputStream ExcelFileToRead = new FileInputStream("D:\\MyntraRegistration.xlsx");
	    XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
	    XSSFSheet sheet = wb.getSheetAt(0);
	    
	    //To read email from the the XL document 
	    XSSFCell Email = wb.getSheetAt(0).getRow(1).getCell(0);
	    String email= Email.toString();
	    driver.findElement(By.name("email")).sendKeys(email);
	    
	   //To read password(Encrypted form) from the the XL document
	    XSSFCell Password = wb.getSheetAt(0).getRow(1).getCell(1);
	    String pass= Password.toString();
	    driver.findElement(By.name("password")).sendKeys(pass);
	    
	    //To read Phone Number from the the XL document
	    XSSFCell PhoneNumber = wb.getSheetAt(0).getRow(1).getCell(2);
	    String Phone= PhoneNumber.getRawValue();
	    driver.findElement(By.name("mobile")).sendKeys(Phone);    
		
	  //To Choose the gender category from radio button
	    driver.findElement(By.xpath("//input[@id=\"male\"]")).click();
	    
	    //to validate the "REGISTER" button (Sign Up Button)
	    boolean RegistrationButton = driver.findElement(By.xpath("//button[text()=\"REGISTER\"]")).isEnabled();
	    
	    Assert.assertEquals(true, RegistrationButton);  
	    

	}
	

}
