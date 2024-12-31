@smoke @api @login
Feature: API Testing Scenarios

  @loginValid
  Scenario: User login with valid credentials
    Given I send a POST request to login with the following body:
      | username  | password  |
      | validUser | validPass |
    Then I should receive status code 200
    And the response should contain "token"

  @loginInvalid
  Scenario: Invalid login with incorrect credentials
    Given I send a POST request to login with the following body:
      | username | password |
      | invalidUser | invalidPass |
    Then I should receive status code 401
    And the response should contain "error"


