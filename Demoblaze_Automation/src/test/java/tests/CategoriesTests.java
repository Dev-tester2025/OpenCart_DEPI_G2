package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Categories;
import pages.Home;

public class CategoriesTests extends BaseTest {

    Home home;
    Categories categories;

    @BeforeMethod
    public void pageObjectsSetup() {
        home = new Home(driver);
        categories = new Categories(driver);
    }



    // TC-CT1 - Filter products by category (Positive)

    @Test
    public void verifyFilterByCategory() {


        // Phones

        categories.clickPhones();
        Assert.assertTrue(
                home.isProductDisplayed("Nexus 6"),
                "Phones category should show 'Nexus 6' but it did NOT!"
        );



        // Laptops

        categories.clickLaptops();
        Assert.assertTrue(
                home.isProductDisplayed("Sony vaio i5"),
                "Laptops category should show 'Sony vaio i5' but it did NOT!"
        );
//


        // Monitors

        categories.clickMonitors();
        Assert.assertTrue(
                home.isProductDisplayed("Apple monitor 24"),
                "Monitors category should show 'Apple monitor 24' but it did NOT!"
        );
    }



    // TC-CT2 - Switch between categories (Positive)

    @Test
    public void verifySwitchingCategories() {


        categories.clickPhones();
        Assert.assertTrue(
                home.isProductDisplayed("Nexus 6"),
                "Phones should display 'Nexus 6'!"
        );


        categories.clickLaptops();
        Assert.assertTrue(
                home.isProductDisplayed("Sony vaio i5"),
                "Laptops should display 'Sony vaio i5'!"
        );


        categories.clickMonitors();
        Assert.assertTrue(
                home.isProductDisplayed("Apple monitor 24"),
                "Monitors should display 'Apple monitor 24'!"
        );
    }
}
