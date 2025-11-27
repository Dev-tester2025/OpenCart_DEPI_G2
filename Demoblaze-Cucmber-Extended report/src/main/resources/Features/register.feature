Feature: Register Functionality
  @HappyScenario
  Scenario Outline: Successful registration
    Given the user opens the homepage and clicks on Sign up header tab
    When the user enters valid "<username>" and "<password>" then clicks on Sign up button
    Then an alert with success message appears

    Examples:
      | username                | password |
      | testerforpassionss@grr.la | testing  |

  @NegativeScenario
  Scenario Outline: Invalid registration
    Given the already existing user opens the homepage and clicks on Sign up header tab
    When the user enters invalid "<username>" and "<password>" then clicks on Sign up button
    Then an alert with error message appears

    Examples:
      | username                | password |
      | testerforpassion@grr.la | testing  |