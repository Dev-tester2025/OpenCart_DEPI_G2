package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Categories {

    WebDriver driver;
    WebDriverWait wait;

    public Categories(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Locators
    private By phonesCategory = By.xpath("//a[@onclick=\"byCat('phone')\"]");
    private By laptopsCategory = By.xpath("//a[@onclick=\"byCat('notebook')\"]");
    private By monitorsCategory = By.xpath("//a[@onclick=\"byCat('monitor')\"]");

    private By productTitles = By.cssSelector(".card-title a");

    private By phoneMainProduct = By.linkText("Nexus 6");
    private By laptopMainProduct = By.linkText("Sony vaio i5");
    private By monitorMainProduct = By.linkText("Apple monitor 24");



    private void waitForProductListChange(int oldCount) {

        wait.until(driver -> {
            List<WebElement> newList = driver.findElements(productTitles);
            return newList.size() != oldCount;
        });

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productTitles));
    }

    private void waitForCategory(By mainProductLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainProductLocator));
    }



    // Actions

    public void clickPhones() {
        int oldCount = driver.findElements(productTitles).size();
        driver.findElement(phonesCategory).click();
        waitForProductListChange(oldCount);
        waitForCategory(phoneMainProduct);
    }

    public void clickLaptops() {
        int oldCount = driver.findElements(productTitles).size();
        driver.findElement(laptopsCategory).click();
        waitForProductListChange(oldCount);
        waitForCategory(laptopMainProduct);
    }

    public void clickMonitors() {
        int oldCount = driver.findElements(productTitles).size();
        driver.findElement(monitorsCategory).click();
        waitForProductListChange(oldCount);
        waitForCategory(monitorMainProduct);
    }




}
