package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.pages.GooglePage;

public class GoogleSearchTest extends BaseTest {

    @Test
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
