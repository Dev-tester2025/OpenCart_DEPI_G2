package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Cart;
import pages.Checkout;
import pages.Home;
import pages.Product;

public class CheckoutTests extends BaseTest {

    @Test(priority = 1)
    public void testSuccessfulCheckout() {
        // Add product to cart
        Home home = new Home(driver);
        Product product = home.clickOnNexus6ProductLink();
        product.clickOnAddToCartButton();
        String addToCartMessage = product.getProductAddedSuccessMessage();
        Assert.assertTrue(addToCartMessage.contains("Product added"));
        product.acceptAlert();

        // Go to cart
        Cart cart = new Cart(driver);
        cart.clickOnCartHeaderTab();
        Assert.assertTrue(cart.isProductInCart());

        // checkout
        Checkout checkout = cart.clickOnPlaceOrderButton();
        checkout.insertName("’Mohamed");
        checkout.insertCountry("Egypt");
        checkout.insertCity("Cairo");
        checkout.insertCreditCard("123456789");
        checkout.insertMonth("11");
        checkout.insertYear("2025");
        checkout.clickOnPurchaseButton();

        // Assertion
        String actualResult = checkout.getSuccessMessage();
        String expectedResult = "Thank you for your purchase!";
        Assert.assertTrue(actualResult.contains(expectedResult));

        checkout.clickOnConfirmButton();
    }

    @Test(priority = 2)
    public void testCheckoutWithEmptyFields() {
        // Add product to cart
        Home home = new Home(driver);
        Product product = home.clickOnNexus6ProductLink();
        product.clickOnAddToCartButton();
        String addToCartMessage = product.getProductAddedSuccessMessage();
        Assert.assertTrue(addToCartMessage.contains("Product added"));
        product.acceptAlert();

        // Go to cart
        Cart cart = new Cart(driver);
        cart.clickOnCartHeaderTab();
        Assert.assertTrue(cart.isProductInCart());

        // checkout without filling fields
        Checkout checkout = cart.clickOnPlaceOrderButton();
        checkout.clickOnPurchaseButton();

        // Assertion
        String actualResult = checkout.getAlertText();
        String expectedResult = "Please fill out Name and Creditcard.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        checkout.acceptAlert();
        checkout.clickOnCloseButton();
    }

    @Test(priority = 3)
    public void testCheckoutWithPartialFields() {
        // Add product to cart
        Home home = new Home(driver);
        Product product = home.clickOnNexus6ProductLink();
        product.clickOnAddToCartButton();
        String addToCartMessage = product.getProductAddedSuccessMessage();
        Assert.assertTrue(addToCartMessage.contains("Product added"));
        product.acceptAlert();

        // Go to cart
        Cart cart = new Cart(driver);
        cart.clickOnCartHeaderTab();
        Assert.assertTrue(cart.isProductInCart());

        // checkout with only name
        Checkout checkout = cart.clickOnPlaceOrderButton();
        checkout.insertName("Mohamed");
        checkout.clickOnPurchaseButton();

        // Assertion
        String actualResult = checkout.getAlertText();
        String expectedResult = "Please fill out Name and Creditcard.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        checkout.acceptAlert();
        checkout.clickOnCloseButton();
    }
}