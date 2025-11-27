package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Cart;
import pages.Checkout;
import pages.Home;
import pages.Product;

import java.time.Duration;
import java.util.Map;

public class CheckoutSteps {
    WebDriver driver;
    Home home;
    Product product;
    Cart cart;
    Checkout checkout;

    public CheckoutSteps() {
        this.driver = Hooks.getDriver();
        this.home = new Home(this.driver);
        this.product = new Product(this.driver);
        this.cart = new Cart(this.driver);
        this.checkout = new Checkout(this.driver);
    }


    @Given("user on home page")
    public void user_on_home_page() {
        driver.get("https://www.demoblaze.com/");
    }


    @When("user clicks on Nexus 6 product")
    public void user_clicks_on_nexus_6_product() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nexus6Link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Nexus 6')]")));
        nexus6Link.click();
        product = new Product(driver);
    }

    @When("user adds product to cart")
    public void user_adds_product_to_cart() {
        product.clickOnAddToCartButton();
    }

    @When("user accepts product alert")
    public void user_accepts_product_alert() {
        product.acceptAlert();
    }

    @When("user navigates to cart")
    public void user_navigates_to_cart() {
        cart = new Cart(driver);
        cart.clickOnCartHeaderTab();
    }

    @When("user clicks on place order button")
    public void user_clicks_on_place_order_button() {
        checkout = cart.clickOnPlaceOrderButton();
    }

    @When("user enters checkout details:")
    public void user_enters_checkout_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        checkout.insertName(data.get("name"));
        checkout.insertCountry(data.get("country"));
        checkout.insertCity(data.get("city"));
        checkout.insertCreditCard(data.get("creditCard"));
        checkout.insertMonth(data.get("month"));
        checkout.insertYear(data.get("year"));
    }


    @When("user enters name {string}")
    public void user_enters_name(String name) {
        checkout.insertName(name);
    }

    @When("user enters country {string}")
    public void user_enters_country(String country) {
        checkout.insertCountry(country);
    }

    @When("user enters city {string}")
    public void user_enters_city(String city) {
        checkout.insertCity(city);
    }

    @When("user enters credit card {string}")
    public void user_enters_credit_card(String creditCard) {
        checkout.insertCreditCard(creditCard);
    }

    @When("user enters month {string}")
    public void user_enters_month(String month) {
        checkout.insertMonth(month);
    }

    @When("user enters year {string}")
    public void user_enters_year(String year) {
        checkout.insertYear(year);
    }

    @When("user clicks on purchase button")
    public void user_clicks_on_purchase_button() {
        checkout.clickOnPurchaseButton();
    }

    @When("user clicks on close button")
    public void user_clicks_on_close_button() {
        checkout.clickOnCloseButton();
    }

    @When("user clicks on confirm button")
    public void user_clicks_on_confirm_button() {
        checkout.clickOnConfirmButton();
    }


    @Then("product added message should contain {string}")
    public void product_added_message_should_contain(String expected) {
        String actual = product.getProductAddedSuccessMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @Then("product should be in cart")
    public void product_should_be_in_cart() {
        Assert.assertTrue(cart.isProductInCart());
    }

    @Then("success message should contain {string}")
    public void success_message_should_contain(String expected) {
        String actual = checkout.getSuccessMessage();
        Assert.assertTrue(actual.contains(expected));
    }

    @Then("checkout alert message should contain {string}")
    public void checkout_alert_message_should_contain(String expected) {
        String actual = checkout.getAlertText();
        Assert.assertTrue(actual.contains(expected));
    }

    @Then("user accepts checkout alert")
    public void user_accepts_checkout_alert() {
        checkout.acceptAlert();
    }
}
