package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    WebDriver driver;
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By accountLogoutHeader = By.xpath("//h1[contains(text(), \"Account Logout\")]");
    private By continueButton = By.cssSelector(".btn.btn-primary");


    //Actions
    public String getAccountLogoutHeaderText(){
        return driver.findElement(accountLogoutHeader).getText();
    }

    public HomePage clickOnContinueButton(){
        driver.findElement(continueButton).click();
        return new HomePage(driver);
    }
}
