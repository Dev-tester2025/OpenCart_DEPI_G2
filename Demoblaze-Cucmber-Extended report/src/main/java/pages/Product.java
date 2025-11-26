package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Product {
    WebDriver driver;
    WebDriverWait wait;
    public Product(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");




    //Actions
    public void clickOnAddToCartButton(){
        driver.findElement(addToCartButton).click();
    }

    //Alert
    public String getProductAddedSuccessMessage(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
    
}
