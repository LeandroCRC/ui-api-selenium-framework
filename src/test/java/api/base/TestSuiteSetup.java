package api.base;

import api.mock.WireMockServerManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestSuiteSetup {

    @BeforeSuite(alwaysRun = true)
    public void startWireMock() {
        WireMockServerManager.startServer();
    }

    @AfterSuite(alwaysRun = true)
    public void stopWireMock() {
        WireMockServerManager.stopServer();
    }
}
