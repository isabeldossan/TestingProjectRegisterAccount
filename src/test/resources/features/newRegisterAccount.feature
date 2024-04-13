Feature: Register Supporter Account on Basketball England Membership Portal with four different scenarios

  As a supporter and fan of Basketball England
  I want to be able to Register a new Account on their Membership Portal
  So that I can get the updates I need about Basketball England Club

  Scenario Outline: Registration of New Supporter Account
    Given I have website for Basketball England membership portal open on <browser>
    And I have clicked to create a new Account
    And I have clicked on Supporter Account
    And I have written in "07/07/1997" for Date of Birth
    And I have written in "Isabel" for First Name
    And I have written in "<lastname>" for Last Name
    And I have written in random email for Email Address
    And I have written in random email for Confirm Email Address
    And I have written in "santosa" for Choose Your Password
    And I have written in "<secondPassword>" in Retype Your Password
    And I have marked the checkmark Fan in Describe your role
    And I <haveClicked> the checkmark for Agreed to Terms & Conditions
    And I have marked the checkmark I am aged over Eighteen
    And I have marked the checkmark for I have read the Code of Ethics & Conducts
    When I choose to Confirm & Join by clicking
    Then I shall get the result <result>

    Examples:
      | browser | lastname | secondPassword  | haveClicked | result                     |
      | chrome  | Santoson | santosa         | have        | Successfully               |
      | chrome  |          | santosa         | have        | Missing Lastname           |
      | chrome  | Santoson | santosa.santosa | have        | Un-matching Password       |
      | chrome  | Santoson | santosa         | have not    | Un-marked Terms&Conditions |
      | firefox | Santoson | santosa         | have        | Successfully               |
      | firefox |          | santosa         | have        | Missing Lastname           |
      | firefox | Santoson | santosa.santosa | have        | Un-matching Password       |
      | firefox | Santoson | santosa         | have not    | Un-marked Terms&Conditions |