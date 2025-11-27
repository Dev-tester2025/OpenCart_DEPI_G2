package tests;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Home;

import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePageTests extends BaseTest {
    @Test
    public void TC_H1_verifyNavigationMenu() {
        Home home = new Home(driver);

        // Home
        home.clickHomeTab();
        Assert.assertTrue(driver.getCurrentUrl().contains("index"), "Home tab failed");

        // Contact
        home.clickContactTab();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modalTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h5[contains(text(),'New message')]")
                )
        );
        Assert.assertTrue(modalTitle.isDisplayed(), "Contact modal did not open");

        //Close modal
        home.closeContactModal();


        // Cart
        home.clickCartTab();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Cart tab failed");
    }

    // Contact modal
    @Test
    public void TC_H2_verifyContactFormSubmission() {

        Home home = new Home(driver);

        home.clickContactTab();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[contains(text(),'New message')]")
        ));

        home.enterContactEmail("test@example.com");
        home.enterContactName("Abdullah");
        home.enterContactMessage("This is an automated test message.");

        home.clickSendMessage();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Assert.assertTrue(alertText.contains("Thanks for the message"),
                "Contact form did NOT submit successfully!");

        home.waitForContactModalToDisappear();
    }

    //Carousel
    @Test
    public void TC_H3_verifyCarouselNavigation() {
        Home home = new Home(driver);

        home.clickHomeTab();

        String initialImageSrc = home.getActiveCarouselImageSrc();

        home.clickNextCarouselButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeToBe(home.activeCarouselImage, "src", initialImageSrc)
        ));

        String newImageSrc = home.getActiveCarouselImageSrc();

        Assert.assertNotEquals(newImageSrc, initialImageSrc, "Carousel image did not change (Next)");

        home.clickPrevCarouselButton();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeToBe(home.activeCarouselImage, "src", newImageSrc)
        ));

        String previousImageSrc = home.getActiveCarouselImageSrc();
        Assert.assertEquals(previousImageSrc, initialImageSrc, "Carousel did not revert to previous image (Previous)");
    }





}
