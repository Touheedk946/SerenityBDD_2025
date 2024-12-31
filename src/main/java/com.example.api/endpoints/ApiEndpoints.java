package com.example.api.endpoints;

public class ApiEndpoints {

    public static final String LOGIN_ENDPOINT = "/hrmapi/login";
    public static final String EMPLOYEE_ENDPOINT = "/hrmapi/employee";
    public static final String JOB_TITLE_ENDPOINT = "/hrmapi/jobtitle";
    public static final String DEPARTMENT_ENDPOINT = "/hrmapi/department";
    public static final String BASE_URL ="https://api.orangehrm.com/";
    // Additional endpoints based on the feature file steps
    public static final String GET_EMPLOYEE_BY_ID_ENDPOINT = "/hrmapi/employee/{id}"; // Replace {id} with actual employee ID
    public static final String GET_ALL_JOB_TITLES_ENDPOINT = "/hrmapi/jobtitle/all";
    public static final String GET_ALL_DEPARTMENTS_ENDPOINT = "/hrmapi/department/all";

}