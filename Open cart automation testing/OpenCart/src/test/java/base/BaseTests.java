package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

public class BaseTests {
    public WebDriver driver;
    public HomePage homepage;
    @BeforeClass
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        homepage = new HomePage(driver);
    }

    @BeforeMethod
    public void goHome(){
        driver.get("http://localhost:8888/OpenCart/index.php?route=common/home&language=en-gb");
    }



    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }


}
