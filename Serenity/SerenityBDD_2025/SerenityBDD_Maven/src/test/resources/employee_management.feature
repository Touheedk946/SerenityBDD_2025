@employee
Feature: Employee Management API

  @createEmployee
  Scenario: Create a new employee
    Given I send a POST request to create a new employee with the following body:
      | name     | age | job_title   | department |
      | John Doe | 30  | Developer   | IT         |
    Then I should receive status code 201
    And the response should contain "employeeId"

  @getEmployee
  Scenario: Get employee details
    Given I send a GET request for employee details with the employee id 1
    Then the employee response should be valid

  @updateEmployee
  Scenario: Update an employee's information
    Given I send a POST request to create a new employee with the following body:
      | name     | age | job_title   | department |
      | John Doe | 30  | Developer   | IT         |
    And I send a PUT request to update the employee's information with the following body:
      | name     | age | job_title   | department |
      | John Doe | 31  | Senior Dev  | IT         |
    Then I should receive status code 200
    And the response should contain "updated"

  @getJobTitles
  Scenario: Fetch all job titles
    Given I send a GET request to fetch all job titles
    Then the response should contain "jobTitle"

  @createJobTitle
  Scenario: Create a new job title
    Given I send a POST request to create a new job title with the following body:
      | title       | description    |
      | Senior Dev  | Software expert|
    Then I should receive status code 201
    And the response should contain "id"

  @getDepartments
  Scenario: Fetch all departments
    Given I send a GET request to fetch all departments
    Then the response should contain "department"

  @createDepartment
  Scenario: Create a new department
    Given I send a POST request to create a new department with the following body:
      | name         | description |
      | Engineering  | Develops software |
    Then I should receive status code 201
    And the response should contain "departmentId"
