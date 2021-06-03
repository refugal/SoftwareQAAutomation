Feature: Validating Appium Scenarios


Scenario: Verify Title on the Page
	Given User open application in Android
	Then Title must be available "selendroid-test-app"
	

Scenario: Verify homepage is displayed after clicking on No
	Given User open application in Android
	When User clicked on EN Button
	And select Option "No,No" 
	Then Homepage must be displayed
	

Scenario: Verify Name and Preferred car on Chrome
	Given User open application in Android
	When User clicked on Chrome logo Button
	Then the text must be available "Hello, can you please tell me your name?"
	When name is entered as "Test"
	And Preferred car is selected as "Mercedes"
	And clicked on Send me Your Name
	Then verify the text is available on the next screen "This is my way of saying hello"
	And name as "Test" and preferred car as "mercedes" is available in the next screen
	When clicked on Here on the bottom of the page
	Then page is navigated to the previous page 
	And default Preferred car selected is "Volvo"
	

Scenario: Verify Registration succesful and verify the details on the page
	Given User open application in Android
	When User clicked on File logo Button
	Then Title must be visible on the page "selendroid-test-app"
	And Text must be visible on the register page "Welcome to register a new User"
	Then Name field must be prepopulated as "Mr. Burns"
	And "Ruby" is selected as Programming Language by default
	When Register Page is filled with details as "Test" as Username, "test@test.com" as email, "test" as password,"testABC" as Name
	And accept adds is checked
	When Register Now button is clicked
	Then Verify the details as "Test" as Username, "test@test.com" as email, "test" as password,"testABC" as Name are available in the next page
	When user clicks on Register now
	Then homepage is available


Scenario: Verify Registration page is available after clicking on Show ProgressBar
	Given User open application in Android
	When User clicked on Show Progress Bar for a while
	Then User registration page is available after waiting for the progress bar to disappear
	

Scenario: Verify home screen is available After clicking on Press to throw unhandled exception
	Given User open application in Android
	When User clicked on Press to throw unhandled exception
	Then User navigates to homepage
	
	
	
	
	
	
	