package api.base;

import api.mock.WireMockServerManager;
import api.utils.AllureUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    @BeforeClass(alwaysRun = true)
    public void setup() {

        // 1) Levantamos WireMock (localhost:8089)
        WireMockServerManager.startServer();

        // 2) Configuramos RestAssured para apuntar SIEMPRE al mock
        RestAssured.baseURI = "http://localhost:8089";

        System.out.println("[API TEST] Base URI configurada a: " + RestAssured.baseURI);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownApi() {
        // Si querés mantenerlo vivo durante toda la suite, podés comentar esto.
        // Para ahora lo dejamos comentado para simplificar:
        // WireMockServerManager.stopServer();
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

