package StepDefinition;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductWriteStepDefinition 
{
	//driver is a WebDriver Instance
	WebDriver driver;
		
	//Instance of Actions class
	Actions act = new Actions(driver);
		
	//Application website
	String WebSite = "https://www.myntra.com";
	
	@Given("^user is on home pagee$")
	public void user_is_on_home_pagee()  
	{
		System.setProperty("webdriver.chrome.driver", "D:\\\\Backup\\\\Eclipse\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();    
	}
	
	@When("^user navigating to product info pagee$")
	public void user_navigating_to_product_info_pagee()  
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		//Mouse Over on the "Men" Menu 
		act.moveToElement(driver.findElement(By.xpath("//*[@class=\"desktop-main\" and text()=\"Men\"]"))).build().perform();
		
		//To select Men-Formal shirt Category from "Men" main menu 
		WebElement Formal_Shirt = driver.findElement(By.xpath("//*[@class=\"desktop-categoryLink\" and text()=\"Formal Shirts\"]"));
		Formal_Shirt.click();
		
		
		/*driver.findElement(By.xpath("//input[@class=\"desktop-searchBar\"]")).sendKeys("chudidhars");
		driver.findElement(By.xpath("//span[@class=\"myntraweb-sprite desktop-iconSearch sprites-search\"]")).click();
		System.out.println("product page reached");
		*/
	}
	
	@Then("^Details write into excel$")
	public void Details_write_into_excel() throws Throwable 
	{
		XSSFWorkbook work = new XSSFWorkbook();
		XSSFSheet sht = work.createSheet("Formal Shirts");
		
		//product details
		String pageXpath[]= {"pdp-title","pdp-price"};
		
		Thread.sleep(2000);		
		
		String ParentWindow= driver.getWindowHandle();
		Actions act = new Actions(driver);	
        Row newRow = sht.createRow(0);
		
		newRow.createCell(0).setCellValue("Product Name");
				
		newRow.createCell(1).setCellValue("Product Price");
		
		//int total= FormalShirts_count.size();
		//System.out.println("total:"+total);
		//System.out.println(driver.getTitle());		
		
		for(int i=1; i<5;i++)
		{
			String SytsaticPart = "(//*[@class=\"img-responsive\"])[";   
			String DynamicPart = SytsaticPart +i+"]";
			act.moveToElement(driver.findElement(By.xpath(DynamicPart))).click().build().perform();
			
			for(String handle : driver.getWindowHandles()) 
			{
				if(!handle.equals(ParentWindow)) 
				{
					driver.switchTo().window(handle);
					newRow = sht.createRow(i);
					for(int j = 0;j<pageXpath.length;j++) 
					 {
						Cell cell = newRow.createCell(j);
						String forPass = "//*[@class = '"+pageXpath[j]+"']"; 
						try {
							cell.setCellValue(driver.findElement(By.xpath(forPass)).getText().toString());
						}
						catch(NoSuchElementException e) {
							cell.setCellValue("Nil");
						}
					 }
				}
		}
			driver.close();
			driver.switchTo().window(ParentWindow);
			//if(i==20) break;
		}
		FileOutputStream out = new FileOutputStream(new File("D:\\Myntra_FormalShirts.xlsx"));
		work.write(out);
		out.close();
		
		
	
	}
		
}	
		



