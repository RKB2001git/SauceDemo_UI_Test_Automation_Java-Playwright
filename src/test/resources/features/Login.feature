Feature: Login to Sauce Demo
  @smoke @Test_1
  Scenario: Login to Sauce Demo with valid credentials
    Given user open the log in page
    When user enter username "problem_user" and password "secret_sauce"
    And user click on login button
    Then user should see the products page

  @smoke @Test_2
  Scenario: Login with invalid credentials
    Given user open the log in page
    When user enter username "problem_user" and password "wrong_password"
    And user click on login button
    Then user should see the login error message