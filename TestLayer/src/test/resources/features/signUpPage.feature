@signUp

Feature: Tests for signup page

Scenario Outline: Open Sign Up Page
Given the user browses the <site>
When the user clicks on the <link>
Then the signup page is loaded 
Examples:
|site                                       |link                |
|http://courses.ultimateqa.com/users/sign_in|Create a new account|

Scenario: Linked In Sign Up
Given the user is on the signup page
When the user clicks on LinkedIn in signup page
Then LinkedIn SignIn pop-up opens

Scenario Outline: Creating new user account - negative scenarios
Given the user is on the signup page
When the user submits the login credentials
|<firstName>|<lastName>|<email>|<password>|
Then a new user account <check> created
And system throws an error message as <message>
Examples:
|firstName|lastName|email|password|check|message|
||gates|random|abcde123|has not been|First name can't be blank|
|bill||random|abcde123|has not been|Last name can't be blank|
|bill|gates||abcde123|has not been|Email can't be blank|
|bill|gates|random||has not been|Password can't be blank|

Scenario Outline: Creating new user account
Given the user is on the signup page
When the user submits the login credentials
|<firstName>|<lastName>|<email>|<password>|
Then a new user account <check> created
Examples:
|firstName|lastName|email|password|check|message|
|bill|gates|random|abcde123|has been||