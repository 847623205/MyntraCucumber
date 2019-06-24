package FeatureFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WriteStepDefinition 
{
	
	//driver is a WebDriver Instance
	WebDriver driver;
	
	//Instance of Actions class
	Actions act = new Actions(driver);
	
	//Application website
	String WebSite = "https://www.myntra.com";
	
	//This Given method is to invoke a browser
	@Given("^User opens the application pagee$")
	public void user_opens_the_application_pagee()  
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Backup\\Eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(WebSite);	
	}

	
	//To navigate into "Men" menu and "Formal Shirts" Collection
	@When("^user navigating to the particular product catogery$")
	public void user_navigating_to_the_particular_product_catogery() 
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//Mouse Over on the "Men" Menu 
		act.moveToElement(driver.findElement(By.xpath("//*[@class=\"desktop-main\" and text()=\"Men\"]"))).build().perform();
		
		//To select Men-Formal shirt Category from "Men" main menu 
		WebElement Formal_Shirt = driver.findElement(By.xpath("//*[@class=\"desktop-categoryLink\" and text()=\"Formal Shirts\"]"));
		Formal_Shirt.click();
	
	}

	//To write product name and price in the Excel Sheet
	
	
	@Then("^User writes the product details to Excel document$")
	public void user_writes_the_product_details_to_Excel_document() throws IOException 
	{
 System.out.println("hi");   	
	}
	
}
