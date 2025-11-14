package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyAccountPage {
    WebDriver driver;
    WebDriverWait wait;
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By myAccountHeader = By.xpath("//h1[contains(text(), \"My Account\")]");
    private By sidebarLogoutButton = By.xpath("(//a[contains(@href,'account/logout')])[2]");


    //Actions
    public String getMyAccountHeaderText(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'My Account')]")));
        return driver.findElement(myAccountHeader).getText();
    }
    public LogoutPage clickOnSidebarLogoutButton(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(@href,'account/logout')])[2]")));
        driver.findElement(sidebarLogoutButton).click();
        return new LogoutPage(driver);
    }

}
