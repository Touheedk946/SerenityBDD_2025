Feature: Get User Details
  Scenario: Retrieve user by ID
    Given the API endpoint "/api/users/2" is available
    When I send a GET request
    Then the response status should be 200
    And the response should contain user details
