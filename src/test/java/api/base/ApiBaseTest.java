package api.base;

import api.mock.WireMockServerManager;
import api.utils.AllureUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    @BeforeClass
    public void setup() {
        // Levantamos WireMock solo para los tests de API
        WireMockServerManager.startServer();

        RestAssured.baseURI = "http://localhost:8089";
        System.out.println("[API TEST] Base URI configurada a: " + RestAssured.baseURI);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        // Apagamos WireMock al finalizar la suite de API
        WireMockServerManager.stopServer();
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


