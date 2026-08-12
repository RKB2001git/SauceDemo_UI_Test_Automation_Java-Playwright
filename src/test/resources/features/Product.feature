
Feature: Sauce Demo shopping
  @regression
  Scenario: User adds a product to the cart
    Given user open the log in page
    When user enter username "error_user" and password "secret_sauce"
    And user click on login button
    Then user adds Sauce Labs Backpack to the cart
    And user opens the cart
    Then user verify Sauce Labs Backpack should be displayed in the cart
    And user clicks the "Open Menu" button
    And user logout from the application
