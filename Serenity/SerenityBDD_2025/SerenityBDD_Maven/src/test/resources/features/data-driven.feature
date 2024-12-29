@api @dataDriven
Feature: Data-driven API Tests

  @createEmployee
  Scenario Outline: Create a new employee with different details
    Given I send a POST request with the following body:
      | name       | age | job_title   | department |
      | <name>     | <age> | <job_title> | <department> |
    Then I should receive status code 201
    And the response should contain "employeeId"

    Examples:
      | name      | age | job_title  | department |
      | John Doe  | 30  | Developer  | IT         |
      | Jane Doe  | 28  | Tester     | QA         |
      | Sam Smith | 35  | Manager    | HR         |
