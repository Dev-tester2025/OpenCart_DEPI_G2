<<<<<<< HEAD:Demoblaze_Automation/Demoblaze_Automation_TestNG/src/test/java/tests/ProductTests.java
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;
import pages.Product;

public class ProductTests extends BaseTest {

    @Test
    public void TestProductPage(){
        Home home = new Home(driver);
        Product product = home.clickOnNexus6ProductLink();
        product.clickOnAddToCartButton();

        //Assertion
        String actualResult = product.getProductAddedSuccessMessage();
        String expectedResult = "Product added";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        product.acceptAlert();
    }
}
=======
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;
import pages.Product;

public class ProductTests extends BaseTest {

    @Test
    public void TestProductPage(){
        Home home = new Home(driver);
        Product product = home.clickOnNexus6ProductLink();
        product.clickOnAddToCartButton();

        //Assertion
        String actualResult = product.getProductAddedSuccessMessage();
        String expectedResult = "Product added";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        product.acceptAlert();
    }

   //Pagination
    @Test
    public void TC_H4_verifyNextPrevPagination() {
        Product productPage = new Product(driver);

        String firstPageProduct = productPage.getFirstProductName();

        productPage.clickNextAndWaitForChange();
        String nextPageProduct = productPage.getFirstProductName();

        Assert.assertNotEquals(firstPageProduct, nextPageProduct, "Next button did not load new products.");

        productPage.clickPreviousAndWaitForChange();
        String prevPageProduct = productPage.getFirstProductName();

        Assert.assertNotEquals(nextPageProduct, prevPageProduct, "Previous button did not load previous products.");
    }

}


>>>>>>> edc5221168e6ac44528b5a646a45f58bef7fcc12:Demoblaze_Automation/src/test/java/tests/ProductTests.java
