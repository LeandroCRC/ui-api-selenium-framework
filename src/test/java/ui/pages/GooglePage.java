package ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;


public class GooglePage {

    // =========================
    // 1️⃣ ATRIBUTOS (ESTADO)
    // =========================
    // Cosas que la página necesita para funcionar

    private WebDriver driver;

    // =========================
    // 2️⃣ LOCATORS
    // =========================
    // Representan elementos del UI
    // NO hacen acciones
    // Solo describen "dónde está" algo

    private By searchInput = By.name("q");
    private By resultsList = By.cssSelector("div.g");
    
    // =========================
    // 3️⃣ CONSTRUCTOR
    // =========================
    // Inicializa la página con el navegador

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    // =========================
    // 4️⃣ ACCIONES DEL USUARIO
    // =========================
    // Métodos void
    // Representan cosas que haría una persona

    public void open() {
        driver.get("https://www.google.com");
    }

    public void search(String text) {
        WebElement input = getSearchInputWhenReady();
        input.clear();
        input.sendKeys(text);
        input.submit();
    }


    // =========================
    // 5️⃣ CONSULTAS / ESTADO
    // =========================
    // Métodos que RESPONDEN preguntas
    // Devuelven datos (boolean, String, int)
    // NO contienen asserts

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOnResultsPage() {
        return getWait().until(driver -> !driver.getTitle().isEmpty());
    }

    public boolean hasResults() {
        return getWait().until(driver ->
            driver.findElements(resultsList).size() > 0
        );
    }



    // =========================
    // 6️⃣ WAITS (UTILIDADES INTERNAS)
    // =========================
    // Métodos de soporte
    // No se usan desde el test
    // Ayudan a que las acciones sean estables

  
    
    private WebElement getSearchInputWhenReady() {
        return getWait().until(
            ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
    }
    
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }


}


