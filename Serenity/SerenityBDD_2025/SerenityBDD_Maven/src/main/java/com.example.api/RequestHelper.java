package com.example.api;

import com.example.api.pojo.reqeusthelper.Department;
import com.example.api.pojo.reqeusthelper.Employee;
import com.example.api.pojo.reqeusthelper.JobTitle;
import com.example.api.pojo.reqeusthelper.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to generate a login request body
    public static String generateLoginRequestBody(String username, String password) throws JsonProcessingException {
        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password(password)
                .build();
        return objectMapper.writeValueAsString(loginRequest);
    }


    // Method to generate job title creation request body
    public static String generateJobTitleRequestBody(String title, String description) throws JsonProcessingException {
        JobTitle jobTitle = JobTitle.builder()
                .title(title)
                .description(description)
                .build();
        return objectMapper.writeValueAsString(jobTitle);
    }

    // Method to generate department creation request body
    public static String generateDepartmentRequestBody(String name, String description) throws JsonProcessingException {
        Department department = Department.builder()
                .name(name)
                .description(description)
                .build();
        return objectMapper.writeValueAsString(department);
    }
}
