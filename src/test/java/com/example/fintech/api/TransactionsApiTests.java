package com.example.fintech.api;

import com.example.fintech.config.ConfigLoader;
import com.example.fintech.data.TransactionFactory;
import com.example.fintech.http.ApiClient;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TransactionsApiTests {

    @Test
    @Description("Create transaction and then list by user")
    public void createAndListTransactions() {
        String userId = "123";
        Map<String,Object> tx = TransactionFactory.validTx(userId);

        given().spec(ApiClient.spec())
                .contentType(ContentType.JSON)
                .body(tx)
        .when()
                .post("/api/transactions")
        .then()
                .statusCode(201)
                .body("status", equalTo("ok"));

        given().spec(ApiClient.spec())
        .when()
                .get("/api/transactions?userId=" + userId)
        .then()
                .statusCode(200)
                .body("transactions", not(empty()))
                .body("transactions[0].type", equalTo("transfer"));
    }

    @Test
    @Description("Unauthorized when missing auth header")
    public void createTransactionUnauthorized() {
        String userId = "123";
        Map<String,Object> tx = TransactionFactory.validTx(userId);

        given()
                .baseUri(ConfigLoader.cfg().apiBaseUrl())
                .contentType(ContentType.JSON)
                .body(tx)
        .when()
                .post("/api/transactions")
        .then()
                .statusCode(401);
    }
}
