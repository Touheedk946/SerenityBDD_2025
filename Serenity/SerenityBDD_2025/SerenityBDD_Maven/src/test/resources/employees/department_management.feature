@department @api
Feature: Department Management API

  @getDepartments
  Scenario: Get list of all departments
    Given I send a GET request to retrieve all departments
    Then I should receive status code 200
    And the response should contain a list of departments

  @addDepartment
  Scenario: Add a new department
    Given I send a POST request to add a new department with the following body:
      | name   | description           |
      | HR     | Human Resources       |
    Then I should receive status code 201
    And the response should contain "departmentId"

  @getDepartments
  Scenario: Get list of all departments
    Given I send a GET request to retrieve all departments
    Then I should receive status code 200
    And the response should contain "departmentId"
