package CRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerfiyObjects {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void login() {
		
		
		String baseURL = "https://alchemy.hguy.co/crm/";
		String username = "admin";
		String password = "pa$$w0rd";
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);
		
		driver.get(baseURL);
		driver.manage().window().maximize();

		// Login to CRM
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("username_password")).sendKeys(password);
		driver.findElement(By.id("bigbutton")).submit();

	}

	@Test
	public void leadAlert() throws InterruptedException {

		Actions action = new Actions(driver);

		// Verify the visibility of Sales Menu

		WebElement sales = driver.findElement(By.id("grouptab_0"));
		action.moveToElement(sales).build().perform();
		wait.until(ExpectedConditions.visibilityOf(sales));

		// Click on activity
		action.moveToElement(driver.findElement(By.id("moduleTab_9_Leads"))).click().perform();

		Thread.sleep(5000);

		// Click additional info
		WebElement addInfo = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr[1]/td[10]/span"));
		action.moveToElement(addInfo).click().perform();

		Thread.sleep(5000);

		// Get Phone number

		WebElement phone = driver.findElement(By.xpath("//*[@class = 'phone']"));
		if (phone != null) {
			System.out.println("Phone number of the Contact person is: " + phone.getText());
		} else {
			System.out.println("No number found");
		}

		Reporter.log("Identifying Lead phone number successful.");

	}

	@Test
	public void verifyActivity() {
		Actions action = new Actions(driver);
		
		// Verify the visibility of Activity Menu
		WebElement activity = driver.findElement(By.id("grouptab_3"));
		action.moveToElement(activity).click().perform();
	
		// Click on activity
		action.moveToElement(driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[2]/ul/li[5]/span[2]/ul/li[1]/a"))).doubleClick().perform();

		// Verify navigation
		String activityCRM = driver.getCurrentUrl();
		Assert.assertEquals(activityCRM, "https://alchemy.hguy.co/crm/index.php?action=ajaxui#ajaxUILoc=index.php%3Fmodule%3DHome%26action%3Dindex%26parentTab%3DActivities");

		Reporter.log("navigated to Activities Sucessfully");

	}

	@AfterMethod
	public void tearDownTest() {
		driver.close();
	}

}
