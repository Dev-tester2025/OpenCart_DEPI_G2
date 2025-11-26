package stepdefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ExtentManager;
import utils.ScreenRecorderUtil;
import utils.VideoConverter;

import java.io.File;

public class Hooks {

    public static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest scenarioLog;
    private static ScreenRecorderUtil recorder;
    private int stepCounter = 0;
    private String videoPath;

    @BeforeAll
    public static void setupExtentReport() {
        extent = ExtentManager.getInstance();

        // Create recordings directory
        new File("target/recordings").mkdirs();
    }

    @Before
    public void setupBrowser(Scenario scenario) {
        scenarioLog = extent.createTest(scenario.getName());
        stepCounter = 0;

        // Start Screen Recording
        try {
            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            recorder = ScreenRecorderUtil.startRecording(scenarioName);
            scenarioLog.info("🎥 Screen recording started");
        } catch (Exception e) {
            scenarioLog.warning("Failed to start recording: " + e.getMessage());
        }

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");

        scenarioLog.info("🌐 Browser opened and navigated to Demoblaze");
    }

    @AfterStep
    public void captureScreenshot(Scenario scenario) {
        stepCounter++;

        if (isAlertPresent()) {
            scenarioLog.warning("⚠️ Step " + stepCounter + ": Alert present - screenshot skipped");
            return;
        }

        try {
            String base64Screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);

            byte[] screenshotBytes = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshotBytes, "image/png", "Step " + stepCounter);

            String stepName = "📸 Step " + stepCounter + " Passed";

            if (scenario.isFailed()) {
                scenarioLog.fail(stepName,
                        MediaEntityBuilder
                                .createScreenCaptureFromBase64String(base64Screenshot)
                                .build());
            } else {
                scenarioLog.pass(stepName,
                        MediaEntityBuilder
                                .createScreenCaptureFromBase64String(base64Screenshot)
                                .build());
            }

        } catch (Exception e) {
            scenarioLog.warning("Failed to capture screenshot for step " + stepCounter);
        }
    }
    @After
    public void closeBrowser(Scenario scenario) {
        // Stop Screen Recording & Convert to MP4
        try {
            if (recorder != null) {
                recorder.stopRecording();
                Thread.sleep(2000);

                File recordingsDir = new File("target/recordings");
                File[] files = recordingsDir.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".avi"));

                if (files != null && files.length > 0) {
                    File latestVideo = files[0];
                    for (File file : files) {
                        if (file.lastModified() > latestVideo.lastModified()) {
                            latestVideo = file;
                        }
                    }

                    // Convert AVI to MP4
                    scenarioLog.info("🔄 Converting video to MP4...");
                    File mp4Video = VideoConverter.convertAviToMp4(latestVideo);

                    String videoFileName = mp4Video.getName();
                    String relativeVideoPath = "recordings/" + videoFileName;

                    // Embed MP4 video
                    String videoHTML =
                            "<div style='margin: 20px 0; padding: 15px; background: #f8f9fa; border-radius: 8px;'>" +
                                    "<h4 style='color: #007bff; margin-bottom: 10px;'>🎬 Test Execution Video</h4>" +
                                    "<video width='100%' height='600' controls preload='metadata' " +
                                    "style='border: 2px solid #007bff; border-radius: 8px; background: #000;'>" +
                                    "<source src='../" + relativeVideoPath + "' type='video/mp4'>" +
                                    "Your browser does not support HTML5 video." +
                                    "</video>" +
                                    "<div style='margin-top: 10px; text-align: center;'>" +
                                    "<a href='../" + relativeVideoPath + "' download " +
                                    "style='display: inline-block; padding: 10px 20px; background: #28a745; " +
                                    "color: white; text-decoration: none; border-radius: 5px;'>" +
                                    "⬇️ Download Video</a>" +
                                    "</div>" +
                                    "</div>";

                    scenarioLog.info(videoHTML);
                    scenarioLog.pass("✅ Video saved: " + videoFileName);
                }
            }
        } catch (Exception e) {
            scenarioLog.warning("Failed to process recording: " + e.getMessage());
        }
        // Final screenshot on failure
        if (scenario.isFailed() && !isAlertPresent()) {
            try {
                String base64Screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BASE64);

                scenarioLog.fail("❌ Scenario Failed - Final Screenshot",
                        MediaEntityBuilder
                                .createScreenCaptureFromBase64String(base64Screenshot)
                                .build());
            } catch (Exception e) {
                scenarioLog.warning("Failed to capture final screenshot");
            }
        } else if (!scenario.isFailed()) {
            scenarioLog.pass("✅ Scenario Passed Successfully");
        }

        if (driver != null) {
            driver.quit();
            scenarioLog.info("🚪 Browser closed");
        }
    }

    @AfterAll
    public static void generateReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("📊 Extent Report: target/ExtentReport.html");
            System.out.println("🎥 Recordings: target/recordings/");
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
