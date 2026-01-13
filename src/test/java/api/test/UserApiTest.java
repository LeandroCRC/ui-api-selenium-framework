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
    @Description("Creates a new user and validates that the user is created correctly")
    public void createUser_shouldReturnId() {

        User user = buildUser("Leandro", "lrojas", "leandro@test.com", "123456789");

        Response response = createUser(user);

        validateUserCreation(response);
    }

    @Test(groups = "api")
    @Story("Get user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Retrieves an existing user and validates returned data")
    public void getUser_shouldReturnUserData() {

        int existingUserId = 1;

        getUser(existingUserId)
                .then()
                .statusCode(200)
                .body("id", equalTo(existingUserId))
                .body("username", not(emptyString()))
                .body("email", not(emptyString()))
                .body("phone", not(emptyString()));
    }

    @Test(groups = "api")
    @Story("Update user")
    @Severity(SeverityLevel.NORMAL)
    @Description("Updates user phone number and validates the change")
    public void updateUser_shouldUpdatePhone() {

        int existingUserId = 1;

        User updatedUser =
                buildUser("Leandro", "lrojas", "leandro@test.com", "999999999");

        updateUser(existingUserId, updatedUser)
                .then()
                .statusCode(200)
                .body("id", equalTo(existingUserId))
                .body("phone", equalTo("999999999"));
    }

    @Test(groups = "api")
    @Story("Delete user")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deletes an existing user and validates deletion flag")
    public void deleteUser_shouldDeleteUser() {

        int existingUserId = 1;

        deleteUser(existingUserId)
                .then()
                .statusCode(200)
                .body("id", equalTo(existingUserId))
                .body("isDeleted", equalTo(true));
    }

    /* =======================
       STEPS / HELPERS
       ======================= */

    @Step("Build user object")
    private User buildUser(String name, String username, String email, String phone) {
        return new User(name, username, email, phone);
    }

    @Step("Create user via POST /users/add")
    private Response createUser(User user) {

        Response response =
                given()
                        .contentType("application/json")
                        .body(user)
                .when()
                        .post("/users/add");

        attachToAllure(response, user);
        return response;
    }

    @Step("Validate user creation response")
    private void validateUserCreation(Response response) {
        response.then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("username", equalTo("lrojas"));
    }

    @Step("Get user with ID: {userId}")
    private Response getUser(int userId) {

        Response response =
                given()
                .when()
                        .get("/users/" + userId);

        attachToAllure(response, null);
        return response;
    }

    @Step("Update user with ID: {userId}")
    private Response updateUser(int userId, User user) {

        Response response =
                given()
                        .contentType("application/json")
                        .body(user)
                .when()
                        .put("/users/" + userId);

        attachToAllure(response, user);
        return response;
    }

    @Step("Delete user with ID: {userId}")
    private Response deleteUser(int userId) {

        Response response =
                given()
                .when()
                        .delete("/users/" + userId);

        attachToAllure(response, null);
        return response;
    }
}


