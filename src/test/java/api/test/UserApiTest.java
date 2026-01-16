package api.test;

import api.base.ApiBaseTest;
import api.pojo.User;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Listeners(AllureTestNg.class)
@Epic("API Automation")
@Feature("User Management")
public class UserApiTest extends ApiBaseTest {

    @Test(groups = "api")
    @Story("Create user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creates a new user and validates response contract")
    public void createUser_shouldReturnId() {

        User user = buildUser("Leandro", "lrojas", "leandro@test.com", "123456789");

        Response response = createUser(user);

        response.then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("username", equalTo("lrojas"));
    }

    @Test(groups = "api")
    @Story("Get user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieves an existing user and validates returned data")
    public void getUser_shouldReturnUserData() {

        Response response = getUser(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("username", not(emptyString()))
                .body("email", not(emptyString()))
                .body("phone", not(emptyString()));
    }

    @Test(groups = "api")
    @Story("Update user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Updates user and validates response structure")
    public void updateUser_shouldReturnUpdatedUser() {

        User updatedUser =
                buildUser("Leandro", "lrojas", "leandro@test.com", "999999999");

        Response response = updateUser(1, updatedUser);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("phone", equalTo("999999999"));
    }

    @Test(groups = "api")
    @Story("Delete user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deletes user and validates response")
    public void deleteUser_shouldReturnSuccessResponse() {

        Response response = deleteUser(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("isDeleted", equalTo(true));
    }

    /* =======================
       HELPERS
       ======================= */

    @Step("Build user object")
    private User buildUser(String name, String username, String email, String phone) {
        return new User(name, username, email, phone);
    }

    @Step("POST /users/add")
    private Response createUser(User user) {

        Response response =
                given()
                        .baseUri(getApiBaseUrl())
                        .contentType("application/json")
                        .body(user)
                .when()
                        .post("/users/add");

        attachToAllure(response, user);
        return response;
    }

    @Step("GET /users/{id}")
    private Response getUser(int userId) {

        Response response =
                given()
                        .baseUri(getApiBaseUrl())
                .when()
                        .get("/users/" + userId);

        attachToAllure(response, null);
        return response;
    }

    @Step("PUT /users/{id}")
    private Response updateUser(int userId, User user) {

        Response response =
                given()
                        .baseUri(getApiBaseUrl())
                        .contentType("application/json")
                        .body(user)
                .when()
                        .put("/users/" + userId);

        attachToAllure(response, user);
        return response;
    }

    @Step("DELETE /users/{id}")
    private Response deleteUser(int userId) {

        Response response =
                given()
                        .baseUri(getApiBaseUrl())
                .when()
                        .delete("/users/" + userId);

        attachToAllure(response, null);
        return response;
    }
}

