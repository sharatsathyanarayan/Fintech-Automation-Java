package com.example.fintech.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServerSuite {
    public static WireMockServer server;

    @BeforeSuite(alwaysRun = true)
    public void startServer() {
        server = new WireMockServer(WireMockConfiguration.options().port(8089));
        server.start();

        // CORS preflight
        ResponseDefinitionBuilder cors = aResponse()
                .withHeader("Access-Control-Allow-Origin", "*")
                .withHeader("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .withHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        server.stubFor(options(urlMatching(".*")).willReturn(cors.withStatus(200)));

        // Users
        server.stubFor(post(urlEqualTo("/api/users"))
            .withHeader("Authorization", matching(".*"))
            .withRequestBody(matchingJsonPath("$.name"))
            .withRequestBody(matchingJsonPath("$.email"))
            .withRequestBody(matchingJsonPath("$.accountType"))
            .willReturn(aResponse()
                .withStatus(201)
                .withHeader("Content-Type", "application/json")
                .withHeader("Access-Control-Allow-Origin", "*")
                .withBody("{\"id\":\"123\",\"name\":\"John Doe\",\"email\":\"john@example.com\",\"accountType\":\"premium\"}")
            ));

        server.stubFor(post(urlEqualTo("/api/users"))
            .withHeader("Authorization", absent())
            .willReturn(aResponse().withStatus(401).withHeader("Content-Type","application/json").withBody("{\"error\":\"unauthorized\"}")));

        server.stubFor(get(urlPathMatching("/api/users/.*"))
            .withHeader("Authorization", matching(".*"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withHeader("Access-Control-Allow-Origin", "*")
                .withBody("{\"id\":\"123\",\"name\":\"John Doe\",\"email\":\"john@example.com\",\"accountType\":\"premium\"}")
            ));

        // Transactions
        server.stubFor(post(urlEqualTo("/api/transactions"))
            .withHeader("Authorization", matching(".*"))
            .withRequestBody(matchingJsonPath("$.userId"))
            .withRequestBody(matchingJsonPath("$.amount"))
            .withRequestBody(matchingJsonPath("$.type"))
            .withRequestBody(matchingJsonPath("$.recipientId"))
            .willReturn(aResponse()
                .withStatus(201)
                .withHeader("Content-Type", "application/json")
                .withHeader("Access-Control-Allow-Origin", "*")
                .withBody("{\"id\":\"tx-001\",\"status\":\"ok\"}")
            ));

        server.stubFor(post(urlEqualTo("/api/transactions"))
            .withHeader("Authorization", absent())
            .willReturn(aResponse().withStatus(401).withHeader("Content-Type","application/json").withBody("{\"error\":\"unauthorized\"}")));

        server.stubFor(get(urlPathEqualTo("/api/transactions"))
            .withQueryParam("userId", matching(".*"))
            .withHeader("Authorization", matching(".*"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withHeader("Access-Control-Allow-Origin", "*")
                .withBody("{\"transactions\":[{\"id\":\"tx-001\",\"amount\":100.5,\"type\":\"transfer\",\"recipientId\":\"456\"}]}")
            ));
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer() {
        if (server != null) server.stop();
    }
}
