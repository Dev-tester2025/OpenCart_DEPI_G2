package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Cart; 
import pages.Product; 

import java.time.Duration;
import java.util.List;

public class CartSteps {

    // Declare instances for WebDriver and Page Objects
    private WebDriver driver;
    private Product productPage;
    private Cart cartPage;

    // --- CONSTRUCTOR: Initializes Driver and Page Objects using Hooks ---
    public CartSteps() {
        // Get the WebDriver instance from the Hooks class
        this.driver = Hooks.getDriver();
        // Initialize Page Objects using the shared driver
        this.productPage = new Product(this.driver);
        this.cartPage = new Cart(this.driver);
    }

    // --- HELPER METHODS (Identical to your original CartTests.java logic) ---

    private void waitForAndAcceptAlert() {
        try {
            // Using a short wait (3s) for the alert to appear
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent())
                    .accept();
        } catch (Exception e) {
            // Alert did not appear, ignore the exception
        }
    }

    private boolean verifySpecificItemDetails(String productName, String expectedPrice) {
        // Find the product row by product name (xpath: //td[text()='Product Name']/parent::tr)
        By productRowXPath = By.xpath("//td[text()='" + productName + "']/parent::tr");
        List<WebElement> rows = driver.findElements(productRowXPath);

        if (rows.isEmpty()) {
            return false;
        }

        WebElement itemRow = rows.get(0);
        // Price is in the 3rd column (xpath: ./td[3])
        String actualPrice = itemRow.findElement(By.xpath("./td[3]")).getText();

        return actualPrice.equals(expectedPrice);
    }
    
    // --- GIVEN Steps ---

    @Given("I am on the Demoblaze homepage")
    public void iAmOnTheDemoblazeHomepage() {
        driver.get("https://www.demoblaze.com/");
    }

    @Given("a product named {string} with price {string} is available")
    public void aProductIsAvailable(String productName, String price) {
        // Navigate to home page if not already there, then check link existence
        driver.get("https://www.demoblaze.com/"); 
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + productName + "']")).isDisplayed(),
                "Precondition Failed: Product link is not visible.");
    }

    @Given("a product named {string} has been added to the cart")
    public void aProductHasBeenAddedToTheCart(String productName) {
        // Navigate to product, add to cart, and accept alert
        driver.findElement(By.xpath("//a[text()='" + productName + "']")).click();
        productPage.clickOnAddToCartButton();
        waitForAndAcceptAlert();
    }

    // --- WHEN Steps ---

    @When("I navigate to the {string} product page")
    public void iNavigateToTheProductPage(String productName) {
        driver.findElement(By.xpath("//a[text()='" + productName + "']")).click();
    }

    @And("I add the product to the cart")
    public void iAddTheProductToTheCart() {
        productPage.clickOnAddToCartButton();
    }

    @And("I accept the confirmation alert")
    public void iAcceptTheConfirmationAlert() {
        waitForAndAcceptAlert();
    }

    @And("I navigate to the Cart page")
    public void iNavigateToTheCartPage() {
        cartPage.clickOnCartHeaderTab();
    }

    @When("I click the 'Delete' button for the product {string}")
    public void iClickTheDeleteButtonForTheProduct(String productName) {
        // Find the specific row to wait for its staleness (disappearance)
        WebElement itemRow = driver.findElement(By.xpath("//td[text()='" + productName + "']/parent::tr"));

        // Locate and click the Delete link associated with the product
        By deleteLink = By.xpath("//td[text()='" + productName + "']/following-sibling::td/a[text()='Delete']");
        driver.findElement(deleteLink).click();
        
        // Wait for the row to disappear (copied from your original TC-C2 logic)
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.stalenessOf(itemRow));
    }
    
    @And("I delete all products from the cart for cleanup")
    public void iDeleteAllProductsFromTheCartForCleanup() {
        List<WebElement> products = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr"));
        if (!products.isEmpty()) {
            
            // Temporary direct implementation for deleting all products
            By deleteLinks = By.xpath("//tbody[@id='tbodyid']//a[text()='Delete']");
            List<WebElement> deleteButtons = driver.findElements(deleteLinks);
            
            // Loop and delete until no products are left
            while (!deleteButtons.isEmpty()) {
                 deleteButtons.get(0).click(); 
                 deleteButtons = driver.findElements(deleteLinks); 
            }
            
            // Wait for table body to visually become empty
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfAllElements(products));
        }
    }


    // --- THEN Steps ---

    @Then("the product {string} with price {string} should appear in the cart")
    public void theProductShouldAppearInTheCart(String productName, String expectedPrice) {
        boolean isItemCorrect = verifySpecificItemDetails(productName, expectedPrice);
        Assert.assertTrue(isItemCorrect,
                "FAILURE: The product '" + productName + "' was not found or price incorrect.");
    }

    @Then("the product {string} should not appear in the cart")
    public void theProductShouldNotAppearInTheCart(String productName) {
        By itemLocator = By.xpath("//td[text()='" + productName + "']");
        boolean isStillPresent = driver.findElements(itemLocator).size() > 0;
        Assert.assertFalse(isStillPresent,
                "FAILURE: The product '" + productName + "' was NOT deleted.");
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        boolean isEmpty = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr")).isEmpty();
        Assert.assertTrue(isEmpty, "FAILURE: Cart is not empty.");
    }
}
