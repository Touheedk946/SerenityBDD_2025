Feature: Create a New User
  Scenario: Add a new user
    Given the API endpoint "/api/users" is available
    When I send a POST request with the following data:
      | name | job  |
      | John | Leader |
    Then the response status should be 201
    And the response should contain "id"
