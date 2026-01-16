package api.base;

import api.mock.WireMockServerManager;
import api.utils.AllureUtils;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    @BeforeClass
    public void apiSetUp() {
        WireMockServerManager.startServer();
    }

    @AfterClass(alwaysRun = true)
    public void apiTearDown() {
        WireMockServerManager.stopServer();
    }

    protected String getApiBaseUrl() {
        return WireMockServerManager.getBaseUrl();
    }

    protected void attachToAllure(Response response, Object requestBody) {

        if (requestBody != null) {
            AllureUtils.attachRequest(requestBody.toString());
        }

        if (response != null) {
            AllureUtils.attachResponse(response.getBody().asPrettyString());
        }
    }
}


