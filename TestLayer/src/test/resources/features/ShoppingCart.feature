@ignore
Feature: Title of your feature

  Scenario Outline: Verify shopping cart
    Given the user browses the site <site>
    When the user searches for <product>
    Then the current page will be the search result page
    When the user adds the 1 product in the search result to the cart
    Then the shopping cart has the added product

    Examples: 
      | site       						| product |
      | http://www.amazon.com | soap   |