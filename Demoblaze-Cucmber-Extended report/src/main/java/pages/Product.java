package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Product {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public Product(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Wait واحد ثابت
    }

    // Locators
    private By addToCartButton = By.xpath("//a[contains(text(),'Add to cart')]");

    // Actions
    public void clickOnAddToCartButton() {
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addBtn.click();
    }

    // Alert
    public String getProductAddedSuccessMessage() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}
