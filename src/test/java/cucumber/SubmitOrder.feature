
@tag
Feature: Purchasing the items from ecommerce website
  I want to submit the order after adding the purchase items to the cart.
  
  Background:
	Given I landed on the Ecommerce website

  @Regression
  Scenario Outline: Submitting the order
    Given I logged in with username <username> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and enter <country> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | username  								 | password 					 | productName | country |
      | seleniumpractice@gmail.com | Seleniumpractice@13 | ZARA COAT 3 | India	 |
