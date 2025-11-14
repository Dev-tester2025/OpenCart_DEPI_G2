package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By firstNameBox = By.cssSelector("#input-firstname");
    private By lastNameBox = By.cssSelector("input-lastname");
    private By emailBox = By.cssSelector("#input-email");
    private By passwordBox = By.cssSelector("#input-password");
    private By newsletterSubscribeCheckbox = By.cssSelector("#input-newsletter");
    private By privacyPolicyCheckbox = By.xpath("//input[@name = 'agree']");
    private By continueButton = By.xpath("//button[contains(text(), \"Continue\")]");


    //Actions
    public void insertFirstName(String name){
        driver.findElement(firstNameBox).sendKeys(name);
    }

    public void insertLastName(String name){
        driver.findElement(lastNameBox).sendKeys(name);
    }

    public void insertEmail(String email){
        driver.findElement(emailBox).sendKeys(email);
    }

    public void insertPassword(String password){
        driver.findElement(passwordBox).sendKeys(password);
    }

    public void clickOnNewsletterSubscribeCheckBox(){
        driver.findElement(newsletterSubscribeCheckbox).click();
    }

    public void clickOnPrivacyPolicyCheckbox(){
        driver.findElement(privacyPolicyCheckbox).click();
    }

    public AccountCreatedPage clickOnContinueButton(){
        driver.findElement(continueButton).click();
        return new AccountCreatedPage(driver);
    }
}
