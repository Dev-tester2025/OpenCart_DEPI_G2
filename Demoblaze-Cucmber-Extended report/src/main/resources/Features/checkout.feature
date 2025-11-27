Feature: Checkout functionality

  Background:
    Given user on home page
    When user clicks on Nexus 6 product
    And user adds product to cart
    Then product added message should contain "Product added"
    And user accepts product alert
    When user navigates to cart
    Then product should be in cart

  @HappyPath @Checkout
  Scenario: Successful checkout with all fields filled
    When user clicks on place order button
    And user enters checkout details:
      | name       | Mohamed   |
      | country    | Egypt     |
      | city       | Cairo     |
      | creditCard | 123456789 |
      | month      | 11        |
      | year       | 2025      |
    And user clicks on purchase button
    Then success message should contain "Thank you for your purchase!"
    And user clicks on confirm button

  @NegativeTest @Checkout
  Scenario: Checkout with empty fields
    When user clicks on place order button
    And user clicks on purchase button
    Then checkout alert message should contain "Please fill out Name and Creditcard."
    And user accepts checkout alert
    And user clicks on close button

  @NegativeTest @Checkout
  Scenario: Checkout with only name filled
    When user clicks on place order button
    And user enters name "Mohamed"
    And user clicks on purchase button
    Then checkout alert message should contain "Please fill out Name and Creditcard."
    And user accepts checkout alert
    And user clicks on close button