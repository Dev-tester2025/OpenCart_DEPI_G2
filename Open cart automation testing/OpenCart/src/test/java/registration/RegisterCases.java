package registration;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountCreatedPage;
import pages.HomePage;
import pages.RegisterPage;

public class RegisterCases extends BaseTests {

    @Test
    public void successfulRegistration(){
        homepage.clickOnMyAccountDropDownButton();
        RegisterPage registerPage = homepage.clickOnRegisterButton();
        registerPage.insertFirstName("tester");
        registerPage.insertLastName("tester");
        registerPage.insertEmail("tester@grr.la");
        registerPage.insertPassword("testingpassword");
        registerPage.clickOnNewsletterSubscribeCheckBox();
        registerPage.clickOnPrivacyPolicyCheckbox();
        AccountCreatedPage accountCreatedPage = registerPage.clickOnContinueButton();

        //Assertion
        String actualResult = accountCreatedPage.getSuccessMessage();
        String expectedResult = "Your Account Has Been Created!";
        Assert.assertTrue(actualResult.contains(expectedResult));

    }
}
