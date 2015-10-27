package tests;



import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;









import Reports.TestCase11;
import Reports.TestCase12;
import Reports.htmlReport;
import dd_xlreader.Xls_Reader;

public class DriverScript {

	public static WebDriver driver=null;
	
	public static Properties config = new Properties();

	public static Properties or = new Properties();

	public static Properties appTexts = new Properties();
	
	public static Properties email = new Properties();

	public static Logger logs = Logger.getLogger("Gmail");

	public static Xls_Reader excel = null;
	
	
	
	String testCaseResult = "";
	
	
	

	String testStepdescription = null;
	String keyword = null;
	static String object = null;
	static String testData = null;
	protected static String testCaseId1;
	
	String testStepResult = "";
	String testCaseResult1="";
	
	TestCase11 obj;
	TestCase12 obje;
	Keywords keywords;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	protected static String startTime;
	protected static String endTime1;
	
	protected static List <TestCase11> objOfList = new ArrayList<TestCase11>();
	protected static List <TestCase12> objList = new ArrayList<TestCase12>();
	
	static OutputStream out;
	
	@BeforeTest
	public static void testConfig() {
		try {
			FileInputStream fis;

			excel = new Xls_Reader(System.getProperty("user.dir")
				+ "//src//Core//controller.xls");
			
      
			 fis = new FileInputStream(System.getProperty("user.dir")+ "//src//Core//config.properties");
							
			config.load(fis);

			
			
			fis = new FileInputStream(System.getProperty("user.dir")
					+ "//src//Core//objects.properties");
			or.load(fis);

			fis = new FileInputStream(System.getProperty("user.dir")
					+ "//src//Core//appText.properties");
			appTexts.load(fis);
			
			
			fis = new FileInputStream(System.getProperty("user.dir")
					+ "//src//Core//customer.properties");
			email.load(fis);
			
			 out = new FileOutputStream(System.getProperty("user.dir")
						+ "//src//Core//customer.properties" );
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void StartExecution() throws IOException {

		keywords = new Keywords();
	
		
		startTime= dateFormat.format(cal.getTime());
		
		for (int rownum = 2; rownum <= excel.getRowCount("Suite"); rownum++) 
		{
			
			
			if (excel.getCellData("Suite", "RunMode", rownum).equalsIgnoreCase(
					"Y")) 
			{

			    String	testCaseId = excel.getCellData("Suite", "Testid", rownum);
				logs.debug("testcaseid:" + testCaseId);

			    String	testCaseDescription = excel.getCellData("Suite", "Description",
						rownum);
				logs.debug("testCaseDescription:" + testCaseDescription);
				
				  testCaseId1 = testCaseId;
				 String testCaseDescription1 = testCaseDescription;
				
				 
				for (int row = 2; row <= excel.getRowCount(testCaseId); row++)
				{
				    
					testStepdescription = excel.getCellData(testCaseId, "Description",
							row);
					keyword = excel.getCellData(testCaseId, "Keyword", row);
					object = excel.getCellData(testCaseId, "Object", row);
					testData = excel.getCellData(testCaseId, "TestData", row);
					
                    String testdescription1 = testStepdescription;
					String testKeyword1 = keyword;
					
					
					try {
						Method method = keywords.getClass().getMethod(keyword);
						method.setAccessible(true);
						testStepResult = (String) method.invoke(keywords);
						logs.debug("TestStepResult:" + testStepResult);
						} catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					
					obje = new TestCase12(testdescription1,testKeyword1,testStepResult);
					objList.add(obje);
					htmlReport.startTestCase(testCaseId);
					if(testStepResult.equalsIgnoreCase("Fail"))
					{
					takeScreenShot(testCaseId);
					
					break;
					}
			    }
				
				 if(testStepResult.equals("Pass"))
				 {
					 testCaseResult= "Pass";
				 }
				 else
				 {
					 testCaseResult= "Fail";
				 }
				 testCaseResult1 = testCaseResult;
				 obj = new TestCase11(testCaseId1, testCaseDescription1,testCaseResult1);
				 objOfList.add(obj);   
				 htmlReport.startTestSuite();   
				 objList.clear();
			}
			
		}
		
	//	driver.close();
		
	}



	public static void takeScreenShot(String fileName) {
		File scrFile = ((org.openqa.selenium.TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\src\\Output\\"+fileName+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
