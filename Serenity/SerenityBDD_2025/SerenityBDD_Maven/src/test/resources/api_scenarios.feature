Feature: API Testing Scenarios

  Scenario: User login with valid credentials
    Given I send a POST request with the following body:
      | username  | password  |
      | validUser | validPass |
    Then I should receive status code 200
    And the response should contain "token"

  Scenario: Get employee details with valid ID
    Given I send a GET request for employee details with id 101
    Then I should receive status code 200
    And the response should contain "name"
