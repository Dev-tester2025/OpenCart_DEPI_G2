package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.Home;


public class LoginSteps  {
        WebDriver driver = Hooks.getDriver();
         Home home = new Home(driver);

    @Given("user is on home page")
    public void user_is_on_home_page() {
        home = new Home(driver);
    }

    @When("user clicks login tab")
    public void user_clicks_login_tab() {
        home.clickOnLoginHeaderTab();
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        home.insertLoginUsername(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        home.insertLoginPassword(password);
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        home.clickOnLoginButton();
    }

    @Then("welcome message should contain {string}")
    public void welcome_message_should_contain(String expected) {
        String actual = home.getWelcomeUserText();
        Assert.assertTrue(actual.contains(expected));
    }

    @Then("error message should contain {string}")
    public void error_message_should_contain(String expected) {
        String actual = home.getAlertText();
        Assert.assertTrue(actual.contains(expected));
    }

    @Then("user logs out")
    public void user_logs_out() {
        home.clickOnLogoutHeaderTab();
    }

    @Then("user accepts alert")
    public void user_accepts_alert() {
        home.acceptAlert();
    }
}
