package com.product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static Properties prop;
	public static FileInputStream ip;
	public static WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports extent;
	public static ExtentHtmlReporter reporter;
	
	/*Creating a Constructor and Initializing the properties file*/
	public Base() {
		try {
			prop = new Properties();
			ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config/config.Properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Below Method is used to Initialize the Driver and Browser*/
	@BeforeMethod
	public static void init_Browser() {
		String browser = prop.getProperty("browser");;

		if(browser.equals("chrome")) { 
			WebDriverManager.chromedriver().setup();
			driver  = new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver  = new FirefoxDriver();
		}
		else {
			System.out.println("Please pass the correct Browser..!!"+ browser);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	@BeforeSuite(alwaysRun=true )
	public static void extentReport(){
		reporter = new ExtentHtmlReporter("./reports/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		reporter.config().setTheme(Theme.STANDARD); 
	}
	@AfterSuite(alwaysRun=true )
	public void tearDown() {
		extent.flush();
	}


}



