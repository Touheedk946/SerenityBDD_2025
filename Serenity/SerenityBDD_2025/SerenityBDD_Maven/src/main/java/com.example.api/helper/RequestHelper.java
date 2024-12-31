package com.example.api.helper;

import com.example.api.employeemodals.reqeusts.Department;
import com.example.api.employeemodals.reqeusts.JobTitle;
import com.example.api.employeemodals.reqeusts.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to generate a login request body
    public static String generateLoginRequestBody(String username, String password) throws JsonProcessingException {
        LoginRequest loginRequest = LoginRequest.builder()
                .setUsername(username)
                .setPassword(password)
                .build();
        return objectMapper.writeValueAsString(loginRequest);
    }


    // Method to generate job title creation request body
    public static String generateJobTitleRequestBody(String title, String description) throws JsonProcessingException {
        JobTitle jobTitle = JobTitle.builder()
                .setTitle(title)
                .setDescription(description)
                .build();
        return objectMapper.writeValueAsString(jobTitle);
    }

    // Method to generate department creation request body
    public static String generateDepartmentRequestBody(String name, String description) throws JsonProcessingException {
        Department department = Department.builder()
                .setName(name)
                .setDescription(description)
                .build();
        return objectMapper.writeValueAsString(department);
    }
}
