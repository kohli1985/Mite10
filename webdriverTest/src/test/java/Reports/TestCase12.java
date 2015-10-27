package Reports;

public class TestCase12 {

	
	
	private String testStepDesc1;
	private String testStepKeyword;
	private String testStepResult;
	
	
	TestCase12()
	{
		
	}
	
	public TestCase12(String testStepDesc1, String testStepKeyword,
			String testStepResult) {
		
		this.testStepDesc1 = testStepDesc1;
		this.testStepKeyword = testStepKeyword;
		this.testStepResult = testStepResult;
	}
	public String getTestStepDesc1() {
		return testStepDesc1;
	}
	public void setTestStepDesc1(String testStepDesc1) {
		this.testStepDesc1 = testStepDesc1;
	}
	public String getTestStepKeyword() {
		return testStepKeyword;
	}
	public void setTestStepKeyword(String testStepKeyword) {
		this.testStepKeyword = testStepKeyword;
	}
	public String getTestStepResult() {
		
		
		return testStepResult;
	}
	public void setTestStepResult(String testStepResult) {
		this.testStepResult = testStepResult;
	}
	
	
}
