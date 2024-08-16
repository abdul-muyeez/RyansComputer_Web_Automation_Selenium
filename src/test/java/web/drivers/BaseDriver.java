package web.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

//Define a public class named BaseDriver
public class BaseDriver {
	
	protected static String url = "https://www.ryans.com/";
	
	// Define a public static variable named driver to hold the WebDriver instance
	public static WebDriver driver;

	// Annotate the method to indicate it should run before the entire test suite
	@BeforeSuite
	public void startBrowser() {
		// Define a string variable to specify the browser name
		String browser_name = "chrome";

		// Check if the specified browser is "chrome"
		if (browser_name.equals("chrome")) {
			// Set up the Chrome WebDriver using WebDriverManager
			WebDriverManager.chromedriver().setup();
			// Initialize the ChromeDriver instance
			driver = new ChromeDriver();
			// Maximize the browser window
			driver.manage().window().maximize();
		} else if (browser_name.equals("firefox")) {
			// Set up the Firefox WebDriver using WebDriverManager
			WebDriverManager.firefoxdriver().setup();
			// Initialize the FirefoxDriver instance
			driver = new FirefoxDriver();
			// Maximize the browser window
			driver.manage().window().maximize();
		} else {
			// Set up the Edge WebDriver using WebDriverManager
			WebDriverManager.edgedriver().setup();
			// Initialize the EdgeDriver instance
			driver = new EdgeDriver();
			// Maximize the browser window
			driver.manage().window().maximize();
		}

		// Set the WebDriver instance in the PageDriver
		PageDriver.getInstance().setDriver(driver);
	}

	// Annotate the method to indicate it should run after the entire test suite
	@AfterSuite
	public void closeBrowser() {
		// Close the browser and quit the WebDriver session
		driver.quit();
	}
}
