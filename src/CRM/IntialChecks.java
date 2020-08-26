package CRM;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class IntialChecks {
	
	WebDriver driver;
	
	@BeforeTest
	@Parameters({"baseURL"})
	public void openBrowser(String baseURL) {
		driver = new ChromeDriver();
		driver.get(baseURL);
	}

	@Test 
	public void getTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		//Assert the title
		
		Assert.assertEquals(title, "SuiteCRM");
		System.out.println(title);
		
		Reporter.log("Title Asserted");
	}
	
	@Test
	public void headerImage() {
		
		//Get Header Image
		String image = driver.findElement(By.xpath("//*[@id='form']/div[1]/img")).getAttribute("src");
		//Print Image Url
		System.out.println(image);
		
		Reporter.log("Activity 2 completed.");
		
	}
	
	@Test
	public void footerImage() {
		//get footer text
		String foot = driver.findElement(By.xpath("//*[@id='admin_options']")).getText();
		System.out.println(foot);
	}


	@AfterTest
	public void tearDown() {
		driver.close();
		
	}

}
