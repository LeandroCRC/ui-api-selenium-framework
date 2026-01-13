package ui.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput))
            .sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMessageVisible() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).isDisplayed();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isOnLoginPage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }
}

