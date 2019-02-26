package com.dev.selenium;

import java.io.IOException;

import org.apache.commons.collections4.list.NodeCachingLinkedList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;


public class commons {
	
	
	private static final ThreadLocal<commons> C = new  ThreadLocal<>();
	
	public static ThreadLocal<commons> get(){
		
		return C;
	}
	
	public static void set(commons common){
		
		C.set(common);
	}

	public static void launchbrowser(String browser) {

		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--ignore-certificate-error", "--dns-prefetch-disable", "--new-window",
					"--lan=en_US UTF-8" + "--disable-extensions");
			co.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			
			
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			Data.driver = new ChromeDriver();
			Data.driver.manage().window().maximize();
			break;
			
		case "firefox": 
			FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--ignore-certificate-error", "--dns-prefetch-disable", "--new-window",
					"--lan=en_US UTF-8" + "--disable-extensions");
			fo.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			Data.driver = new FirefoxDriver();
			Data.driver.manage().window().maximize();
			break;
			
		case "ie":
			InternetExplorerOptions ieo = new InternetExplorerOptions();
			ieo.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			System.setProperty("webdriver.ie.driver", "Drivers\\IEDriverServer.exe");
			Data.driver = new InternetExplorerDriver();
			Data.driver.manage().window().maximize();
			break;
			
		case "edge":
			EdgeOptions eo = new EdgeOptions();
			eo.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			System.setProperty("webdriver.edge.driver", "Drivers\\MicrosoftWebDriver.exe");
			Data.driver.manage().window().maximize();
			break;
			
		default:
			System.out.println("You entered wrong browser: " + browser);
			break;
		}

		Data.driver.get("https://www.phptravels.net/admin");
	}

//	@BeforeTest
	public static void closeBrowser() {

		try {
			Runtime.getRuntime().exec("Taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("Taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("tsskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEServerdriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM microsoftedge.exe");
			Runtime.getRuntime().exec("taskkill /F /IM edgedriver");

		} catch (IOException e) {
			System.out.println("Exception while closing the browsers.");
		}
	}

	public static void deleteCookie() {

		Data.driver.manage().deleteAllCookies();

	}

	public static void close() {
		Data.driver.close();
	}
	
 }

//DesiredCapabilities dc = DesiredCapabilities.chrome();
//dc.setCapability(ChromeOptions.CAPABILITY, co);
//Data.driver = new RemoteWebDriver(dc);

//FirefoxProfile fp = new FirefoxProfile();
//// set something on the profile...
//DesiredCapabilities dc1 = DesiredCapabilities.firefox();
//dc1.setCapability(FirefoxDriver.PROFILE, fp);
//Data.driver = new RemoteWebDriver(dc1);
