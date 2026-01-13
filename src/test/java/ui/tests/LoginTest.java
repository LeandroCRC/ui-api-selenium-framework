package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.listeners.UiTestListener;
import ui.pages.LoginPage;

@Listeners(UiTestListener.class)

public class LoginTest extends BaseTest {

    @Test (groups = "ui")
    public void loginWithInvalidCredentials_shouldShowErrorAndRemainOnLoginPage() {

        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        String urlBeforeLogin = loginPage.getCurrentUrl();

        // Act
        loginPage.login("invalidUser", "invalidPassword");

        // Assert
        Assert.assertTrue(
            loginPage.isOnLoginPage(),
            "Expected to remain on login page after invalid login"
        );

        Assert.assertEquals(
            loginPage.getCurrentUrl(),
            urlBeforeLogin,
            "The URL changed after an invalid login attempt"
        );

        Assert.assertTrue(
            loginPage.isErrorMessageVisible(),
            "Expected error message to be visible after invalid login"
        );

        Assert.assertTrue(
            loginPage.getErrorMessageText().toLowerCase().contains("invalid"),
            "Error message does not indicate invalid credentials"
        );
    }
}

