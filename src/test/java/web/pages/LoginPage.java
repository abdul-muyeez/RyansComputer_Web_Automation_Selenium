package web.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import web.utilities.GetScreenShot;
import web.drivers.PageDriver;
import web.utilities.CommonMethods;

public class LoginPage extends CommonMethods {
	
	
	ExtentTest test;
	
	public LoginPage(ExtentTest test) {
		
		PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test= test;
}
	
	@FindBys({@FindBy (xpath="//body/nav[2]/div[1]/div[4]/div[2]/ul[1]/li[4]/a[1]/i[1]")})
	WebElement account;
	
	@FindBys({@FindBy (xpath="//form[@action='https://www.ryans.com/customers/login']//input[@placeholder='Enter Your Phone Number']")})
	WebElement phonenumber;
	
	@FindBys({@FindBy (xpath="//form[@action='https://www.ryans.com/customers/login']//input[@placeholder='Enter Your Password']")})
	WebElement password;

	@FindBys({@FindBy (xpath="//form[@action='https://www.ryans.com/customers/login']//button[@type='submit'][normalize-space()='Submit']"),
			@FindBy (xpath="//body/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[4]/button[1]")})
	WebElement submit;

	public LoginPage() {

	}


	// Report
	public void passCase(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
	}

	@SuppressWarnings("unused")
	public void passCaseWithSC(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}

	// Fail
	@SuppressWarnings("unused")
	public void failCase(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#FF5353; font-size:13px\"><b>" + message + "</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), "" + scName + "");
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + "" + scName + ".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDriver().quit();
	}
	
	public void login () throws IOException {
		
	try {
		test.info("Please Click Account Option");
		if(account.isDisplayed()) {
			account.click();
			passCase("You have successfully click account option");
			timeout(2000);
		try {
			test.info("Please enter phone number");
			if(phonenumber.isDisplayed()) {
				phonenumber.sendKeys("01317117829");
				passCase("You have successfully entered number");
				timeout(2000);
				
				try {
					test.info("Please enter password");
					if(password.isDisplayed()) {
						password.sendKeys("astspasts");
						passCase("You have successfully entered password");
						timeout(2000);
						
						try {
							test.info("Click on Sign in");
							if(submit.isDisplayed()) {
								submit.click();
								passCase("You have successfully clicked Submit");
								timeout(3000);
								
							
							}	
								
								// Check if the error message is displayed
							/*/	try {
									WebElement errorMessage = PageDriver.getCurrentDriver().findElement(By.xpath("//span[contains(text(),'These credentials do not match our records.')]"));
									if(errorMessage.isDisplayed()) {
										failCase("Login failed: Invalid credentials", "login_fail");
										System.out.println("Login failed: Invalid credentials");
									} else {
										passCaseWithSC("You have successfully clicked Login", "login_pass");
										System.out.println("You have successfully clicked Login");
									}
								} catch (Exception e) {
									passCaseWithSC("You have successfully clicked Login", "login_pass");
							*/	
						
							    
							
						} catch (Exception e) {
							failCase("Submit button locator not found", "login_fail");
							System.out.println("User submit locator was not found");
						}		
					}
				
				} catch (Exception e) {
					failCase("Password locator not found", "password_fail");
					System.out.println("User password locator was not found");
				}
			
			} 
			
		} catch (Exception e) {
			failCase("phone number locator not found", "number_fail");
			System.out.println("User number locator was not found");
		}
			}
			
			} catch (Exception e) {
				failCase("account option locator not found", "account_fail");
				System.out.println("Account locator was not found");
			}
	     }
	} 

	
	
	

