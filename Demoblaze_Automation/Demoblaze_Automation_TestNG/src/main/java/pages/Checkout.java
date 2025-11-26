package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Checkout {
    WebDriver driver;
    WebDriverWait wait;

    public Checkout(WebDriver driver) {
        this.driver = driver;
    }

    // LOCATORS
    private By nameField = By.cssSelector("#name");
    private By countryField = By.cssSelector("#country");
    private By cityField = By.cssSelector("#city");
    private By creditCardField = By.cssSelector("#card");
    private By monthField = By.cssSelector("#month");
    private By yearField = By.cssSelector("#year");
    private By purchaseButton = By.xpath("//button[contains(text(), 'Purchase')]");
    private By closeButton = By.xpath("//div[@id='orderModal']//button[contains(text(), 'Close')]");
    private By successMessage = By.xpath("//h2[contains(text(), 'Thank you for your purchase!')]");
    private By confirmButton = By.xpath("//button[contains(text(), 'OK')]");

    // ACTIONS
    public void insertName(String name) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
    }

    public void insertCountry(String country) {
        driver.findElement(countryField).sendKeys(country);
    }

    public void insertCity(String city) {
        driver.findElement(cityField).sendKeys(city);
    }

    public void insertCreditCard(String creditCard) {
        driver.findElement(creditCardField).sendKeys(creditCard);
    }

    public void insertMonth(String month) {
        driver.findElement(monthField).sendKeys(month);
    }

    public void insertYear(String year) {
        driver.findElement(yearField).sendKeys(year);
    }

    public void clickOnPurchaseButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        driver.findElement(purchaseButton).click();
    }

    public void clickOnCloseButton() {
        driver.findElement(closeButton).click();
    }

    public String getSuccessMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public void clickOnConfirmButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public String getAlertText() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}