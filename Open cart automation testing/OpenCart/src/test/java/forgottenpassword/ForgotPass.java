package forgottenpassword;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;

public class ForgotPass extends BaseTests {

    @Test (priority = 1)
    public void forgotPassTest(){
        homepage.clickOnMyAccountDropDownButton();
        LoginPage loginPage = homepage.clickOnLoginButton();
        ForgotPasswordPage forgotPasswordPage = loginPage.clickOnForgottenPasswordButton();
        forgotPasswordPage.insertEmail("testerfortesting@grr.la");
        forgotPasswordPage.clickOnContinueButton();

        //Assertion
        String actualResult = loginPage.getForgotPassResetSuccessMessage();
        String expectedResult = "text_success";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }

    @Test (priority = 2)
    public void forgotPassInvalidEmail(){

        homepage.clickOnMyAccountDropDownButton();
        LoginPage loginPage = homepage.clickOnLoginButton();
        ForgotPasswordPage forgotPasswordPage = loginPage.clickOnForgottenPasswordButton();
        forgotPasswordPage.insertEmail("testerfortesting@grr.laaaa");
        forgotPasswordPage.clickOnContinueButton();

        //Assertion
        String actualResult = forgotPasswordPage.getEmailNotFoundMessage();
        String expectedResult = "Warning: The E-Mail Address was not found in our records!";
        Assert.assertTrue(actualResult.contains(expectedResult));
    }
}
