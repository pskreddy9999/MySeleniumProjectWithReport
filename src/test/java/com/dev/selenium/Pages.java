package com.dev.selenium;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class Pages {
	
	
	private static final ThreadLocal<Pages> P = new ThreadLocal<>();
	
	public static ThreadLocal<Pages> get(){
		return P;
	}

	public static void set(Pages pages){
		
		P.set(pages);
	}
	public static void loginPage() {
				
		commons.launchbrowser("chrome");
	
		Events.highlightElement(By.xpath(ConfigData.GetData("userXpath")), ConfigData.GetData("color"), "kjiii");        
		Events.enterValues(By.xpath(ConfigData.GetData("userXpath")), ConfigData.GetData("username"),"entering user name");
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("forgetPass")),  40);
	  
	    
		Events.highlightElement(By.xpath(ConfigData.GetData("passXpath")), ConfigData.GetData("color1"), "kjiii");
		Events.enterValues(By.xpath(ConfigData.GetData("passXpath")), ConfigData.GetData("passWord"),"Entering password");
		
		
		Events.highlightElement(By.xpath(ConfigData.GetData("btnXpath")), ConfigData.GetData("color2"), "kkkm");
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("userXpath")),  40);
		Events.takeScreenShot("Login page2 ", "dd");
		
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("passXpath")),  40);
		Events.clickElement(By.xpath(ConfigData.GetData("btnXpath")), "click on login button");		

		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("LogoutXpath")),  40);
		
		Events.verifyAdminByAssert(By.xpath(ConfigData.GetData("adminXpath")), ConfigData.GetData("adminText"));
//		Events.verifyAdminName(By.xpath(ConfigData.GetData("adminXpath")), ConfigData.GetData("adminText"));
		Events.takeScreenShot("After Login page2 ", "cc");
		
		Events.clickElement(By.xpath(ConfigData.GetData("LogoutXpath")), "clicked on logout button");
		
//		commons.deleteCookie();
		commons.close();
	}
	
	public static void LoginAfterWindowHandle(){
		
		commons.launchbrowser(ConfigData.GetData("browser"));
		
		Events.highlightElement(By.xpath(ConfigData.GetData("userXpath")), ConfigData.GetData("color"), "kjiii");        
		Events.enterValues(By.xpath(ConfigData.GetData("userXpath")), ConfigData.GetData("username"),"entering user name");
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("forgetPass")),  40);
	  
	    
		Events.highlightElement(By.xpath(ConfigData.GetData("passXpath")), ConfigData.GetData("color1"), "kjiii");
		Events.enterValues(By.xpath(ConfigData.GetData("passXpath")), ConfigData.GetData("passWord"),"Entering password");
		
		
		Events.highlightElement(By.xpath(ConfigData.GetData("btnXpath")), ConfigData.GetData("color2"), "kkkm");
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("userXpath")),  40);
		Events.takeScreenShot("log in", "dd");
		
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("passXpath")),  40);
		Events.clickElement(By.xpath(ConfigData.GetData("btnXpath")), "click on login button");		
         Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("LogoutXpath")),  40);
		
		Events.verifyAdminByAssert(By.xpath(ConfigData.GetData("adminXpath")), ConfigData.GetData("adminText"));
		Events.takeScreenShot("After Log in ", "cc");
		
		//WebElement ele= Events.clickElement(By.linkText(ConfigData.GetData("frontend")), "Clicking on frontent link text");
		//String e = ele.toString();
		 Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("LogoutXpath")),  40);
		WebElement ele1 = Events.getTitleOfWindow(By.linkText(ConfigData.GetData("frontend")));
		
		Events.clickElement(By.xpath(ConfigData.GetData("offerLinkXpath")), "After switching to new window click the Home button");
		Events.takeScreenShot("Clid window1", "After switching to child window");
		commons.close();//It will close the current window...
		Events.parentWindow();
		
		Events.takeScreenShot("Parent window", "After switching to parent window");
		
		Events.clickElement(By.xpath(ConfigData.GetData("BookingLink")), "After closing the child window, again clicking in parent window Booking button");
		
		
	}
	
	public static void selectDate(){
		
		commons.launchbrowser(ConfigData.GetData("browser"));
		Events.highlightElement(By.xpath(ConfigData.GetData("userXpath")), ConfigData.GetData("color"), "kjiii");        
		Events.enterValues(By.xpath(ConfigData.GetData("userXpath")), ConfigData.GetData("username"),"entering user name");
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("forgetPass")),  40);
	  
	    
		Events.highlightElement(By.xpath(ConfigData.GetData("passXpath")), ConfigData.GetData("color1"), "kjiii");
		Events.enterValues(By.xpath(ConfigData.GetData("passXpath")), ConfigData.GetData("passWord"),"Entering password");
		
		
		Events.highlightElement(By.xpath(ConfigData.GetData("btnXpath")), ConfigData.GetData("color2"), "kkkm");
		Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("userXpath")),  40);
		Events.clickElement(By.xpath(ConfigData.GetData("btnXpath")), "click on login button");		
        Events.waitForElementToDisplay(By.xpath(ConfigData.GetData("LogoutXpath")),  40);
		
		Events.verifyAdminByAssert(By.xpath(ConfigData.GetData("adminXpath")), ConfigData.GetData("adminText"));
		
		Events.webTable(By.xpath(ConfigData.GetData("tableXpath")));
	}
}
