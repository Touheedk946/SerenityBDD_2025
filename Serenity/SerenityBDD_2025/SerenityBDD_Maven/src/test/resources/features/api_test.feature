@smoke @api
Feature: Basic API Tests

  @createEmployee
  Scenario: Create a new employee
    Given I send a POST request with the following body:
      | name     | age | job_title   | department |
      | John Doe | 30  | Developer   | IT         |
    Then I should receive status code 201
    And the response should contain "employeeId"

  @updateEmployee @regression
  Scenario: Update an employee's information
    Given I send a PUT request with the following body:
      | name     | age | job_title   | department |
      | John Doe | 31  | Senior Dev  | IT         |
    Then I should receive status code 200
    And the response should contain "updated"
