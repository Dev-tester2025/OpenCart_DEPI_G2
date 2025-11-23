package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home {
    WebDriver driver;
    WebDriverWait wait;

    public Home(WebDriver driver) {
        this.driver = driver;
    }


    //LOCATORS
    //Login
    private By loginHeaderTab = By.cssSelector("#login2");
    private By usernameBoxLogin = By.xpath("//input[@id = \"loginusername\"]");
    private By passwordBoxLogin = By.xpath("//input[@id = \"loginpassword\"]");
    private By loginButton = By.xpath("//button[contains(text(), 'Log in')]");
    private By welcomeUser = By.cssSelector("#nameofuser");

    //Register
    private By signUpHeaderTab = By.xpath("//a[contains(text(), 'Sign up')]");
    private By usernameBoxSignUp = By.cssSelector("#sign-username");
    private By passwordBoxSignUp = By.cssSelector("#sign-password");
    private By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");



    //ACTIONS
    //Login
    public void clickOnLoginHeaderTab(){
        driver.findElement(loginHeaderTab).click();
    }

    public void insertLoginUsername(String email){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameBoxLogin));
        driver.findElement(usernameBoxLogin).sendKeys(email);
    }

    public void insertLoginPassword(String password){
        driver.findElement(passwordBoxLogin).sendKeys(password);
    }

    public void clickOnLoginButton(){
        driver.findElement(loginButton).click();
    }

    public String getWelcomeUserText(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
        return driver.findElement(welcomeUser).getText();
    }

    public String getAlertText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    //Register
    public void clickOnSignUpHeaderTab(){
        driver.findElement(signUpHeaderTab).click();
    }

    public void insertSignUpUsername(String email){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameBoxSignUp));
        driver.findElement(usernameBoxSignUp).sendKeys(email);
    }

    public void insertSignUpPassword(String password){
        driver.findElement(passwordBoxSignUp).sendKeys(password);
    }

    public void clickOnSignUpButton(){
        driver.findElement(signUpButton).click();
    }

    public String getSignUpSuccessAlert(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    
}

