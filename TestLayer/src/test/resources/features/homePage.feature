@homePage
Feature: Tests for Login Automation page

  Scenario Outline: Verify home page features
    Given the user browses the site https://www.ultimateqa.com/automation/
    When the user clicks on the <link>
    Then the appropriate page will be loaded

    Examples: 
      | link                                                        |
      | Login automation                                            |
      | Big page with many elements                                 |
      | Fill out forms                                              |
      | Learn how to automate an application that evolves over time |
      | Interactions with simple elements                           |
