package stepDefinitions;

import com.example.api.ApiService;
import com.example.api.ResponseValidator;
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
import net.serenitybdd.core.Serenity;

import java.util.List;
import java.util.Map;

import static com.example.api.endpoints.ApiEndpoints.BASE_URL;


public class ApiTestSteps {
    private Response response;
    ApiService apiService = new ApiService();
    ObjectMapper objectMapper = new ObjectMapper();

    @Given("User send  a Post request to HRM API")
    public void user_send_a_post_request_to_hrm_api(io.cucumber.datatable.DataTable dataTable) {


    }

    // User Authentication Steps
    @Given("I send a POST request to login with the following body:")
    public void sendPostRequestForLogin(DataTable dataTable) throws JsonProcessingException {
        // Convert DataTable to Map
        Map<String, String> loginData = dataTable.asMap(String.class, String.class);
        // Map the data to LoginRequest POJO
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(loginData.get("username"));
        loginRequest.setPassword(loginData.get("password"));

        // Convert the request object to JSON
        String body = objectMapper.writeValueAsString(loginRequest);

        // Send POST request
        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.LOGIN_ENDPOINT, body);
        System.out.println("ResponseBody: " + response.getBody().asString());

        // Store the response in Serenity session variables for later use
        Serenity.setSessionVariable("responseBody").to(response);
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

    @Given("I send a POST request with the following body:")
    public void sendPostRequestWithBody(io.cucumber.datatable.DataTable dataTable) throws JsonProcessingException {
        // Convert the DataTable into a List of Maps for easier access to data
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Iterate through the rows in the DataTable
        for (Map<String, String> employeeData : data) {
            // Create a POJO for the employee
            Employee employee = new Employee();
            employee.setName(employeeData.get("name"));
            employee.setAge(Integer.parseInt(employeeData.get("age")));
            employee.setJobTitle(employeeData.get("job_title"));
            employee.setDepartment(employeeData.get("department"));

            // Convert the Employee object to JSON
            String body = objectMapper.writeValueAsString(employee);

            // Send POST request to create the employee
            response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT, body);

            if (response != null && response.getBody() != null) {
                System.out.println("ResponseBody: " + response.getBody().asString());
            } else {
                System.out.println("Error: Response body is null");
            }

            // Optional: print the response body (for debugging)
            System.out.println("ResponseBody: " + response.getBody().asString());        }
    }

    @Given("User send a Post request to HRM API to create employee")
    public void sendPostRequestToCreateEmployee(DataTable dataTable) throws JsonProcessingException {
        // Convert the DataTable to Map for employee data
        Map<String, String> employeeData = dataTable.asMap(String.class, String.class);

        // Create a new Employee object using the setter methods
        Employee employee = new Employee();
        employee.setName(employeeData.get("name"));
        employee.setAge(Integer.parseInt(employeeData.get("age")));
        employee.setJobTitle(employeeData.get("job_title"));
        employee.setDepartment(employeeData.get("department"));

        // Convert the Employee object to JSON
        String body = objectMapper.writeValueAsString(employee);

        // Send POST request to create the employee
        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT, body);

        // Optional: print the response body (for debugging)
        System.out.println("ResponseBody: " + response.getBody().asString());
    }


    // Scenario 2: Update an employee's information
    @Given("I send a PUT request with the following body:")
    public void sendPutRequestToUpdateEmployee(DataTable dataTable) throws JsonProcessingException {
        // Convert the DataTable to Map for employee data
        Map<String, String> employeeData = dataTable.asMap(String.class, String.class);

        // Map the data to the Employee POJO with updated information
        Employee employee = new Employee();
        employee.setName(employeeData.get("name"));
        employee.setAge(Integer.parseInt(employeeData.get("age")));
        employee.setJobTitle(employeeData.get("job_title"));
        employee.setDepartment(employeeData.get("department"));

        // Convert the Employee object to JSON
        String body = objectMapper.writeValueAsString(employee);

        // Send PUT request to update the employee information
        response = apiService.sendPutRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT, body);

        // Optional: print the response body (for debugging)
        System.out.println("ResponseBody: " + response.getBody().asString());
    }




    @Given("I send a GET request for employee details with id {int}")
    public void sendGetRequestForEmployeeDetails(int id) {
        // Replace the placeholder in the API endpoint with the employee ID
        String endpoint = ApiEndpoints.EMPLOYEE_ENDPOINT.replace("{id}", String.valueOf(id));
        response = apiService.sendGetRequest(endpoint);

        // Add null check before trying to access response body
        if (response == null) {
            throw new RuntimeException("Received null response from the API");
        }

        // Optional: To print the response body and check its content
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }






    @Given("I send a GET request for employee details with the employee id {int}")
    public void sendGetRequestForEmployee(int id) {
        response = apiService.sendGetRequest(BASE_URL + ApiEndpoints.EMPLOYEE_ENDPOINT.replace("{id}", String.valueOf(id)));
    }

    @Then("the employee response should be valid")
    public void validateEmployeeResponse() {
        // Use ResponseValidator to validate employee response
        ResponseValidator.validateEmployeeResponse(response);
    }

    // Job Title Management Steps
    @Given("I send a GET request to fetch all job titles")
    public void sendGetRequestForJobTitles() {
        response = apiService.sendGetRequest(BASE_URL + ApiEndpoints.JOB_TITLE_ENDPOINT);
    }

    @Given("I send a POST request to create a new job title with the following body:")
    public void sendPostRequestForJobTitle(DataTable dataTable) throws JsonProcessingException {
        // Convert DataTable to a Map
        Map<String, String> jobTitleData = dataTable.asMap(String.class, String.class);

        // Retrieve the values from the Map
        String title = jobTitleData.get("title");
        String description = jobTitleData.get("description");

        // Create a new JobTitle object and set the values directly
        JobTitle jobTitle = new JobTitle();
        jobTitle.setTitle(title);
        jobTitle.setDescription(description);

        // Convert the JobTitle object to JSON string using ObjectMapper
        String body = objectMapper.writeValueAsString(jobTitle);

        // Send POST request with the job title data in the body
        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.JOB_TITLE_ENDPOINT, body);
    }



    @Then("the job title response should be valid")
    public void validateJobTitleResponse() {
        // Use ResponseValidator to validate job title response
        ResponseValidator.validateJobTitleResponse(response);
    }

    // Department Management Steps
    @Given("I send a GET request to fetch all departments")
    public void sendGetRequestForDepartments() {
        response = apiService.sendGetRequest(BASE_URL + ApiEndpoints.DEPARTMENT_ENDPOINT);
    }

    @Given("I send a POST request to create a new department with the following body:")
    public void sendPostRequestForDepartment(DataTable dataTable) throws JsonProcessingException {
        // Convert DataTable to a Map
        Map<String, String> departmentData = dataTable.asMap(String.class, String.class);

        // Retrieve the values from the Map
        String name = departmentData.get("name");
        String description = departmentData.get("description");

        // Create a new Department object and set the values directly
        Department department = new Department();
        department.setName(name);
        department.setDescription(description);

        // Convert the Department object to JSON string using ObjectMapper
        String body = objectMapper.writeValueAsString(department);

        // Send POST request with the department data in the body
        response = apiService.sendPostRequest(BASE_URL + ApiEndpoints.DEPARTMENT_ENDPOINT, body);
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