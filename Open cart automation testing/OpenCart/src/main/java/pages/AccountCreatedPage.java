package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage {
    WebDriver driver;
    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By successMessage = By.xpath("//h1[contains(text(), \"Your Account Has Been Created!\")]");

    //Actions
    public String getSuccessMessage(){
        return driver.findElement(successMessage).getText();
    }
}
