@login @homepage @allTest
Feature: Testing Homepage Items and Login related test cases .

  Background: 
    Given Validate User landed on homepage

  @InvalidLogin
  Scenario Outline: Verify logging in with Invalid credentials in Login Homepage
    Given Pass "<UserID>" on "UserID" Field
    And Pass "<Password>" on "Password" Field
    Then click on Login button
    Then Click on "OK" button from the alert

    Examples: 
      #Both Invalid
      | UserID     | Password | Tag      |
      | mngr6467   |  0000000 | @TC_0005 |
      #valid user ID and invalid Password
      | mngr646768 |  0000000 | @TC_0006 |
      #Invalid UserID and Valid Password
      | mngr111    | nebEsAg  | @TC_0007 |

  Scenario: Verify User can Clear userID Field after putting multiple Numeric userID by Mistake
    Given Pass 6 digit Numeric userID 123456 on userID Field and immidiately Clear it
    Then Pass 5 digit Numeric userID 12345 on userID Field and immidiately Clear it
    Then Pass 8 digit Numeric userID 12345678 on userID Field and immidiately Clear it
    Then Pass 10 digit Numeric userID 1234567891 on userID Field and immidiately Clear it
    Then click on Reset Button

  @TC_0010
  Scenario: Verify User can Clear userID Field after putting multiple Numeric userID by Mistake
    Given I enter and immediately clear the following UserIDs:
      |     123456 |
      |      12345 |
      |   12345678 |
      | 1234567891 |
    Then click on Reset Button

  @TC_0011  
  Scenario: Verify multiple UserID and Password combinations
    Given I try the following credentials:
      | UserID | Password  |
      | 123456 | pass123   |
      |  98765 | secret987 |
      | 111111 | testpwd   |

  #TC12 is homework
  @TC_0013 
  Scenario Outline: Verify logging in with valid credentials
    Given Pass "mngr646768" on "UserID" Field
    And Pass "nebEsAg" on "Password" Field
    Then click on Login button with Valid Credentials
