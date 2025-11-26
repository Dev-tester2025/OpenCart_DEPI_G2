package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

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
    private By logoutHeaderTab = By.xpath("//a[contains(text(), 'Log out')]");

    //Register
    private By signUpHeaderTab = By.xpath("//a[contains(text(), 'Sign up')]");
    private By usernameBoxSignUp = By.cssSelector("#sign-username");
    private By passwordBoxSignUp = By.cssSelector("#sign-password");
    private By signUpButton = By.xpath("//button[contains(text(), 'Sign up')]");

    //Product
    private By nexus6ProductLink = By.xpath("//a[contains(text(), 'Nexus 6')]");

    //Navigation
    private By homeTab = By.xpath("//a[contains(text(),'Home')]");
    private By contactTab = By.xpath("//a[contains(text(),'Contact')]");
    private By cartTab = By.xpath("//a[contains(text(),'Cart')]");
    private By contactModalCloseButton = By.xpath("//div[@id='exampleModal']//button[@class='close']");

    // Contact Modal
    private By contactEmailField = By.xpath("//input[@id='recipient-email']");
    private By contactNameField = By.xpath("//input[@id='recipient-name']");
    private By contactMessageField = By.xpath("//textarea[@id='message-text']");
    private By contactSendMessageButton = By.xpath("//button[contains(text(),'Send message')]");

    // The modal container
    private By contactModal = By.id("exampleModal");

    // Carousel
    private By carouselPrevButton = By.xpath("//a[contains(@class, 'carousel-control-prev')]");
    private By carouselNextButton = By.xpath("//a[contains(@class, 'carousel-control-next')]");
    public By activeCarouselImage = By.xpath("//div[contains(@class, 'carousel-item active')]//img");



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

     //Navigation
    public void clickHomeTab() {
        driver.findElement(homeTab).click();
    }

    public void clickContactTab() {driver.findElement(contactTab).click();}


    public void clickCartTab() {
        driver.findElement(cartTab).click();
    }

    public void closeContactModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement closeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(contactModalCloseButton)
        );

        closeBtn.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.id("exampleModal")
        ));
    }

    //Contact Modal

    public void enterContactEmail(String email){
        driver.findElement(contactEmailField).sendKeys(email);
    }

    public void enterContactName(String name){
        driver.findElement(contactNameField).sendKeys(name);
    }

    public void enterContactMessage(String message){
        driver.findElement(contactMessageField).sendKeys(message);
    }

    public void clickSendMessage(){
        driver.findElement(contactSendMessageButton).click();
    }

    public void waitForContactModalToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(contactModal));
    }

    public void submitContactForm(String email, String name, String message) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait modal to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmailField));

        enterContactEmail(email);
        enterContactName(name);
        enterContactMessage(message);

        clickSendMessage();

        // Wait alert
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // Carousel
    // click "Next" button
    public void clickNextCarouselButton() {
        driver.findElement(carouselNextButton).click();
    }

    //  click "Previous" button
    public void clickPrevCarouselButton() {
        driver.findElement(carouselPrevButton).click();
    }
    public String getActiveCarouselImageSrc() {
        return driver.findElement(activeCarouselImage).getAttribute("src");
    }
}


