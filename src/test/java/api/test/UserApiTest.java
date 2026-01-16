package api.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.base.ApiBaseTest;
import api.pojo.User;
import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
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
                .statusCode(201) // 201 porque así lo definimos en el stub
                .body("id", notNullValue())
                .body("username", equalTo("lrojas"));
    }

    @Test(groups = "api")
    @Story("Get user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieves an existing user and validates returned data")
    public void getUser_shouldReturnUserData() {

        getUser(1)
                .then()
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

        updateUser(1, updatedUser)
                .then()
                .statusCode(200) // Stub devuelve 200 en PUT
                .body("id", equalTo(1))
                .body("phone", equalTo("999999999"));
    }

    @Test(groups = "api")
    @Story("Delete user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deletes user and validates response")
    public void deleteUser_shouldReturnSuccessResponse() {

        deleteUser(1)
                .then()
                .statusCode(200) // Stub devuelve 200 en DELETE
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

        System.out.println("[API TEST] POST /users/add");

        Response response =
                given()
                        .contentType("application/json")
                        .body(user)
                .when()
                        .post("/users/add");

        attachToAllure(response, user);
        return response;
    }

    @Step("GET /users/{id}")
    private Response getUser(int userId) {

        System.out.println("[API TEST] GET /users/" + userId);

        Response response =
                given()
                .when()
                        .get("/users/" + userId);

        attachToAllure(response, null);
        return response;
    }

    @Step("PUT /users/{id}")
    private Response updateUser(int userId, User user) {

        System.out.println("[API TEST] PUT /users/" + userId);

        Response response =
                given()
                        .contentType("application/json")
                        .body(user)
                .when()
                        .put("/users/" + userId);

        attachToAllure(response, user);
        return response;
    }

    @Step("DELETE /users/{id}")
    private Response deleteUser(int userId) {

        System.out.println("[API TEST] DELETE /users/" + userId);

        Response response =
                given()
                .when()
                        .delete("/users/" + userId);

        attachToAllure(response, null);
        return response;
    }
}

