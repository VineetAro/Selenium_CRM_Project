package CRM;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class DataScraping {
	WebDriver driver;
	WebDriverWait wait;

	@Parameters({ "username", "password", "baseURL" })
	@BeforeMethod
	public void loginCRM(String username, String password, String baseURL) {
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
	public void accountData() throws InterruptedException {

		Actions action = new Actions(driver);

		// Verify the visibility of Sales Menu

		WebElement sales = driver.findElement(By.id("grouptab_0"));
		action.moveToElement(sales).build().perform();
		wait.until(ExpectedConditions.visibilityOf(sales));

		// Click on Accounts
		action.moveToElement(driver.findElement(By.id("moduleTab_9_Accounts"))).click().perform();
		Thread.sleep(5000);

		// Get the data in accounts table
		int i = 1;
		while (i < 10) {
			String accountName = driver
					.findElement(
							By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr[" + i + "]/td[3]/b"))
					.getText();
			System.out.println("Account Name " + i + " is: " + accountName);
			i += 2;
		}

	}
	
	@Test
	public void leadsData() throws InterruptedException {

		Actions action = new Actions(driver);

		// Verify the visibility of Sales Menu

		WebElement sales = driver.findElement(By.id("grouptab_0"));
		action.moveToElement(sales).build().perform();
		wait.until(ExpectedConditions.visibilityOf(sales));

		// Click on Leads
		action.moveToElement(driver.findElement(By.id("moduleTab_9_Leads"))).click().perform();
		Thread.sleep(5000);

		// Get the data in Leads table
		int i = 1;
		while (i <= 10) {
			String leadName = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr[" + i + "]/td[3]/b")).getText();
			System.out.println("Lead Name " + i + " is: " + leadName);

			// get lead creation user
			String leadUser = driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/form[2]/div[3]/table/tbody/tr[" + i + "]/td[8]/a")).getText();
			System.out.println("Lead created by: " + leadUser);

			i += 1;
		}

	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
