package tests;







import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;






import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;





public class Keywords extends DriverScript {

	
	
	String Browser =null;
	
	static Random random;
	
	static int MaxTime= 1000;
	
	static int time =3000;

	static String locatorId= "id";
	
	static String locatorXpath ="xpath";
	
	static String locatorClass= "class";
	
	static String locatorName ="name";
	
	String val=null;

		
	public  String launchURL()
	{
	
		logs.info("Executing the launchURL function");
		try
		{
		
			launchlocalURL();
			driver.manage().window().maximize();
			driver.navigate().to(config.getProperty("testsite"));
	//		driver.navigate().refresh();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			return "Pass";
			
		}catch(Exception e)
		{   
			
			return "Fail";
			
		}
		
		
	}
		
	// launch Url
	public static void launchlocalURL()
	{   DesiredCapabilities  capabilities = null;
		
		try
		{
			
		 if(config.getProperty("browser").equalsIgnoreCase("firefox"))
		 { 
			 
		 driver = new FirefoxDriver();
		 }
		 else if(config.getProperty("browser").equalsIgnoreCase("internet explorer"))
		 {
			 System.setProperty("webdriver.ie.driver", "E:\\IEDriverServer.exe");
			 driver = new InternetExplorerDriver();
		 }
		 else if(config.getProperty("browser").equalsIgnoreCase("chrome"))
		 {
			 System.setProperty("webdriver.chrome.driver", "D:\\Lib\\chromedriver.exe");
			  driver = new ChromeDriver();
		 }
		 else if(config.getProperty("browser").equalsIgnoreCase("appium"))
		 {
			    capabilities = new DesiredCapabilities();
				capabilities.setCapability("automationName","Appium");
				capabilities.setCapability("platformName","Android");
				capabilities.setCapability("platformVersion", "4.4.2");
				capabilities.setCapability("deviceName", "AVD_for_Nexus_5_by_Google");
				capabilities.setCapability("udid", "emulator-5554");  				  
				capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("appium-version","1.3.4.1");
				
				try {
					driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
		 }
		 else
		 {
			 logs.debug("Invalid browser");
		 }
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	//Verify Text
	public String verifyText()
	{
		String expected =null;
		String actual =null;
		logs.debug("Executing the verifyText function");
		try
		{
		 logs.debug("Object:"+ object);
		 logs.debug("Address: "+ or.getProperty(object));
		 
		 if(object.contains("id"))
		 {
			 
			 actual = driver.findElement(By.id(or.getProperty(object))).getText();
		 }
		 else if (object.contains("xpath"))
		 {
			 actual = driver.findElement(By.xpath(or.getProperty(object))).getText();
		 }
		 else if(object.contains("class"))
		 {
			 actual = driver.findElement(By.className(or.getProperty(object))).getText();
		 }
		 else if(object.contains("name"))
		 {
			 actual = driver.findElement(By.name(or.getProperty(object))).getText();
		 }
		 
		 
		 expected = appTexts.getProperty(object);
		 if(actual.equalsIgnoreCase(expected))
		 { 
		 
			return "Pass";
		 }
		 else
		 {
			 return "Fail";
		 }
		}catch(Exception e)
		{
		return "Fail";
		}
		
	}
	
	//clickLinkButton
	public String clickLinkButton()
	{
		logs.debug("Executing the clickLinkButton function");
		WebElement element = null;
		try
		{
		 logs.debug("Object:"+ object);
		 logs.debug("Address: "+ or.getProperty(object));
		 
		 if(object.contains("id"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorId );
		 }
		 else if(object.contains("xpath"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorXpath );
		 }
		 else if(object.contains("class"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorClass);
		 }
		 else if(object.contains("name"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorName );
		 }
		 else
		 {
			 logs.debug("Invalid Locator used to find the element");
		 }
				
		 if(element.isDisplayed())
		 {
			 element.click();
			 return "Pass";
		 }
		 
		}catch(Exception e)
		{ 
			e.getStackTrace();
			return "Fail";
			
		}
		return "Pass";
		
	}
	
	
	//Input Text
	public static String inputText() throws IOException
	{
				
		logs.debug("Executing the inputText");
		WebElement element = null;
		try
		{
		 logs.debug("Object:"+ object);
		 logs.debug("Address: "+ or.getProperty(object));
		 logs.debug("Testdata: "+ testData);
		 
		 if(object.contains("id"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorId );
		 }
		 else if(object.contains("xpath"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorXpath );
		 }
		 else if(object.contains("class"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorClass);
		 }
		 else if(object.contains("name"))
		 {
			 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorName);
		 }
		 else
		 {
			 logs.debug("Invalid Locator used to find the element");
		 }
		 
		 if(element.isDisplayed())
		 {
			if(config.getProperty(testData)!=null)
			{
				element.sendKeys(config.getProperty(testData));
				 return "Pass";
			}
			else if(email.getProperty("customerEmail")!=null)
			{
				element.sendKeys(email.getProperty("customerEmail"));
				 return "Pass";
			}
			else
			{
			 element.sendKeys(testData);
			 return "Pass";
			}		
			
		 }
		 else
		 {
			 return "Fail";
		 }
		 
		}catch(Exception e)
		{
		e.printStackTrace();
		return "Fail";
		}
		
		
		
	
		
	
		
	}
	
	
	public String selectDropDownByOption()
	{
		
		
		logs.debug("Executing the selectDropDownByOption");
		
		try
		{    
			 logs.debug("Object:"+ object);
			 logs.debug("Address: "+ or.getProperty(object));
			 logs.debug("TestData: "+ testData);
			 if(object.contains("id"))
			 {
				 Select select = new Select(driver.findElement(By.id(or.getProperty(object))));
				 List<WebElement>  list =  select.getOptions();
				 for(WebElement element : list)
				    {
				       String v1=   element.getText();
				       if(v1.equalsIgnoreCase(testData))
				       {
				    	   element.click();
				    	   
				       }
				    }
				 
			 }
			 else if(object.contains("xpath"))
			 {
				 Select select = new Select(driver.findElement(By.xpath(or.getProperty(object))));
				 List<WebElement>  list =  select.getOptions();
				 for(WebElement element : list)
				    {
				       String v1=   element.getText();
				       if(v1.equalsIgnoreCase(testData))
				       {
				    	   element.click();
				    	   
				       }
				    }
			 }
			 
			 return "Pass"; 
		}catch(Exception e)
		{
			return "Fail";
		}
		
				
	}
	
	
	public String getCurrentUrl()
	{
		
		return null;
				
	}
	
	
	public static WebElement waitForElementToLoad(final WebDriver driver, final int waitTime, final String elementAddress, final String locator )
	{
		WebDriverWait wait = new WebDriverWait(driver ,MaxTime);
		WebElement element =null;
		if(locator.equalsIgnoreCase("id"))
		 {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementAddress)));
		 }
		else if(locator.equalsIgnoreCase("xpath"))
		{
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementAddress)));
		}
		else if(locator.equalsIgnoreCase("class"))
		{
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(elementAddress)));
		}
		else if(locator.equalsIgnoreCase("name"))
		{
			element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(elementAddress)));
		}
				
		return element;
	}
	
	
	 /**addItem.
	 *
	 * @return String
	 */
	/*
	public static String addItem() {
	    try {
	        logs.debug("Execute addItem function ");
	        String[] arr = dataColumnName.split("#");
	        String quantity = arr[0];
	        String item = cONFIG.getProperty(arr[1]);
	        logs.debug("Item quantity : " + quantity);
	        logs.debug("Item SKU : " + item);
	      //  ActionDriver.click(By.xpath(
	             //   oR.getProperty("link_addItem_xpath")));
	        Thread.sleep(WAITFORONESECOND);
	        ActionDriver.type(By.xpath(oR.getProperty(
	        "input_quantity_xpath")), quantity);
	        ActionDriver.type(By.xpath(oR.getProperty(
	        "input_itemId_xpath")), item);
	        ActionDriver.click(By.xpath(
	                oR.getProperty("button_addItem_xpath")));
	       // ActionDriver.click(By.xpath(
	              //  oR.getProperty("salesOrderPage_doneButton_xpath")));
	        return "Pass";
	    } catch (Exception t) {
	        logs.debug("Error in addItem function " + t.getMessage());
	        return "Fail->" + t.getMessage();
	    }
		
*/	
	
	
	public  static String isElementPresent()
	{
		
		logs.debug("Executing the isElementPresent");
		int size = 0;
		try
		{
			 logs.debug("Object:"+ object);
			 logs.debug("Address: "+ or.getProperty(object));
			 
			 if(object.contains("id"))
			 {
		     size = driver.findElements(By.id(or.getProperty(object))).size();
			 }
			 else if(object.contains("xpath"))
			 {
				  size = driver.findElements(By.xpath(or.getProperty(object))).size();
			 }
			 else if(object.contains("class"))
			 {
				  size = driver.findElements(By.className(or.getProperty(object))).size();
			 }
			 else if(object.contains("name"))
			 {
				  size = driver.findElements(By.name(or.getProperty(object))).size();
			 }
			 else
			 {
				 logs.debug("Invalid Locator used to find the element");
			 }
			 
			 
			 if(size > 0 )
			 {
				 return "Pass";
			 }else
			 {
				 return "Fail";
			 }
				
			
			
		}catch(Exception e)
		{
			return "Fail-->";
		}
		
		
	}
	
	
	
	public String waitTime()
	{
		logs.debug("Executing the waitTime");
		try
		{
		
			Thread.sleep(time);
			
			return "Pass";
		}catch(Exception e)
		{
			return "Fail";
		}
		
		
	}
	
	

	
	
	
	public String mouseHover()
	{

		logs.debug("Executing the mouseHover");
		
		try
		{
			 logs.debug("Object:"+ object);
			 logs.debug("Address: "+ or.getProperty(object));
			 
			 if(object.contains("id"))
			 {
			    WebElement element = driver.findElement(By.id(or.getProperty(object)));
				Actions action = new Actions(driver);
				action.moveToElement(element).perform();
			 }
			 else if(object.contains("xpath"))
			 {
				    WebElement element = driver.findElement(By.xpath(or.getProperty(object)));
					Actions action = new Actions(driver);
					action.moveToElement(element).perform();
			 }
			
			return "Pass";
		}catch(Exception e)
		{
			return "Fail";
		}
		
	}
	
	
	
	public String createEmail()
	{
		logs.debug("Executing the createEmail function");
		WebElement element = null;
		val= Email();
		
		try
		{
			 logs.debug("Object:"+ object);
			 logs.debug("Address: "+ or.getProperty(object));
			 
			 if(object.contains("id"))
			 {
				 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorId );
			 }
			 else if(object.contains("xpath"))
			 {
				 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorXpath );
			 }
			 else if(object.contains("class"))
			 {
				 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorClass);
			 }
			 else if(object.contains("name"))
			 {
				 element= waitForElementToLoad(driver,MaxTime,or.getProperty(object), locatorName);
			 }
			 else
			 {
				 logs.debug("Invalid Locator used to find the element");
			 }
			 
			 if(element.isDisplayed())
			 {
				
					element.sendKeys(val);
					email.setProperty("customerEmail", val);
					email.store(out, null);
					 return "Pass";
			 }
			 else
			 {
				 return "Fail";
			 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return "Fail";
		}
		
		
	}
	
	
	
	
	public static String Email()
	{
	  
	String arr [] = {
			
	  "abc",
	  "cde",
	  "fgh",
	  "ijk",
	  "lmn",
	  "opq",
	  "rst",
	  "uvw",
	  "xyz",
	  "aa1",
	  "bb1",
	  "cc1",
	  "dd1",
	  "ee1",
	  "ff1",
	  "a11",
	  "abc1",
	  "asdf",
	  "xy12",
	  "a21",
	  "b41",
	  "cf1",
	  "dk1",
	  "ejd1",
	  "fds1",
	  "a1a",
	  "abc1",
	  "asdf",	  
	  "asd3f",
	  "xkj12",
	  "21f",
	  "bmnb1",
	  "cjkg",
	  "dkgf1",
	  "ejcd1",
	  "fdes1",
	  "a1na",
	  "abcc1",
	  "asedf"
	  		
	};
	  random =  new Random();
	  int len = arr.length;
	  String s1;
	  int i = random.nextInt(len);
	  s1= arr[i];
	  s1 = s1+"@"+s1+".com";
	  
	  return s1;
	  
	}
	
	
	
	public String wishListCount()
	{
		
		
		
		 try {
		        logs.debug("Execute wishListCount function ");
		        logs.debug("Object:"+ object);
		        logs.debug("TestData: "+ testData);
		        String[] arr = object.split("#");
		        String obj1= arr[0];
		        String obj2= arr[1];
		        String obj3= arr[2];
		        String obj4= arr[3];
		       int count =Integer.parseInt(testData);
		       logs.debug("Address1: "+ or.getProperty(obj1));
		       logs.debug("Address2: "+ or.getProperty(obj2));
		       logs.debug("Address3: "+ or.getProperty(obj3));
		       logs.debug("Address4: "+ or.getProperty(obj4));
		       
		       String oldval= driver.findElement(By.xpath(or.getProperty(obj4))).getText();
		       int precount=Integer.parseInt(oldval);
		       logs.debug("precount: "+precount );
		     
		        
		        for(int i= 1;i <=count;i++)
		        {
		        	WebElement element = driver.findElement(By.xpath(or.getProperty(obj1)+i+or.getProperty(obj2)));
					Actions action = new Actions(driver);
					action.moveToElement(element).perform();
					Thread.sleep(2000);
					driver.findElement(By.xpath(or.getProperty(obj3))).click();
					Thread.sleep(2000);
		        }
		        	
		        
		     val= driver.findElement(By.xpath(or.getProperty(obj4))).getText();
		     int newcount =   Integer.parseInt(val);
		     logs.debug("newcount: "+newcount );
		        if(count==(newcount-precount))
		        {
		        	return "Pass";
		        }else
		        {
		        	return "Fail";
		        }
		       		        
		    } catch (Exception t) {
		        
		        return "Fail";
		    }
		
		
	}
	
	
	
	
}
