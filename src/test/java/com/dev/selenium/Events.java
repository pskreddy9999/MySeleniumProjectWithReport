package com.dev.selenium;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Events {

	private static final ThreadLocal<Events> Ev = new ThreadLocal<>();

	public static ThreadLocal<Events> get() {
		return Ev;
	}

	public static void set(Events events) {
		Ev.set(events);
	}

	// *****************************************************************************
	public static void takeScreenShot(String screenShotName, String stepName) {

		File screenshot = ((TakesScreenshot) Data.driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(screenshot,
					new File(ConfigData.GetData("ScreenShotPath") + screenShotName + timestamp() + ".jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	// **********************************************************
	public static String timestamp() {

		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	// ************************************************************

	public static WebElement waitForElementToDisplay(By by, int maxTime) {

		WebElement elem = null;
		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, maxTime);
			wait.pollingEvery(Duration.ofMillis(maxTime));
			elem = wait.until(ExpectedConditions.visibilityOfElementLocated(by)); // elem
		} catch (Exception e) {
			System.out.println("Element with locator: " + by.toString()
					+ " is not displayed even after the waiting for: " + maxTime);
		}
		return elem;
	}

	// *****************************************************************************
	public static WebElement waitForElemToDisplay(By by, int maxTime) {

		WebElement elem = null;

		try {
			WebDriverWait wait = new WebDriverWait(Data.driver, maxTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("Element with locator: " + by.toString() + "is not dispaled even after the waiting for: "
					+ maxTime);
		}
		return elem;
    
		// WebElement myDynamicElement = (new WebDriverWait(Data.driver, 10))
		// .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
	}
 
	// ************************************************************************************
	public static void highlightElement(By by, String color, String stepName) {

		WebElement ele = Data.driver.findElement(by);

		JavascriptExecutor jse = (JavascriptExecutor) Data.driver;
		jse.executeScript("arguments[0].style.border='3px solid " + color + "'", ele);
	}

	// **************************************************************************************
	public static WebElement enterValues(By by, String strValue, String stepname) {
		WebElement elem = Data.driver.findElement(by);
		try {
			elem.clear();
			elem.sendKeys(strValue);
		} catch (NoSuchElementException nsee) {
			System.out.println(stepname + " no element find in application with given locator; " + by.toString());
		}
		return elem;

	}

	// ********************************************************************************************
	public static WebElement clickElement(By by, String stepName) {

		WebElement elem = waitForElementToDisplay(by, 40);
		try {
			if (elem.isEnabled()) {
				elem.click();
			} else {
				System.out.println("Failed: " + stepName + ": Unable to click the emenent is not displayed.");
			}
		} catch (NullPointerException npe) {
			Assert.fail("Test has been failed");
		}
		return elem;
	}

	public static void getTxtForValidate(By by, String strTxt, String stepName) {

		WebElement elem = clickElement(by, stepName);
		String str = elem.getText();

		if (str.equalsIgnoreCase(strTxt)) {
			System.out.println("Pass: Login is successfull with expected and actual text matched.");
		} else {
			System.out.println("Fail: Login is unsuccessful with expected and actual are not matched");
		}
	}

	// ***********************************************************
	public static boolean verifyElementPresentOrNot(By by) {

		List<WebElement> elem = Data.driver.findElements(by);
		if (elem.size() != 0) {
			return false;
		} else {
			return true;
		}
	}

	// ******************************************************************************
	public static void verifyAdminName(By by, String actualText) {

		WebElement elem = Data.driver.findElement(by);
		String expectedText = elem.getText();

		if (expectedText.equals(actualText)) {
			System.out.println(
					"PASS: Admin which loged in is : " + expectedText + " and actaul result is: " + actualText);
		} else {
			System.out.println("FAIL: Admin loged in is not same: " + expectedText + " and actual result are no same: "
					+ actualText);
		}
	}

	// *************************************************************************************
	public static boolean verifyAdminByAssert(By by, String actualres) {
		WebElement elem = Data.driver.findElement(by);
		String expResult = elem.getText();
		Assert.assertEquals(expResult, actualres);
		System.out.println("PASS:  Expected Result is : " + expResult + " and actual result is; " + actualres);
		return false;

	}

	// *********************************************************************************************
	public static void parentWindow() {

		String parentWin = Data.driver.getWindowHandle();
		System.out.println("Parent window name : " + parentWin);
		Data.driver.switchTo().window(parentWin);
	}

	// ******************************************************
	public static WebElement getTitleOfWindow(By by) {

		WebElement elem = Data.driver.findElement(by);
		try {
			if (elem.isEnabled()) {
				elem.click();

				Set<String> allWindows = Data.driver.getWindowHandles();
				for (String window : allWindows) {
					Data.driver.switchTo().window(window);
					Data.driver.manage().window().maximize();
					System.out.println("Title of current window is: " + Data.driver.getTitle());
				}
			} else {
				System.out.println("Failed: Unable to click the element and it is in disable mode : " + by.toString());
			}

			// parentWindow();
		} catch (NoSuchElementException nsee) {
			System.out.println("Element is not find: " + by.toString());
		}

		return elem;

	}

	// *********************************************************************
	public static void switchToWindowHandle(String windowName) {

		Set<String> allWindows = Data.driver.getWindowHandles();
		System.out.println("All windows size : " + allWindows.size());

		for (String window : allWindows) {
			Data.driver.switchTo().window(window);
			String allTitles = Data.driver.getTitle();
			System.out.println(allTitles);

			if (allTitles.contains(windowName)) {
				Data.driver.switchTo().window(allTitles);
				Data.driver.manage().window().maximize();
				// Data.driver.close();
			}
		}
	}
//**********************************************************************	
	public static void checkBox(By by,String strVal){
		
		List<WebElement> checkBox = Data.driver.findElements(by);
		
		for(int i=0;i<checkBox.size();i++){
			WebElement elem = checkBox.get(i);
				String str = elem.getAttribute("value");
				if(str.equalsIgnoreCase(strVal)){
					elem.click();
			}
		}
	}
	
	public static void webTable(By by){
		
		WebElement table = Data.driver.findElement(by);
		
		List<WebElement> allRows= table.findElements(By.tagName("tr"));
		for(int i=0;i<allRows.size();i++){
			
			List<WebElement> allCols= allRows.get(i).findElements(By.tagName("td"));
			
			System.out.println(allRows);
			for(int j=0;j<allCols.size();j++){
				String str = allCols.get(j).getText();
				Data.driver.findElement(by).click();
			}
			System.out.println(allCols);
		}		
	}

	// **********************************************************************************
	public static void forSingleElement(By by, String screenShotName, String stepName) {

		WebElement ele = Data.driver.findElement(by);
		File src = ((TakesScreenshot) Data.driver).getScreenshotAs(OutputType.FILE);

		try {
			BufferedImage bi = ImageIO.read(src);
			Point point = ele.getLocation();

			int imgWidth = ele.getSize().getWidth();
			int imghight = ele.getSize().getHeight();

			BufferedImage bimage = bi.getSubimage(point.getX(), point.getY(), imgWidth, imghight);

			ImageIO.write(bimage, "jpg	", src);
			File screenShotLocation = new File(ConfigData.GetData("ScreenShotPath") + screenShotName + ".jpg ");
			FileUtils.copyDirectory(src, screenShotLocation);
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	public static void selectDate(String date) {

		String startDate = date;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dtDate = null;
		try {
			dtDate = sdf.parse(startDate);

			Calendar c = Calendar.getInstance();
			c.setTime(dtDate);

			int day = c.get(Calendar.DAY_OF_MONTH);
			int year = c.get(Calendar.DAY_OF_YEAR);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public static void getTextFromElement(By by, String stepName) {

		WebElement elem = waitForElementDisplay(by, stepName);
	}

	public static WebElement waitForElementDisplay(By by, String stepName) {

		WebElement elem = null;

		return elem;

	}
}
