package ui.tests;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ui.base.BaseTest;
import ui.listeners.UiTestListener;
import ui.pages.GooglePage;

@Listeners(UiTestListener.class)
public class GoogleSearchTest extends BaseTest {

	@Test(groups = "ui")
	public void googleSearch_shouldDemonstrateBotLimitation() {

	    GooglePage googlePage = new GooglePage(driver);

	    googlePage.open();
	    googlePage.search("Selenium WebDriver automation");

	    try {
	        boolean hasResults = googlePage.hasResults();
	        System.out.println("Google returned results? " + hasResults);

	        Assert.fail(
	            "El test de Google está diseñado como ejemplo didáctico.\n" +
	            "En entornos reales (headless/CI), Google suele bloquear la automatización " +
	            "con CAPTCHA / verificación de bot. Si este test llegara a pasar, " +
	            "no lo consideraríamos una validación estable del UI."
	        );

	    } catch (TimeoutException e) {

	        Assert.fail(
	            "Google bloqueó la automatización (CAPTCHA / protección anti-bots).\n" +
	            "Este test está INTENCIONALMENTE fallando para demostrar que:\n" +
	            "- Entiendo que Google no es un AUT estable para E2E.\n" +
	            "- En estos casos, se debe usar una aplicación controlada (como el LoginTest).\n" +
	            "Por eso implementé otro test de UI estable contra un sitio demo.",
	            e
	        );
	    }
	}
}
