# Path: src/test/resources/features/cart_management.feature

Feature: Cart Management on Demoblaze

  Background:
    # Assuming "I am on the Demoblaze homepage" is defined elsewhere 
    # and handles navigation/login if necessary.
    Given I am on the Demoblaze homepage

  @cart_add
  Scenario: TC-C1: Verify added product details and price appear correctly
    Given a product named "Samsung galaxy s6" with price "360" is available
    When I navigate to the "Samsung galaxy s6" product page
    And I add the product to the cart
    And I accept the confirmation alert
    And I navigate to the Cart page
    Then the product "Samsung galaxy s6" with price "360" should appear in the cart
    And I delete all products from the cart for cleanup

  @cart_delete
  Scenario: TC-C2: Verify item can be successfully deleted from the cart
    Given a product named "Nokia lumia 1520" has been added to the cart
    And I navigate to the Cart page
    When I click the 'Delete' button for the product "Nokia lumia 1520"
    Then the product "Nokia lumia 1520" should not appear in the cart

  @cart_empty
  Scenario: TC-C3: Verify the cart page displays correctly when empty
    Given I navigate to the Cart page
    And I delete all products from the cart for cleanup
    Then the cart should be empty
