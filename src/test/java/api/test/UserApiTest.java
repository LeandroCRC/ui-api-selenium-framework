package api.test;

import org.testng.annotations.Test;

import api.base.ApiBaseTest;
import api.pojo.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends ApiBaseTest {

    @Test
    public void createUser_shouldReturnId() {

        User user = new User(
                "Leandro",
                "lrojas",
                "leandro@test.com",
                "123456789"
        );

        Response response =
            given()
                .contentType("application/json")
                .body(user)
            .when()
                .post("/users/add");

        response.then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("username", equalTo("lrojas"));
    }

    @Test
    public void getUser_shouldReturnUserData() {

        int existingUserId = 1;

        given()
        .when()
            .get("/users/" + existingUserId)
        .then()
            .statusCode(200)
            .body("id", equalTo(existingUserId))
            .body("username", not(emptyString()))
            .body("email", not(emptyString()))
            .body("phone", not(emptyString()));
    }

    @Test
    public void updateUser_shouldUpdatePhone() {

        int existingUserId = 1;

        User updatedUser = new User(
                "Leandro",
                "lrojas",
                "leandro@test.com",
                "999999999"
        );

        given()
            .contentType("application/json")
            .body(updatedUser)
        .when()
            .put("/users/" + existingUserId)
        .then()
            .statusCode(200)
            .body("id", equalTo(existingUserId))
            .body("phone", equalTo("999999999"));
    }

    @Test
    public void deleteUser_shouldDeleteUser() {

        int existingUserId = 1;

        given()
        .when()
            .delete("/users/" + existingUserId)
        .then()
            .statusCode(200)
            .body("id", equalTo(existingUserId))
            .body("isDeleted", equalTo(true));
    }
}


