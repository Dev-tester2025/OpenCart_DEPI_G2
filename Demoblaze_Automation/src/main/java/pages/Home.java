package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    private By logoutHeaderTab = By.xpath("//a[contains(text(), 'Log out')]");

    //Register
    private By signUpHeaderTab = By.xpath("//a[contains(text(), 'Sign up')]");
    private By usernameBoxSignUp = By.cssSelector("#sign-username");
    private By passwordBoxSignUp = By.cssSelector("#sign-password");
    private By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");

    //Product
    private By nexus6ProductLink = By.xpath("//a[contains(text(), 'Nexus 6')]");
//categories
    private By phoneProduct1= By.linkText("Nokia lumia 1520");
    private By phoneProduct2= By.linkText("Samsung galaxy s6");
    private By phoneProduct3= By.linkText("Nexus 6");
    private By phoneProduct4= By.linkText("Samsung galaxy s7");
    private By phoneProduct5= By.linkText("Iphone 6 32gb");
    private By phoneProduct6= By.linkText("Sony xperia z5");


    private By laptopProduct1= By.linkText("Sony vaio i5");
    private By laptopProduct2= By.linkText("Sony vaio i7");
    private By laptopProduct3= By.linkText("MacBook air");
    private By laptopProduct4= By.linkText("Dell i7 8gb");
    private By laptopProduct5= By.linkText("2017 Dell 15.6 Inch");
    private By laptopProduct6= By.linkText("MacBook Pro");



    private By monitorsProduct1= By.linkText("Apple monitor 24");
    private By monitorsProduct2= By.linkText("ASUS Full HD");


    private By productTitles = By.cssSelector(".card-title a");




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

    public void clickOnLogoutHeaderTab(){
        driver.findElement(logoutHeaderTab).click();
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

    //Product
    public Product clickOnNexus6ProductLink(){
        driver.findElement(nexus6ProductLink).click();
        return new Product(driver);
    }


    public void clickOnHomeTab() {
    }

    public boolean isProductDisplayed(String productName) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitles));

        List<WebElement> products = driver.findElements(productTitles);

        for (int i = 0; i < products.size(); i++) {
            try {
                String title = products.get(i).getText().trim().toLowerCase();
                if (title.contains(productName.toLowerCase())) {
                    return true;
                }
            } catch (StaleElementReferenceException e) {

                products = driver.findElements(productTitles);
                String title = products.get(i).getText().trim().toLowerCase();
                if (title.contains(productName.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }







}

