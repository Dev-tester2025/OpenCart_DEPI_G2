package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.Home;

public class RegisterSteps {
    WebDriver driver = Hooks.getDriver();
    Home home = new Home(driver);

    @Given("the user opens the homepage and clicks on Sign up header tab")
    public void the_user_opens_the_homepage_and_clicks_on_sign_up_header_tab() {
        home.clickOnSignUpHeaderTab();
    }
    @When("the user enters valid {string} and {string} then clicks on Sign up button")
    public void the_user_enters_valid_and_then_clicks_on_sign_up_button(String username, String password) {
        home.insertSignUpUsername(username);
        home.insertSignUpPassword(password);
        home.clickOnSignUpButton();
    }
    @Then("an alert with success message appears")
    public void an_alert_with_success_message_appears() {
        String actualResult = home.getAlertText();
        String expectedResult = "Sign up successful.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();
    }


    @Given("the already existing user opens the homepage and clicks on Sign up header tab")
    public void givenTheAlreadyExistingUserOpensTheHomepageAndClicksOnSignUpHeaderTab() {
        home.clickOnSignUpHeaderTab();
    }

    @When("the user enters invalid {string} and {string} then clicks on Sign up button")
    public void theUserEntersInvalidAndThenClicksOnSignUpButton(String username, String password) {
        home.insertSignUpUsername(username);
        home.insertSignUpPassword(password);
        home.clickOnSignUpButton();
    }

    @Then("an alert with error message appears")
    public void anAlertWithErrorMessageAppears() {
        String actualResult = home.getAlertText();
        String expectedResult = "This user already exist.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();
    }
}
