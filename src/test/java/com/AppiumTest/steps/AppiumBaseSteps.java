package com.AppiumTest.steps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

import com.jqueryui.ExtentListners.ExtentTestManager;
import com.jqueryui.steps.BaseSteps;
import com.jqueryui.utilities.AndroidManager;
import com.jqueryui.utilities.DriverFactory;
import com.jqueryui.utilities.DriverManager;

public class AppiumBaseSteps {

	private WebDriver driver;
	private Properties Config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseSteps.class);
	public boolean grid=true;
	
	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "src/test/resources/properties" + File.separator
				+ "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	

	public void setUpFramework() {

		configureLogging();
		
		DriverFactory.setConfigPropertyFilePath(
				System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.info("Config properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void logInfo(String message) {
		
		ExtentTestManager.testReport.get().info(message);
	}
	
	//This will be used first to call desiredcapabilities
	public void openApplication() throws MalformedURLException {

		DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        dc.setCapability("autoGrantPermissions", "true");

        
        String locationAppium=System.getProperty("user.dir") + "//src//test//resources//executables//selendroid-test-app.apk";
        dc.setCapability("app", locationAppium);
       // dc.setCapability("app", "C:\\Users\\Dell\\Documents\\selendroid-test-app.apk");
        
        URL url=new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(url,dc);


        AndroidManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
	}
	public void quit() {

		AndroidManager.getDriver().quit();
		log.info("Test Execution Completed !!!");
	}
}
	

