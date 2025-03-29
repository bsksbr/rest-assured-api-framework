package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * A TestListener is a powerful mechanism used in test automation frameworks to track, modify, and control test execution events.
 * 
 * TestListener is a TestNG Listener that integrates:
 * - **Logging (SLF4J & Log4j2)** → Console & Log Files
 * - **Reporting (Extent Reports)** → HTML Report
 * 
 * This ensures that test execution details are logged both in:
 * => Console (via SLF4J Logger)  
 * => HTML Report (via Extent Reports)
 */

public class TestListener implements ITestListener {
	
	// Single Instance of Extent Reports (Managed by ExtentManager)
	private static ExtentReports extent = ExtentManager.getExtentReportInstance();
	// ThreadLocal Ensures logs don't get mixed in parallel execution 
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	// Logger for Console Logging
	private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
	
	
	 /**
     * Called when the <Test Suite> starts.
     * @param context TestNG execution context
     */
	@Override
	public void onStart(ITestContext context) {
		logger.info("==> Text Execution Started: {} ",context.getName());
	}
	/**
	 * Called <before each test method> execution. 
	 * creates new test node in the ExtentReport.
	 * 
	 * @param result details of the test method.
	 */
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("TestListener onStart triggered...");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
		logger.info("<===  Test Started: {}",result.getMethod().getMethodName());
	}
	
	/**
	 * Called when the test method <PASSED>
	 * Logs the success status in both Console & Extent Report.
	 * @param result Details of the test method.
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		String testPassMessage = "==> Test PASSED: "+ result.getMethod().getMethodName();
		test.get().log(Status.PASS,testPassMessage); // Log in Extent Report
		logger.info(testPassMessage); // Log in Console
	}
	
	/**
	 * Called when a test method <FAILS>
	 * Logs the failure reason in both Console & Extent Report.
	 * @param result Details of the test method
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		 String testFailMessage = "Test Failed: "+result.getMethod().getMethodName();
		 // Log failure details in Extent Report ()with error stack trace
		 test.get().log(Status.FAIL,testFailMessage +"  -  "+result.getThrowable().getMessage());
		 // Log failure details in Console		 
		 logger.error(testFailMessage, result.getThrowable().getMessage());
	}

	/**
	 *Called when a test method is <SKIPPED>
	 *Logs the Skipped status in both Console & Extent Report
	 *@param result Details of the test method 
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		String testSkipMessage = "Test Skipped: "+result.getMethod().getMethodName();
		test.get().log(Status.SKIP, testSkipMessage);
		logger.warn(testSkipMessage);
	}

	/**
	 * Called When test <Test Suite> execution is Completed.
	 * Flushes Extent Reports to generate the final HTML report.
	 * @param context TestNG execution context
	 * 
	 */
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Execution Completed ===> ");
		extent.flush(); // Generate Final Extent Report 
		logger.info(" Test Execution Completed: {}", context.getName());
	}

	/**
	 * Returns the current test's ExtentTest instance
	 * Used to log the details into Extent Reports from other classes.
	 * @return ExtentTest instance 
	 * 
	 * */
	public static ExtentTest getExtentTest() {
		return test.get();
		
	}
}
