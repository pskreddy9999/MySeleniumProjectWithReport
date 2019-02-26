package seleniumMain;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.dev.selenium.Pages;

public class Tests {
	
	
	
	@Parameters("browser")
	@Test
	public static void tcr(@Optional String browser) throws InterruptedException{
		
		
		browser = (browser==null)?"chrome":browser;// This for default browser( Now we have chrome is default)
		
//		Pages.loginPage();
		
//		Pages.LoginAfterWindowHandle();
		
		Pages.selectDate();
	}
}
