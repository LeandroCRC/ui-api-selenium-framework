package ui.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchBox = By.name("q");
    private final By results = By.cssSelector("div#search h3");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get("https://www.google.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    public void search(String text) {
        WebElement input = driver.findElement(searchBox);
        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);
    }

    public boolean hasResults() {
        List<WebElement> elements =
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(results));
        return !elements.isEmpty();
    }
}
