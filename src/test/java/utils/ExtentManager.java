/**
 * 
 */
package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Extent Manager is responsible for setting up and managing the ExtentReports instance 
 * It ensures that only a single instance is created (Singleton pattern).
 */
public class ExtentManager {
	private static ExtentReports extentReport; // Single instance of ExtentReports
	
	/** 
	 * Returns the ExtentReports instance.
	 * If it is not initialized, it creates a new instance.
	 * @return ExtentReports instance
	 * */
	public static ExtentReports getExtentReportInstance() {
		if (extentReport == null) {
			extentReport = createExtentReportInstance();
		}
		return extentReport;
	}

	/**
	 * Creates a new instances of ExtentReports with a unique report file for each test execution.
	 * @return ExtentReports instance
	 * */
	private static ExtentReports createExtentReportInstance() {
		//Generate unique folder name using the current timestamp
		
		String timeStamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String reportDir= "test-output/ExtentReports/"+timeStamp;
		
		
        //  Ensure the directory exists before creating the report
        File reportFolder = new File(reportDir);
        if (!reportFolder.exists()) {
            reportFolder.mkdirs(); // ✅ Creates folder if it doesn't exist
        }
		
        String reportPath = reportDir + "/extent-report.html";
        System.out.println("Report generated at: " + reportPath);
		//	Initialize ExtentSparkReporter to generate the report
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
		extentSparkReporter.config().setDocumentTitle("***** Simple Grocery Store API *****"); // Title of the HTML report
		extentSparkReporter.config().setReportName("Automation Test Results : "); // Report name
		extentSparkReporter.config().setTheme(Theme.STANDARD); // Set report Theme as STANDARD
		
		//	Create ExtentReports instance and attach the Spark Reporter.		
		ExtentReports extentReport=new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		return extentReport;
	}

}
