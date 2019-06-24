package StepDefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PurchaseStepdefinition
{
	//driver is a WebDriver Instance
	WebDriver driver;
			
	//Application website=-URL
	String WebSite = "https://www.myntra.com";
			
	//This Given method is to invoke a browser
	@Given("^User navigating to the particular product$")
	public void user_navigating_to_the_particular_product() 
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Backup\\Eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies(); 
	}

	@When("^User selects the product to purchase$")
	public void user_selects_the_product_to_purchase() throws InterruptedException  
	{
		//Instance of Actions Class
		Actions act = new Actions(driver);
		
		//Mouse over on "Women" link to to select a product from women menu 
		act.moveToElement(driver.findElement(By.xpath("//a[@class=\"desktop-main\" and text()=\"Women\"]"))).build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//to select  womens's ethnic wears collectios 
		WebElement Ethnic_Dresses = driver.findElement(By.xpath("//*[@class=\"desktop-categoryLink\" and text()=\"Ethnic Dresses\"]"));
		Ethnic_Dresses.click();
		
		//to select first product from the list of women's ethnic wears
		WebElement First_Product = driver.findElement(By.xpath("//*[@id=\"desktopSearchResults\"]/div[2]/section/ul/li[1]/a/div[1]/div"));
		First_Product.click();

	}
	
	@Then("^User adds the product to bag and purchase it$")
	public void User_adds_the_product_to_bag_and_purchase_it() throws InterruptedException
	{
		//Parent Window
		String parentWinHandle = driver.getWindowHandle();
        System.out.println("Parent window handle: " + parentWinHandle);

        //to store all child windows in list 
        Set<String> winHandles = driver.getWindowHandles();
        
        // Loop through all Child window handles
        for(String handle: winHandles)
        	{
            
        	//parent window != child window
        	if(!handle.equals(parentWinHandle))
            {
                    		
        	driver.switchTo().window(handle);
            Thread.sleep(1000);
            
            //it selects the size of a dress - M size
            WebElement Size = driver.findElement(By.xpath("//p[@class=\"size-buttons-unified-size\" and text()=\"M\"]"));
            Size.click();
            
            // to add the product into bag
            WebElement Add_To_Bag = driver.findElement(By.xpath("//span[@class=\"myntraweb-sprite pdp-whiteBag sprites-whiteBag pdp-flex pdp-center\"]"));
            Add_To_Bag.click();
            
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            
            //to reach cart to buy the product in the cart
            WebElement Go_To_Cart = driver.findElement(By.xpath("//*[@class=\"pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center\"]"));
            Go_To_Cart.click();
          
            WebElement Place_Order = driver.findElement(By.xpath("//*[text()=\"Place Order\"]"));
            Place_Order.click();
            
            }

        }
       
	}

}
