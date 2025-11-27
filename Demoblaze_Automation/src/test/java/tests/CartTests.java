package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Cart;
import pages.Product;

import java.time.Duration;
import java.util.List;

public class CartTests extends BaseTest {

    // --- HELPER METHODS FOR TEST EXECUTION ---

    private void waitForAndAcceptAlert(WebDriver driver) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent())
                    .accept();
        } catch (Exception e) {
            // Alert did not appear within 3 seconds, ignore
        }
    }

    private boolean verifySpecificItemDetails(WebDriver driver, String productName, String expectedPrice) {
        // Optimized XPATH to locate the row based on product name
        By productRowXPath = By.xpath("//td[text()='" + productName + "']/parent::tr");
        List<WebElement> rows = driver.findElements(productRowXPath);

        if (rows.isEmpty()) {
            return false;
        }

        WebElement itemRow = rows.get(0);
        // Price is in the 3rd column
        String actualPrice = itemRow.findElement(By.xpath("./td[3]")).getText();

        return actualPrice.equals(expectedPrice);
    }

    private void deleteAllProductsIfPresent(Cart cartPage) {
        List<WebElement> products = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr"));
        if (!products.isEmpty()) {
            cartPage.deleteAllProductsFromCart();
            // Wait for table to become empty
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.invisibilityOfAllElements(products));
        }
    }

    // ------------------ TC-C1 ------------------

    @Test(description = "TC-C1: Verify added product details and price appear correctly.")
    public void tc_c1_verifyAddedItemsAppearCorrectly() {
        Product productPage = new Product(driver);
        Cart cartPage = new Cart(driver);

        String productName = "Samsung galaxy s6";
        String expectedPrice = "360";

        // 1. Navigate to the product page
        driver.findElement(By.xpath("//a[text()='" + productName + "']")).click();

        // 2. Add the product to the cart
        productPage.clickOnAddToCartButton();
        waitForAndAcceptAlert(driver);

        // 3. Navigate to the cart page
        cartPage.clickOnCartHeaderTab();

        // 4. Validate name + price
        boolean isItemCorrect = verifySpecificItemDetails(driver, productName, expectedPrice);
        Assert.assertTrue(isItemCorrect,
                "FAILURE: The product '" + productName + "' was not found or price incorrect.");

        // Cleanup
        deleteAllProductsIfPresent(cartPage);
    }

    // ------------------ TC-C2 ------------------

    @Test(description = "TC-C2: Verify item can be successfully deleted from the cart.")
    public void tc_c2_deleteItemFromCart() {
        Product productPage = new Product(driver);
        Cart cartPage = new Cart(driver);

        String productName = "Nokia lumia 1520";

        // Add item setup
        driver.findElement(By.xpath("//a[text()='" + productName + "']")).click();
        productPage.clickOnAddToCartButton();
        waitForAndAcceptAlert(driver);

        // Navigate to cart
        cartPage.clickOnCartHeaderTab();

        By itemLocator = By.xpath("//td[text()='" + productName + "']");
        WebElement itemRow = driver.findElement(By.xpath("//td[text()='" + productName + "']/parent::tr"));

        Assert.assertTrue(driver.findElements(itemLocator).size() > 0,
                "PRE-CHECK FAILURE: Item was not added to cart initially.");

        // Click delete
        cartPage.clickOnDeleteButton();

        // Wait for the row to disappear
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.stalenessOf(itemRow));

        // Final verification
        boolean isStillPresent = driver.findElements(itemLocator).size() > 0;
        Assert.assertFalse(isStillPresent,
                "FAILURE: The product '" + productName + "' was NOT deleted.");
    }

    // ------------------ TC-C3 ------------------

    @Test(description = "TC-C3: Verify the cart page displays correctly when empty.")
    public void tc_c3_viewEmptyCart() {
        Cart cartPage = new Cart(driver);

        // Pre-requisite cleanup: Ensure the cart is empty
        cartPage.clickOnCartHeaderTab();
        deleteAllProductsIfPresent(cartPage);

        // TC-C3 Verification
        boolean isEmpty = driver.findElements(By.xpath("//tbody[@id='tbodyid']/tr")).isEmpty();
        Assert.assertTrue(isEmpty, "FAILURE: Cart is not empty.");
    }
}
