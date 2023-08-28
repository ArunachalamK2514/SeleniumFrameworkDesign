@tag
Feature: Error Validation
  I want to handle the errors

 Background:
 Given I landed on the Ecommerce website

  @ErrorValidation
  Scenario Outline: Error Validation
    Given I logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

     Examples: 
      | username  								 | password 				 |
      | seleniumpractice@gmail.com | Seleniumpractice@ |
