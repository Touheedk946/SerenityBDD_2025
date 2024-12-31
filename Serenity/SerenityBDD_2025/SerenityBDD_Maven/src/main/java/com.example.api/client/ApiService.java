package com.example.api.client;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class ApiService {

    private ApiClient apiClient;

    public ApiService() {
        apiClient = new ApiClient();
    }

    // For sending a GET request
    public Response sendGetRequest(String endpoint) {
        return apiClient.sendGetRequest(endpoint);
    }

    // For sending a POST request
    public Response sendPostRequest(String endpoint, Object body) {
        return apiClient.sendPostRequest(endpoint, body);
    }

    // For sending a PUT request
    public Response sendPutRequest(String endpoint, Object body) {
        return apiClient.sendPutRequest(endpoint, body);
    }

    // For sending a DELETE request
    public Response sendDeleteRequest(String endpoint) {
        return apiClient.sendDeleteRequest(endpoint);
    }

    // A method to validate response (e.g., status code or other checks)
    public void validateResponse(Response response, String field) {
        response.then().assertThat().body(field, Matchers.notNullValue());
    }
}
