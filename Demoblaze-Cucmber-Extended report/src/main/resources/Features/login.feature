Feature: Login functionality
@HappyScenario
  Scenario: Successful login
    Given user is on home page
    When user clicks login tab
    And user enters username "tester@grr.la"
    And user enters password "testing"
    And user clicks login button
    Then welcome message should contain "Welcome"
    And user logs out

  Scenario: Invalid login
    Given user is on home page
    When user clicks login tab
    And user enters username "testertester@grr.la"
    And user enters password "testingtesting"
    And user clicks login button
    Then error message should contain "User does not exist."
    And user accepts alert
