package seleniumFrameworkDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		
		String reportPath = System.getProperty("user.dir") + "\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arun");
		return extent;
	}

}
