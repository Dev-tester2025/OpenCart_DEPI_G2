package logout;

import base.BaseTests;
import login.LoginCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MyAccountPage;

public class LogoutCases extends BaseTests {

    @Test
    public void successfulLogout(){
        homepage.clickOnMyAccountDropDownButton();
        LoginPage loginPage = homepage.clickOnLoginButton();
        loginPage.insertEmail("testerfortesting@grr.la");
        loginPage.insertPassword("testing");
        MyAccountPage myAccountPage = loginPage.clickOnLoginButton();
        LogoutPage logoutPage = myAccountPage.clickOnSidebarLogoutButton();

        //Assertion
        String actualResult = logoutPage.getAccountLogoutHeaderText();
        String expectedResult = "Account Logout";
        Assert.assertTrue(actualResult.contains(expectedResult));

    }
}
