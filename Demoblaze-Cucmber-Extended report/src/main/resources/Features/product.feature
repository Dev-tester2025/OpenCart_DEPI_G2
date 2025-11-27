Feature: Add to Cart Functionality on Product Page
  @HappyScenario
  Scenario: Successful Add to Cart
    Given the user is on the homepage and clicks on Nexus product
    When the user clicks on Add to Cart button on the product page
    Then an alert with a success message appears "Product added"