package api.base;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;

public class ApiBaseTest {

    @BeforeClass
    public void setupApi() {
        RestAssured.baseURI = "https://dummyjson.com";
    }
}
