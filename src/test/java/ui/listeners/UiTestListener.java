package ui.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.base.BaseTest;

public class UiTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testInstance = result.getInstance();

        // Si por algún motivo no hay instancia del test, no hacemos nada
        if (!(testInstance instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) testInstance).getDriver();

        // Si el driver no se llegó a inicializar, no intentamos sacar screenshot
        if (driver == null) {
            return;
        }

        // Si el driver no implementa TakesScreenshot, tampoco hacemos nada
        if (!(driver instanceof TakesScreenshot)) {
            return;
        }

        saveScreenshot((TakesScreenshot) driver);
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] saveScreenshot(TakesScreenshot driver) {
        try {
            return driver.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            // Si algo falla al sacar el screenshot, no tiramos abajo todo el build
            return new byte[0];
        }
    }
}
