package Reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import tests.DriverScript;



public class htmlReport extends DriverScript {

	
	static PrintStream printhtml;
	public static void startTestSuite() throws FileNotFoundException {
		
		
		
		File file = new File(System.getProperty("user.dir")+ "//src//Output","Index.html");
		 	
		OutputStream htmlfile= new FileOutputStream(file);
		 printhtml  = new PrintStream(htmlfile);
		
			
				
		printhtml.append("<html>");
		printhtml.append("<head>");
		printhtml.append("<Title>");
		printhtml.append("Test Automation Report");
		printhtml.append("</Title>");
		printhtml.append("</head>");
		printhtml.append("<Body>");
		printhtml.append("<H1>");
		printhtml.append("<center>" +"Test Report");
		printhtml.append("</H1>");
		printhtml.append("<th> <b> Test Env. Details </th>");
		printhtml.append("<table border=1 cellpadding=2 cellspacing=0>");
		
		printhtml.append("<tr>");
		printhtml.append("<td> Test Env </td>");
		printhtml.append("<td>" + config.getProperty("env")+  "</td>");
		printhtml.append("</tr>");
		
		printhtml.append("<tr>");
		printhtml.append("<td> Browser </td>");
		printhtml.append("<td>" + config.getProperty("browser")+ "</td>");
		printhtml.append("</tr>");
		
		printhtml.append("<tr>");
		printhtml.append("<td> Test start time </td>");
		
		printhtml.append("<td>" + startTime + "</td>");
		printhtml.append("</tr>");
		printhtml.append("<tr>");
		printhtml.append("<td> Test end time </td>");
		printhtml.append("<td> " + endTime1 + " </td>");
		printhtml.append("</tr>");
		printhtml.append("</table>");
		
		printhtml.append("<H2>");
		printhtml.append("<th> <b> Test Suites </th>");
		printhtml.append("</H2>");
		
		printhtml.append("<table border=1 cellpadding=2 cellspacing=0>");
		printhtml.append("<tr> ");
		printhtml.append("<td> <b>Test Case ID </b> </td>");
		printhtml.append("<td> <b>Test Case Description </b> </td>");
		printhtml.append("<td> <b>Result</b> </td>");
		printhtml.append("</tr> ");
		
				
		for (int i= 0;i<objOfList.size();i++) {
			if(objOfList.get(i).getTestCaseResult1().equalsIgnoreCase("Fail"))
			{
			printhtml.append("<tr style='background-color:red;'> ");
			
			}
			else if(objOfList.get(i).getTestCaseResult1().equalsIgnoreCase("Pass"))
			{
				printhtml.append("<tr style='background-color:green;'> ");
			}
			
			printhtml.append("<td style='width:200px;'> <a href ="+testCaseId1+".html>" +objOfList.get(i).getTestCaseId1()+ "</a> </td>");
			printhtml.append("<td style='width:200px;'>" + objOfList.get(i).getTestCaseDescription1()+ " </td>");
			printhtml.append("<td style='width:200px;'>"+ objOfList.get(i).getTestCaseResult1() + "</td>");
			printhtml.append("</tr> ");
			}	
		
		
		
		
		printhtml.append("</table>");
		printhtml.append("</body>");
		printhtml.append("</html>");
		
		printhtml.toString();
		printhtml.close();
	
	}

	
	
	
	
	
	public static  void startTestCase(String tcid) throws FileNotFoundException
	{
		File file = new File(System.getProperty("user.dir")+ "//src//Output",""+ tcid +".html");
		OutputStream htmlfile= new FileOutputStream((file));
        PrintStream printhtml = new PrintStream(htmlfile);
   
		printhtml.append("<html>");
		printhtml.append("<head>");
		printhtml.append("<Title>");
		printhtml.append("Test Automation Report");
		printhtml.append("</Title>");
		printhtml.append("</head>");
		printhtml.append("<Body>");
		printhtml.append("<H1>");
		printhtml.append("<center>" +"Test Report");
		printhtml.append("</H1>");
		
		
		
		printhtml.append("<table border=1 cellpadding=2 cellspacing=0>");
		printhtml.append("<tr> ");
		printhtml.append("<td> <b> TestSteps</b> </td>");
		printhtml.append("<td> <b>Test Step Description</b> </td>");
		printhtml.append("<td> <b>Keyword</b> </td>");
		printhtml.append("<td> <b>Result</b> </td>");
		printhtml.append("</tr> ");
		
			
		
		for (int i=0;i<objList.size();i++) {
		
		
		if(objList.get(i).getTestStepResult().equalsIgnoreCase("Fail"))
		{
		printhtml.append("<tr style='background-color:red;'> ");
		printhtml.append("<td> TS"+ (i+1) + "</td>");
		printhtml.append("<td>"+ objList.get(i).getTestStepDesc1()+  "</td>");
		printhtml.append("<td>"+ objList.get(i).getTestStepKeyword() + "</td>");
		printhtml.append("<td> <a href ="+testCaseId1+".jpg>" +objList.get(i).getTestStepResult()+ "</a> </td>");
		
		
		}
		else if(objList.get(i).getTestStepResult().equalsIgnoreCase("Pass"))
		{
			printhtml.append("<tr style='background-color:green;'> ");
			printhtml.append("<td> TS"+ (i+1) + "</td>");
			printhtml.append("<td>"+ objList.get(i).getTestStepDesc1()+  "</td>");
			printhtml.append("<td>"+ objList.get(i).getTestStepKeyword() + "</td>");
		    printhtml.append("<td>"+ objList.get(i).getTestStepResult() +"</td>");
		}
		
		
		printhtml.append("</tr> ");
		}
		
		
		printhtml.append("</table>");
		printhtml.append("</body>");
		printhtml.append("</html>");
		
		printhtml.toString();
		printhtml.close();
	
	}
	
	
		   
	    
	
	
}