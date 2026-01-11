package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	// 1️⃣ ATRIBUTOS
	private WebDriver driver;

	// 2️⃣ LOCATORS
	By usernameInput = By.id("username");
	By passwordInput = By.id("password");
	By loginButton = By.cssSelector("button[type='submit']");
	By errorMessage = By.id("flash");
	

	// 3️⃣ CONSTRUCTOR
	public LoginPage(WebDriver driver) {
	    this.driver = driver;
	}

	// 4️⃣ ACCIONES
	public void open() {
	    driver.get("https://the-internet.herokuapp.com/login");
	}

	public void login(String username, String password) {
	    driver.findElement(usernameInput).sendKeys(username);
	    driver.findElement(passwordInput).sendKeys(password);
	    driver.findElement(loginButton).click();
	}

	// 5️⃣ CONSULTAS
	public boolean isErrorMessageVisible() {
	    return driver.findElement(errorMessage).isDisplayed();
	}

	public String getCurrentUrl() {
	    return driver.getCurrentUrl();
	}

	public String getErrorMessageText() {
	    return driver.findElement(errorMessage).getText();
	}
	
	public boolean isOnLoginPage() {
	    return driver.findElement(loginButton).isDisplayed();
	}

}
