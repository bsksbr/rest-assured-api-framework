package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

/**
 * Implements TestNG's ITestListener to generate ExtentReports for test
 * execution.
 */
public class TestListener implements ITestListener {
	private static ExtentReports extent = ExtentManager.getExtentReportInstance(); // Get the ExtentReports instance
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	/**
	 * Invoked each time before a test will be invoked. The <code>ITestResult</code>
	 * is only partially filled with the references to class, method, start millis
	 * and status.
	 *
	 * @param result the partially filled <code>ITestResult</code>
	 * @see ITestResult#STARTED
	 */
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	/**
	 * Invoked each time a test succeeds. Called when a test passed. Logs the success in the report.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SUCCESS
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS,"Test Passed: "+ result.getMethod().getMethodName());
	}

	/**
	 * Invoked each time a test fails. Called when a test fails. Logs failure details in the report.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#FAILURE
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		 test.get().log(Status.FAIL,"Test Failed: "+result.getMethod().getMethodName());
		 test.get().log(Status.FAIL,result.getThrowable().getMessage()); // Log Exception message
	}

	/**
	 * Invoked each time a test is skipped. Called when the test is skipped. Logs the skip status in the report.
	 *
	 * @param result <code>ITestResult</code> containing information about the run
	 *               test
	 * @see ITestResult#SKIP
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test Skipped: "+result.getMethod().getMethodName());
	}

	/**
	 * Invoked after all the test methods belonging to the classes inside the
	 * <test> tag have run and all their Configuration methods have been
	 * called.
	 *
	 *Called after all tests are completed. Flushes the report to save it.
	 * @param context The test context
	 */
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
