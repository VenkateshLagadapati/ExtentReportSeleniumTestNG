package com.selenium.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.selenium.utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	static Logger logger = LogManager.getLogger(BaseClass.class.getName());
	public static String screenshotsSubFolderName;
	
	public static ExtentReports extentReports;
	public static  ExtentTest extentTest;
	
	ReadConfig readconfig = new ReadConfig();
	
	public String url = readconfig.getUrl();
	public String browser = readconfig.getBrowserName();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	
	@Parameters("browser")
	@BeforeClass
	public void setup(ITestContext context, @Optional("chrome") String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String device = capabilities.getBrowserName() + " " + capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
		logger.info("launching browser");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		extentTest = extentReports.createTest(context.getName());
		extentTest.assignDevice(device);
	}
	
	@BeforeSuite
	public void initialiseExtentReport() {
		ExtentSparkReporter sparkReport_all = new ExtentSparkReporter("./Reports/AllTests.html");
		sparkReport_all.config().setReportName("All Tests report");
		
		ExtentSparkReporter sparkReporter_failed = new ExtentSparkReporter("./Reports/FailedTests.html");
		sparkReporter_failed.filter().statusFilter().as(new Status[] {Status.FAIL}).apply();
		sparkReporter_failed.config().setReportName("Failure report");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReport_all, sparkReporter_failed);
		
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("username", username);
		extentReports.setSystemInfo("password", password);
	}
	
	@AfterSuite
	public void generateExtentReport() {
		extentReports.flush();
	}
	
	@AfterMethod
	public void checkStatus(Method m,ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = null;
			screenshotPath = captureScreenshot(result.getTestContext().getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName()+ " is passed");
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver=null;
		logger.info("closing browser");
	}
	

	public String captureScreenshot(String fileName) {
		if(screenshotsSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotsSubFolderName = myDateObj.format(myFormatObj);
		}
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ screenshotsSubFolderName+"/"+fileName+".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();
	}
	
	
	
}
