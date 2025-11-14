package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    private By myAccountDropDownButton = By.xpath("//span[contains(text(), \"My Account\")]");
    private By registerButton = By.xpath("//*[contains(text(), \"Register\")]");
    private By loginButton = By.xpath("//*[contains(text(), \"Login\")]");


    //Actions
    public void clickOnMyAccountDropDownButton(){
        driver.findElement(myAccountDropDownButton).click();
    }
    public RegisterPage clickOnRegisterButton(){
        driver.findElement(registerButton).click();
        return new RegisterPage(driver);
    }
    public LoginPage clickOnLoginButton(){
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }


}
