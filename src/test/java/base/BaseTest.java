package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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
	
	public void captureScreenshotFail(String fileName) {
		
		if (screenshotSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotSubFolderName = myDateObj.format(myFormatObj);
		}
	    
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshotsFail/"+ screenshotSubFolderName +"/"+ fileName + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void captureScreenshotPass(String fileName) {
		
		if (screenshotSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotSubFolderName = myDateObj.format(myFormatObj);
		}
	    
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshotsPass/"+ screenshotSubFolderName +"/"+ fileName + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider (name = "loginTestData")
	public String[][] getData() throws Exception {
		File excelFile = new File("./src/test/resources/Testbook.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int noOfRows = sheet.getPhysicalNumberOfRows();
		int noOfCol = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[noOfRows-1][noOfCol];
		
		for (int i=0; i< noOfRows-1; i++) {		
			for (int j=0 ; j<noOfCol; j++) {
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}		
		workbook.close();
		fis.close();
		return data;
	}
	
	public void storeFailScreenshotInReport(String reportFileName) {
		File img = new File("./screenshotsFail/"+ screenshotSubFolderName +"/"+ reportFileName + ".png");
		FileOutputStream screenshotStream = null;
		try {
			screenshotStream = new FileOutputStream(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			screenshotStream.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
		}
		try {
			screenshotStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log(" Defect- " +" <a href='"+img.getAbsolutePath()+"'> <img src='"+ img.getAbsolutePath()+"' height='150' width='280'/> </a>");
	}
	
	public void storePassScreenshotInReport(String reportFileName) {
		File img = new File("./screenshotsPass/"+ screenshotSubFolderName +"/"+ reportFileName + ".png");
		FileOutputStream screenshotStream = null;
		try {
			screenshotStream = new FileOutputStream(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			screenshotStream.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
		}
		try {
			screenshotStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log(" Passed- " +" <a href='"+img.getAbsolutePath()+"'> <img src='"+ img.getAbsolutePath()+"' height='150' width='300'/> </a>");
	}

}
