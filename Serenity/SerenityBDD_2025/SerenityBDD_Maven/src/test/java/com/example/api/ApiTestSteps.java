package com.example.api;

import com.example.api.endpoints.ApiEndpoints;
import com.example.api.pojo.reqeusthelper.Department;
import com.example.api.pojo.reqeusthelper.Employee;
import com.example.api.pojo.reqeusthelper.JobTitle;
import com.example.api.pojo.reqeusthelper.LoginRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiTestSteps {

    ApiService apiService = new ApiService();
    Response response;
    ObjectMapper objectMapper = new ObjectMapper();

    // User Authentication Steps
    @Given("I send a POST request to login with the following body:")
    public void sendPostRequestForLogin(DataTable dataTable) throws JsonProcessingException {
        // Map DataTable to LoginRequest POJO
        String username = dataTable.cell(0, 0);
        String password = dataTable.cell(0, 1);
        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password(password)
                .build();
        String body = objectMapper.writeValueAsString(loginRequest);

        response = apiService.sendPostRequest(ApiEndpoints.LOGIN_ENDPOINT, body);
    }

    @Then("I should receive status code {int}")
    public void verifyStatusCode(int statusCode) {
        // Validate status code using ResponseValidator
        ResponseValidator.validateStatusCode(response, statusCode);
    }

    @Then("the response should contain {string}")
    public void verifyResponseField(String field) {
        // Validate response field using ResponseValidator
        ResponseValidator.validateResponseField(response, field);
    }

    // Employee Management Steps
    @Given("I send a POST request to create a new employee with the following body:")
    public void sendPostRequestForEmployee(DataTable dataTable) throws JsonProcessingException {
        // Map DataTable to Employee POJO
        String name = dataTable.cell(0, 0);
        String age = dataTable.cell(0, 1);
        String jobTitle = dataTable.cell(0, 2);
        String department = dataTable.cell(0, 3);
        Employee employee = Employee.builder()
                .name(name)
                .age(age)
                .jobTitle(jobTitle)
                .department(department)
                .build();
        String body = objectMapper.writeValueAsString(employee);

        response = apiService.sendPostRequest(ApiEndpoints.EMPLOYEE_ENDPOINT, body);
    }

    @Given("I send a GET request for employee details with the employee id {int}")
    public void sendGetRequestForEmployee(int id) {
        response = apiService.sendGetRequest(ApiEndpoints.EMPLOYEE_ENDPOINT.replace("{id}", String.valueOf(id)));
    }

    @Then("the employee response should be valid")
    public void validateEmployeeResponse() {
        // Use ResponseValidator to validate employee response
        ResponseValidator.validateEmployeeResponse(response);
    }

    // Job Title Management Steps
    @Given("I send a GET request to fetch all job titles")
    public void sendGetRequestForJobTitles() {
        response = apiService.sendGetRequest(ApiEndpoints.JOB_TITLE_ENDPOINT);
    }

    @Given("I send a POST request to create a new job title with the following body:")
    public void sendPostRequestForJobTitle(DataTable dataTable) throws JsonProcessingException {
        // Map DataTable to JobTitle POJO
        String title = dataTable.cell(0, 0);
        String description = dataTable.cell(0, 1);
        JobTitle jobTitle = JobTitle.builder()
                .title(title)
                .description(description)
                .build();
        String body = objectMapper.writeValueAsString(jobTitle);

        response = apiService.sendPostRequest(ApiEndpoints.JOB_TITLE_ENDPOINT, body);
    }

    @Then("the job title response should be valid")
    public void validateJobTitleResponse() {
        // Use ResponseValidator to validate job title response
        ResponseValidator.validateJobTitleResponse(response);
    }

    // Department Management Steps
    @Given("I send a GET request to fetch all departments")
    public void sendGetRequestForDepartments() {
        response = apiService.sendGetRequest(ApiEndpoints.DEPARTMENT_ENDPOINT);
    }

    @Given("I send a POST request to create a new department with the following body:")
    public void sendPostRequestForDepartment(DataTable dataTable) throws JsonProcessingException {
        // Map DataTable to Department POJO
        String name = dataTable.cell(0, 0);
        String description = dataTable.cell(0, 1);
        Department department = Department.builder()
                .name(name)
                .description(description)
                .build();
        String body = objectMapper.writeValueAsString(department);

        response = apiService.sendPostRequest(ApiEndpoints.DEPARTMENT_ENDPOINT, body);
    }

    @Then("the department response should be valid")
    public void validateDepartmentResponse() {
        // Use ResponseValidator to validate department response
        ResponseValidator.validateDepartmentResponse(response);
    }

    // Error Response Validation Step (if required)
    @Then("the error response should be valid with message {string}")
    public void validateErrorResponse(String expectedErrorMessage) {
        // Use ResponseValidator to validate error response
        ResponseValidator.validateErrorResponse(response, expectedErrorMessage);
    }
}