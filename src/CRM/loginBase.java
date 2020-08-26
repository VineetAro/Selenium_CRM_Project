package CRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginBase{

	WebDriver driver;
		
	@BeforeTest
	public void login() {
		String baseURL = "https://alchemy.hguy.co/crm/" ;
		String username = "admin";
		String password = "pa$$w0rd";
		
		driver = new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();

		// Login to CRM
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.findElement(By.id("bigbutton")).submit();

	}
	
	@Test
	  public void homePage_Verification(){
		  
		  String home = driver.getCurrentUrl();
		  
		  //Assert HomePage 
		  
		  Assert.assertEquals(home, "https://alchemy.hguy.co/crm/index.php?module=Home&action=index");
		  
		  Reporter.log("Navigated to Homepage successfully.");
	  }
	
	@Test
	public void verifyNavColor() {
		WebElement navBar = driver.findElement(By.xpath("//*[@id='ajaxHeader']/nav"));

		// Get color and print on console
		String color = navBar.getCssValue("color");

		System.out.println("Navigation RGB color is: " + color);

	}
	@AfterTest
	public void tearDownTest() {
		driver.close();
	}

}
