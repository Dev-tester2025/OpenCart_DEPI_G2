package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.Home;
import pages.Product;

public class ProductSteps {
    WebDriver driver = Hooks.getDriver();
    Home home = new Home(driver);
    Product product;

    @Given("the user is on the homepage and clicks on Nexus product")
    public void theUserIsOnTheHomepageAndClicksOnNexusProduct() {
        product = home.clickOnNexus6ProductLink();
    }

    @When("the user clicks on Add to Cart button on the product page")
    public void theUserClicksOnAddToCartButtonOnTheProductPage() {
        product.clickOnAddToCartButton();
    }


    @Then("an alert with a success message appears {string}")
    public void anAlertWithASuccessMessageAppears(String expectedResult) {
        String actualResult = product.getProductAddedSuccessMessage();
        Assert.assertTrue(actualResult.contains(expectedResult));
    }
}
