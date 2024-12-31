package stepDefinitions;

import com.example.api.client.ApiService;
import com.example.api.helper.ResponseValidator;
import com.example.api.endpoints.ApiEndpoints;
import com.example.api.employeemodals.reqeusts.Department;
import com.example.api.employeemodals.reqeusts.Employee;
import com.example.api.employeemodals.reqeusts.JobTitle;
import com.example.api.employeemodals.reqeusts.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.util.List;
import java.util.Map;

import static com.example.api.endpoints.ApiEndpoints.BASE_URL;

public class ApiTestSteps {
    private Response response;
    ApiService apiService = new ApiService();
    ObjectMapper objectMapper = new ObjectMapper();

    @Given("User send a Post request to HRM API")
    public void user_send_a_post_request_to_hrm_api(io.cucumber.datatable.DataTable dataTable) {
    }

    // User Authentication Steps
    @Given("I send a POST request to login with the following body:")
    public void sendPostRequestForLogin(DataTable dataTable) throws JsonProcessingException {
        Map<String, String> loginData = dataTable.asMap(String.class, String.class);
        LoginRequest loginRequest = LoginRequest.builder()
                .setUsername(loginData.get("username"))
                .setPassword(loginData.get("password"))
                .build();

        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.LOGIN_ENDPOINT, loginRequest);
        Serenity.setSessionVariable("responseBody").to(response);
    }

    @Then("I should receive status code {int}")
    public void verifyStatusCode(int statusCode) {
        ResponseValidator.validateStatusCode(response, statusCode);
    }

    @Then("the response should contain {string}")
    public void verifyResponseField(String field) {
        ResponseValidator.validateResponseField(response, field);
    }

    @Given("I send a POST request with the following body:")
    public void sendPostRequestWithBody(io.cucumber.datatable.DataTable dataTable) throws JsonProcessingException {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> employeeData : data) {
            Employee employee = Employee.builder()
                    .setName(employeeData.get("name"))
                    .setAge(Integer.parseInt(employeeData.get("age")))
                    .setJobTitle(employeeData.get("job_title"))
                    .setDepartment(employeeData.get("department"))
                    .build();

            response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT, employee);
        }
    }

    @Given("User send a Post request to HRM API to create employee")
    public void sendPostRequestToCreateEmployee(DataTable dataTable) throws JsonProcessingException {
        Map<String, String> employeeData = dataTable.asMap(String.class, String.class);

        Employee employee = Employee.builder()
                .setName(employeeData.get("name"))
                .setAge(Integer.parseInt(employeeData.get("age")))
                .setJobTitle(employeeData.get("job_title"))
                .setDepartment(employeeData.get("department"))
                .build();

        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT, employee);
    }

    @Given("I send a PUT request with the following body:")
    public void sendPutRequestToUpdateEmployee(DataTable dataTable) throws JsonProcessingException {
        Map<String, String> employeeData = dataTable.asMap(String.class, String.class);

        Employee employee = Employee.builder()
                .setName(employeeData.get("name"))
                .setAge(Integer.parseInt(employeeData.get("age")))
                .setJobTitle(employeeData.get("job_title"))
                .setDepartment(employeeData.get("department"))
                .build();

        response = apiService.sendPutRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT, employee);
    }

    @Given("I send a GET request for employee details with id {int}")
    public void sendGetRequestForEmployeeDetails(int id) {
        String endpoint = ApiEndpoints.EMPLOYEE_ENDPOINT.replace("{id}", String.valueOf(id));
        response = apiService.sendGetRequest(endpoint);

        if (response == null) {
            throw new RuntimeException("Received null response from the API");
        }

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }

    @Given("I send a GET request for employee details with the employee id {int}")
    public void sendGetRequestForEmployee(int id) {
        response = apiService.sendGetRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT.replace("{id}", String.valueOf(id)));
    }

    @Then("the employee response should be valid")
    public void validateEmployeeResponse() {
        ResponseValidator.validateEmployeeResponse(response);
    }

    @Given("I send a GET request to fetch all job titles")
    public void sendGetRequestForJobTitles() {
        response = apiService.sendGetRequest(BASE_URL + ApiEndpoints.JOB_TITLE_ENDPOINT);
    }

    @Given("I send a POST request to create a new job title with the following body:")
    public void sendPostRequestForJobTitle(DataTable dataTable) throws JsonProcessingException {
        Map<String, String> jobTitleData = dataTable.asMap(String.class, String.class);

        JobTitle jobTitle = JobTitle.builder()
                .setTitle(jobTitleData.get("title"))
                .setDescription(jobTitleData.get("description"))
                .build();

        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.JOB_TITLE_ENDPOINT, jobTitle);
    }

    @Then("the job title response should be valid")
    public void validateJobTitleResponse() {
        ResponseValidator.validateJobTitleResponse(response);
    }

    @Given("I send a GET request to fetch all departments")
    public void sendGetRequestForDepartments() {
        response = apiService.sendGetRequest(BASE_URL + ApiEndpoints.DEPARTMENT_ENDPOINT);
    }

    @Given("I send a POST request to create a new department with the following body:")
    public void sendPostRequestForDepartment(DataTable dataTable) throws JsonProcessingException {
        Map<String, String> departmentData = dataTable.asMap(String.class, String.class);

        Department department = Department.builder()
                .setName(departmentData.get("name"))
                .setDescription(departmentData.get("description"))
                .build();

        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.DEPARTMENT_ENDPOINT, department);
    }

    @Then("the department response should be valid")
    public void validateDepartmentResponse() {
        ResponseValidator.validateDepartmentResponse(response);
    }

    @Then("the error response should be valid with message {string}")
    public void validateErrorResponse(String expectedErrorMessage) {
        ResponseValidator.validateErrorResponse(response, expectedErrorMessage);
    }
}
