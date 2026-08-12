Feature: Checkout Product
  @regression @Test_3
  Scenario: Complete checkout for Sauce Labs Backpack
    Given user open the log in page
    Then user enter username "standard_user" and password "secret_sauce"
    And user click on login button
    When user click Add to Cart for product "Sauce Labs Backpack"
    And user opens the cart
    And user proceeds to checkout
    And user enters following checkout information
      | Field Name | Value |
      | First Name | Rajat |
      | Last Name  | Kumar |
      | Zip/Postal Code | 75930 |
    Then user finishes the checkout
    When user completes the order
    Then order confirmation should be displayed
    And user clicks the "Open Menu" button
    And user logout from the application