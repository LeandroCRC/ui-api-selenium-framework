package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.listeners.UiTestListener;
import ui.pages.GooglePage;

@Listeners(UiTestListener.class)

public class GoogleSearchTest extends BaseTest {

    @Test (groups = "ui")
    public void search_shouldDisplayResults() {

        // Arrange
        GooglePage googlePage = new GooglePage(driver);

        // Act
        googlePage.open();
        googlePage.search("Selenium WebDriver");

        // Assert
        Assert.assertTrue(
            googlePage.hasResults(),
            "Expected search results, but none were found"
        );
    }
}
