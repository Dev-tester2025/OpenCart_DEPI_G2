package login;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MyAccountPage;

public class LoginCases extends BaseTests {

    MyAccountPage myAccountPage;
    @Test (priority = 1)
     public void successfulLogin(){
        homepage.clickOnMyAccountDropDownButton();
        LoginPage loginPage = homepage.clickOnLoginButton();
        loginPage.insertEmail("testerfortesting@grr.la");
        loginPage.insertPassword("testing");
        myAccountPage = loginPage.clickOnLoginButton();

        //Assertion
        String actualResult = myAccountPage.getMyAccountHeaderText();
        String expectedResult = "My Account";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test (priority = 2)
    public void invalidUsername(){
        homepage.clickOnMyAccountDropDownButton();
        LoginPage loginPage = homepage.clickOnLoginButton();
        loginPage.insertEmail("testerfortesting@grr.laaa");
        loginPage.insertPassword("testing");
        loginPage.clickOnLoginButton();

        //Assertion
        String actualResult = loginPage.getInvalidUsernameOrPasswordMessage();
        String expectedResult = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test (priority = 3)
    public void invalidPassword(){
        homepage.clickOnMyAccountDropDownButton();
        LoginPage loginPage = homepage.clickOnLoginButton();
        loginPage.insertEmail("testerfortesting@grr.la");
        loginPage.insertPassword("testingtesting");
        loginPage.clickOnLoginButton();

        //Assertion
        String actualResult = loginPage.getInvalidUsernameOrPasswordMessage();
        String expectedResult = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }










    /*@Test
    public void successfulLogout() {

        successfulLogin();
        LogoutPage logoutPage = myAccountPage.clickOnSidebarLogoutButton();

        //Assertion
        String actualResult = logoutPage.getAccountLogoutHeaderText();
        String expectedResult = "Account Logout";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

     */





}
