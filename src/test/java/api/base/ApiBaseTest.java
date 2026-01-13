package api.base;

import api.utils.AllureUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
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
