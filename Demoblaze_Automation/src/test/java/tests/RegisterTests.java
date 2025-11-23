package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;

public class RegisterTests extends BaseTest {

    @Test
    public void testSuccessfulRegister(){
        Home home = new Home(driver);
        home.clickOnSignUpHeaderTab();
        home.insertSignUpUsername("testingpassion@grr.la");
        home.insertSignUpPassword("testing");
        home.clickOnSignUpButton();

        //Assertion
        String actualResult = home.getAlertText();
        String expectedResult = "Sign up successful";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();
    }


}
