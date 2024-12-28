package com.example.api;

import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private RequestSpecification request;

    public ApiClient() {
        this.request = SerenityRest.given();
    }

    public Response sendGetRequest(String endpoint) {
        return request.get(endpoint);
    }

    public Response sendPostRequest(String endpoint, String body) {
        return request.body(body).post(endpoint);
    }

    public Response sendPutRequest(String endpoint, String body) {
        return request.body(body).put(endpoint);
    }

    public Response sendDeleteRequest(String endpoint) {
        return request.delete(endpoint);
    }
}
