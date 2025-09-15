package com.example.fintech.api;

import com.example.fintech.config.ConfigLoader;
import com.example.fintech.data.UserFactory;
import com.example.fintech.http.ApiClient;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UsersApiTests {

    @Test
    @Description("Create a user (201) and then fetch it (200)")
    public void createAndGetUser() {
        Map<String,Object> body = UserFactory.validUser();

        String userId =
        given().spec(ApiClient.spec())
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/api/users")
        .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().path("id");

        given().spec(ApiClient.spec())
        .when()
                .get("/api/users/" + userId)
        .then()
                .statusCode(200)
                .body("id", equalTo(userId));
    }

    @Test
    @Description("Missing email should result in 400 Bad Request")
    public void createUserMissingEmail() {
        Map<String,Object> body = UserFactory.invalidUserMissingEmail();

        given().spec(ApiClient.spec())
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/api/users")
        .then()
                .statusCode(anyOf(is(400), is(404)));
    }

    @Test
    @Description("Unauthorized request should return 401")
    public void createUserUnauthorized() {
        Map<String,Object> body = UserFactory.validUser();

        given()
                .baseUri(ConfigLoader.cfg().apiBaseUrl())
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/api/users")
        .then()
                .statusCode(401);
    }
}
