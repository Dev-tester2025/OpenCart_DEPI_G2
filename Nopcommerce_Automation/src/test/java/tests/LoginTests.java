package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessfulLogin(){
        Home home = new Home(driver);
        home.clickOnLoginHeaderButton();
        home.insertUsername("tester@grr.la");
        home.insertPassword("testing");
        home.clickOnLoginButton();

        //Assertion
        String actualResult = home.getWelcomeUserText();
        String expectedResult = "Welcome";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    public void testInvalidLogin(){
        Home home = new Home(driver);
        home.clickOnLoginHeaderButton();
        home.insertUsername("testertester@grr.la");
        home.insertPassword("testingtesting");
        home.clickOnLoginButton();

        //Assertion
        String actualResult = home.getAlertText();
        String expectedResult = "User does not exist.";
        Assert.assertTrue(actualResult.contains(expectedResult));

        //Cleanup
        home.acceptAlert();

    }
}
