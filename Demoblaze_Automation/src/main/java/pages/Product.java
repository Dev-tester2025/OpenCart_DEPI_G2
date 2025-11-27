package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

import java.time.Duration;

public class Product {
    WebDriver driver;
    WebDriverWait wait;
    public Product(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Locators
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");

    //Pagination
    private By nextButton = By.xpath("//button[contains(text(),'Next')]");
    private By prevButton = By.xpath("//button[contains(text(),'Previous')]");
    private By productItems = By.cssSelector("#tbodyid .col-lg-4");
    private By paginationContainer = By.xpath("//ul[@class='pagination']");
    private By productTitles = By.cssSelector("#tbodyid .col-lg-4 .card-title a");




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

    //Pagination

    private void scrollToPagination() {
        WebElement element = driver.findElement(paginationContainer);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public int getProductsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productItems));
        List<WebElement> items = driver.findElements(productItems);
        return items.size();
    }

    public void clickNextAndWaitForChange() {
        String oldName = getFirstProductName();

        scrollToPagination();
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(productTitles, oldName)
        ));
    }


    public void clickPreviousAndWaitForChange() {
        String oldName = getFirstProductName();  

        scrollToPagination();
        wait.until(ExpectedConditions.elementToBeClickable(prevButton));
        driver.findElement(prevButton).click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(productTitles, oldName)
        ));
    }
    public String getFirstProductName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitles));
        return driver.findElements(productTitles).get(0).getText();
    }
    
}
