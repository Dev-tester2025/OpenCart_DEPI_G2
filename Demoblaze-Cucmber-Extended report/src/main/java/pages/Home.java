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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    //LOCATORS
    //Login
    private By loginHeaderTab = By.cssSelector("#login2");
    private By usernameBoxLogin = By.xpath("//input[@id = \"loginusername\"]");
    private By passwordBoxLogin = By.xpath("//input[@id = \"loginpassword\"]");
    private By loginButton = By.xpath("//button[contains(text(), 'Log in')]");
    private By welcomeUser = By.cssSelector("#nameofuser");
    private By logoutHeaderTab = By.xpath("//a[contains(text(), 'Log out')]");

    //Register
    private By signUpHeaderTab = By.xpath("//a[contains(text(), 'Sign up')]");
    private By usernameBoxSignUp = By.cssSelector("#sign-username");
    private By passwordBoxSignUp = By.cssSelector("#sign-password");
    private By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");

    //Product
    private By nexus6ProductLink = By.xpath("//a[contains(text(), 'Nexus 6')]");



    //ACTIONS
    //Login
    public void clickOnLoginHeaderTab(){
        driver.findElement(loginHeaderTab).click();
    }

    public void insertLoginUsername(String email){

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

        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeUser));
        return driver.findElement(welcomeUser).getText();
    }

    public String getAlertText(){

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public void clickOnLogoutHeaderTab(){
        driver.findElement(logoutHeaderTab).click();
    }

    //Register
    public void clickOnSignUpHeaderTab(){
        driver.findElement(signUpHeaderTab).click();
    }

    public void insertSignUpUsername(String email){

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

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    //Product
    public Product clickOnNexus6ProductLink(){
        driver.get("https://www.demoblaze.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nexus6ProductLink));
        driver.findElement(nexus6ProductLink).click();
        return new Product(driver);
    }



}

