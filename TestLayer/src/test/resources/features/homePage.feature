@homePage
Feature: Tests for Login Automation page

  Scenario Outline: Verify home page features
    Given the user browses the https://www.ultimateqa.com/automation/
    When the user clicks on the <link>
    Then the appropriate page will be loaded<actualUrl>

    Examples: 
      | link                                                        |actualUrl|
      | Login automation                                            |http://courses.ultimateqa.com/users/sign_in|
      | Big page with many elements                                 |https://www.ultimateqa.com/complicated-page/|
      | Fill out forms                                              |https://www.ultimateqa.com/filling-out-forms/|
      | Learn how to automate an application that evolves over time |https://www.ultimateqa.com/sample-application-lifecycle-sprint-1/|
      | Interactions with simple elements                           |https://www.ultimateqa.com/simple-html-elements-for-automation/|
