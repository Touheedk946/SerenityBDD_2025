package com.example.api.helper;


import com.example.api.employeemodals.responses.DepartmentResponse;
import com.example.api.employeemodals.responses.EmployeeResponse;
import com.example.api.employeemodals.responses.ErrorResponse;
import com.example.api.employeemodals.responses.JobTitleResponse;
import io.restassured.response.Response;
import io.restassured.mapper.ObjectMapperType;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResponseValidator {
    //builderPattern
    // Employee Response Validation
    public static void validateEmployeeResponse(Response response) {
        EmployeeResponse employeeResponse = response.as(EmployeeResponse.class, ObjectMapperType.GSON);

        assertNotNull(employeeResponse.getEmployeeId(), "Employee ID should not be null");
        assertNotNull(employeeResponse.getName(), "Employee name should not be null");
        assertNotNull(employeeResponse.getJobTitle(), "Job title should not be null");
    }

    // Job Title Response Validation
    public static void validateJobTitleResponse(Response response) {
        JobTitleResponse jobTitleResponse = response.as(JobTitleResponse.class, ObjectMapperType.GSON);

        assertNotNull(jobTitleResponse.getId(), "Job title ID should not be null");
        assertNotNull(jobTitleResponse.getTitle(), "Job title should not be null");
    }

    // Department Response Validation
    public static void validateDepartmentResponse(Response response) {
        DepartmentResponse departmentResponse = response.as(DepartmentResponse.class, ObjectMapperType.GSON);

        assertNotNull(departmentResponse.getDepartmentId(), "Department ID should not be null");
        assertNotNull(departmentResponse.getName(), "Department name should not be null");
    }

    // Error Response Validation
    public static void validateErrorResponse(Response response, String expectedErrorMessage) {
        ErrorResponse errorResponse = response.as(ErrorResponse.class, ObjectMapperType.GSON);

        assertNotNull(errorResponse.getError(), "Error message should not be null");
        assertEquals(expectedErrorMessage, errorResponse.getError(), "Error message does not match");
    }

    // Common Status Code Validation
    public static void validateStatusCode(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    // Common Response Field Validation
    public static void validateResponseField(Response response, String field) {
        response.then().assertThat().body(field, notNullValue());
    }
}
