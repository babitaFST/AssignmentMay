@Functional
Feature: Retail Registration
@sanity
  Scenario: Retail Registration Sign Up
    Given Lunch Upskill
    When account icon shown
    Then click on Account icon
    And click on LoginRegister link 
    And  fill up the registeration Form for all mandatory fields
         |FirstName|manzoor|
         |LastName|mehadi|
         |Email|manzoor@gmail.com|
         |Telephone|9876543210|
         |Address1|yeshwanthapur|
         |Address2|bangalore|
         |city|bangalore|
         |PostCode|560022|
         |Country|India|
         |State|Karnataka|
         |password|India|
         |confirmPassword|India|
    And Submit the details
    When Verify Message "Congratulations! Your new account has been successfully created!"
		Then Close Browser
    
@sanity
  Scenario: Retail Loggin 
    Given Lunch Upskill
    Then click on Account icon
    And Login Retail Portal
    And click on Login link
    When Verify Header "My Account" 
	Then Close Browser