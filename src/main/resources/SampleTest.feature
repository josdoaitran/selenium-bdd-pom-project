Feature: Sample Test for a Test Suite
  This feature will show the basic scenario and features of cucumber

  @Test1
  Scenario: Test-1: We will input the sample test
    Given I navigate to scientific page
    And I click sign up link
    Then I should see sign up page section: "Let's get started"
    And I input first name: "Admin"