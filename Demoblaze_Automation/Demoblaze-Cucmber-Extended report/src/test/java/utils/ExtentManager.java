package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");

            // ══════════════════════════════════════════════════════════════
            // CONFIGURATION - Light Theme
            // ══════════════════════════════════════════════════════════════
            sparkReporter.config().setTheme(Theme.STANDARD); // ← Light theme
            sparkReporter.config().setDocumentTitle("Demoblaze Test Report");
            sparkReporter.config().setReportName("Automation Test Results");
            sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
            sparkReporter.config().setEncoding("UTF-8");

            // Custom CSS for better screenshot display
            sparkReporter.config().setCss(
                    "img { max-width: 100%; height: auto; border: 2px solid #ddd; border-radius: 4px; }"
            );

            // ══════════════════════════════════════════════════════════════
            // EXTENT REPORTS SETUP
            // ══════════════════════════════════════════════════════════════
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // System Information
            extent.setSystemInfo("Application", "Demoblaze E-commerce");
            extent.setSystemInfo("Environment", "Test");
            extent.setSystemInfo("Browser", "Firefox");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }
}