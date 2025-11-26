package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup driver
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

        // Browser settings
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Open Demoblaze website
        driver.get("https://demoblaze.com/");
    }

    @BeforeMethod
    public void navigateToHome() {
        driver.navigate().to("https://demoblaze.com/");
    }

    @AfterMethod
    public void cleanUp() {
        driver.navigate().refresh();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}