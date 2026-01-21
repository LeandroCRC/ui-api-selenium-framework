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
        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).getDriver();

            
            if (driver != null) {
                saveScreenshot(driver);
            } else {
                System.out.println("[UI LISTENER] Driver es null, no se puede tomar screenshot.");
            }
        }
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

