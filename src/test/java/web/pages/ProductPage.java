package web.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import web.drivers.PageDriver;
import web.utilities.GetScreenShot;

public class ProductPage extends LoginPage {

	ExtentTest test;

	public ProductPage(ExtentTest test) {
        super(test);
        PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test= test;
	}

	@FindBys({@FindBy (xpath="//button[@aria-label='cart desktop']//i[@class='fas fa-cart-arrow-down']"),
		 @FindBy (xpath="//body/nav[2]/div[1]/div[4]/div[2]/ul[1]/li[1]/button[1]"),
			@FindBy (xpath = "//body/nav[2]/div[1]/div[4]/div[2]/ul[1]/li[1]/button[1]/i[1]")})
		WebElement cart;
	
	
	
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
		
		public void product() throws IOException {
			
			try {
				test.info("Click cart button");
				if(cart.isDisplayed()) {
					cart.click();
					passCase("You have successfully clicked cart");
					timeout(2000);
				}
				
			} catch (Exception e) {
				failCase("Click button locator not found", "cart_fail");
				System.out.println("Cart locator was not found");
				
			}
		}
}
