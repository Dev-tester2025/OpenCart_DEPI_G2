package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;

public class RegisterTests extends BaseTest {

    @Test (priority = 1)
    public void testSuccessfulRegister(){
        Home home = new Home(driver);
        home.clickOnSignUpHeaderTab();
        home.insertSignUpUsername("testingpassionssss@grr.la");
        home.insertSignUpPassword("testing");
        home.clickOnSignUpButton();

        //Assertion
        String actualResult = home.getAlertText();
        String expectedResult = "Sign up successful";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();
        driver.navigate().refresh();
    }

    @Test (priority = 2)
    public void testInvalidRegister(){
        Home home = new Home(driver);
        home.clickOnSignUpHeaderTab();
        home.insertSignUpUsername("testingpassion@grr.la");
        home.insertSignUpPassword("testing");
        home.clickOnSignUpButton();

        //Assertion
        String actualResult = home.getAlertText();
        String expectedResult = "This user already exist.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();
        driver.navigate().refresh();
    }


}
