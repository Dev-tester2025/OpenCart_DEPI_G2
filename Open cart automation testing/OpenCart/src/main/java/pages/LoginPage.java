package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By emailBox = By.cssSelector("#input-email");
    private By passwordBox = By.cssSelector("#input-password");
    private By loginButton = By.xpath("//button[contains(text(), \"Login\")]");
    private By forgottenPasswordButton = By.partialLinkText("Forgotten Password");
    private By forgotPassResetSuccessMessage = By.xpath("//*[contains(text(), \"text_success\")]");
    private By invalidUsernameOrPasswordMessage = By.cssSelector(".alert.alert-danger.alert-dismissible");



    //Actions
    public void insertEmail(String email){
        driver.findElement(emailBox).sendKeys(email);
    }

    public void insertPassword(String password){
        driver.findElement(passwordBox).sendKeys(password);
    }

    public MyAccountPage clickOnLoginButton(){
        driver.findElement(loginButton).click();
        return new MyAccountPage(driver);
    }

    public ForgotPasswordPage clickOnForgottenPasswordButton(){
        driver.findElement(forgottenPasswordButton).click();
        return new ForgotPasswordPage(driver);
    }

    public String getForgotPassResetSuccessMessage(){
        return driver.findElement(forgotPassResetSuccessMessage).getText();
    }

    public String getInvalidUsernameOrPasswordMessage(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidUsernameOrPasswordMessage));
        return driver.findElement(invalidUsernameOrPasswordMessage).getText();
    }


}
