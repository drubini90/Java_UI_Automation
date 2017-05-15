@forms
Feature: Tests for filling out forms

  @form1
  Scenario Outline: Filling out form 1
    Given the user is on the forms page
    When the user fills form 1
      | <name> | <message> |
    Then the appropriate message is displayed<expectedMessage> for form1

    Examples: 
      | name  | message       | expectedMessage                       |
      | Priya | Nice Article! | Form filled out successfully          |
      | Priya |               | Please, fill in the following fields: |
      |       | Nice Article! | Please, fill in the following fields: |

  @form2
  Scenario Outline: Filling out form 2
    Given the user is on the forms page
    When the user fills form 2
      | <name> | <message> |
    Then the appropriate message is displayed<expectedMessage> for form2

    Examples: 
      | name  | message       | expectedMessage                       |
      | Priya | Nice Article! | Success                               |
      | Priya |               | Please, fill in the following fields: |
      |       | Nice Article! | Please, fill in the following fields: |
