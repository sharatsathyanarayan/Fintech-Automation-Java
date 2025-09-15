package com.example.fintech.http;

import com.example.fintech.config.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private static RequestSpecification spec;

    public static RequestSpecification spec() {
        if (spec == null) {
            String base = ConfigLoader.cfg().apiBaseUrl();
            String token = ConfigLoader.cfg().authToken();
            spec = new RequestSpecBuilder()
                    .setBaseUri(base)
                    .addHeader("Authorization", token)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addFilter(new AllureRestAssured())
                    .log(LogDetail.METHOD)
                    .log(LogDetail.URI)
                    .log(LogDetail.BODY)
                    .build();
        }
        return spec;
    }
}
