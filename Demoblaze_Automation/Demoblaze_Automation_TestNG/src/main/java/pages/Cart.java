package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cart {
    WebDriver driver;
    WebDriverWait wait;

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    // LOCATORS
    private By cartHeaderTab = By.cssSelector("#cartur");
    private By placeOrderButton = By.xpath("//button[contains(text(), 'Place Order')]");
    private By totalPrice = By.cssSelector("#totalp");
    private By productRow = By.xpath("//tbody[@id='tbodyid']/tr");
    private By deleteButton = By.xpath("//a[contains(text(), 'Delete')]");

    // ACTIONS
    public void clickOnCartHeaderTab() {
        driver.findElement(cartHeaderTab).click();
    }

    public Checkout clickOnPlaceOrderButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        driver.findElement(placeOrderButton).click();
        return new Checkout(driver);
    }

    public String getTotalPrice() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
        return driver.findElement(totalPrice).getText();
    }

    public boolean isProductInCart() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(productRow));
        return driver.findElements(productRow).size() > 0;
    }

    public void clickOnDeleteButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        driver.findElement(deleteButton).click();
    }
    public void deleteAllProductsFromCart() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        while (driver.findElements(deleteButton).size() > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
            driver.findElement(deleteButton).click();
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(deleteButton)));
        }
    }
}
