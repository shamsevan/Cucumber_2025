@dashboard  @allTest
Feature: Testing Logged user dashboard features

  Background: 
    Given Pass "mngr650508" on "UserID" Field
    And Pass "mEhAbem" on "Password" Field
    Then click on Login button with Valid Credentials

    
    @TC_0014  
    Scenario: Verify Manager button is visible in the left side
    Given "Manager" Button is visible
    
    
    @TC_015
  Scenario: Add New Customer using Excel data
    Given I open the "New Customer" page with PageButtontitle "Guru99 Bank New Customer Entry Page"
    When I fill the form using file "testcases_for_Automation.xlsx" and sheet "Invalid_Pin_Data" with fields:
      | Customer Name |
      | Gender        |
      | Date of Birth |
      | Address       |
      | City          |
      | State         |
      | PIN           |
      | Mobile Number |
      | E-mail        |
    Then I click Submit
    