package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    WebDriver driver;
    WebDriverWait wait;
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By emailBox = By.cssSelector("#input-email");
    private By continueButton = By.cssSelector(".btn.btn-primary");
    private By emailNotFoundMessage = By.cssSelector(".alert.alert-danger.alert-dismissible");


    //Actions
    public void insertEmail(String email){
        driver.findElement(emailBox).sendKeys(email);
    }

    public void clickOnContinueButton(){
        driver.findElement(continueButton).click();
    }

    public String getEmailNotFoundMessage(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailNotFoundMessage));
        return driver.findElement(emailNotFoundMessage).getText();
    }
}
