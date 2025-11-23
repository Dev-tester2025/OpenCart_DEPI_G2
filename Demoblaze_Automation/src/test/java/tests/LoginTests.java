package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessfulLogin(){
        Home home = new Home(driver);
        home.clickOnLoginHeaderTab();
        home.insertLoginUsername("tester@grr.la");
        home.insertLoginPassword("testing");
        home.clickOnLoginButton();

        //Assertion
        String actualResult = home.getWelcomeUserText();
        String expectedResult = "Welcome";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void testInvalidLogin(){
        Home home = new Home(driver);
        home.clickOnLoginHeaderTab();
        home.insertLoginUsername("testertester@grr.la");
        home.insertLoginPassword("testingtesting");
        home.clickOnLoginButton();

        //Assertion
        String actualResult = home.getAlertText();
        String expectedResult = "User does not exist.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();

    }
}
