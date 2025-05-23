package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class BaseTest {
	
	public static WebDriver driver;
	public static String screenshotSubFolderName;
	
	@BeforeTest
	public void beforeTestMethod() {
	}
	
	@BeforeMethod
	@Parameters ("browser")
	public void beforeMethodMethod(String browser, Method testMethod) {
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		
	}
	
	@AfterMethod
	public void afterMethodMethod() {
		driver.quit();
	}
	
	@AfterTest
	public void afterTestMethod() {
		
	}
	
	public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}
	
	public void captureScreenshot(String fileName) {
		
		if (screenshotSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotSubFolderName = myDateObj.format(myFormatObj);
		}
	    
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/"+ screenshotSubFolderName +"/"+ fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
