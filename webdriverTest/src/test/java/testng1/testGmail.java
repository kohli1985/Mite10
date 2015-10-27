package testng1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testGmail {

	WebDriver driver= null;
	
	@BeforeTest
	public void testServer()
	{
		driver= new FirefoxDriver();
	}
	
	@Test
	public void testGmail()
	{
		
		driver.get("http://www.gmail.com");
	}
	
	
	
}
