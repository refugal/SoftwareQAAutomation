Feature: Navigate to authentication page

Background: User is Logged In
 		Given launch browser "chrome" in windows
    When User navigate to the "https://jqueryui.com/" url


  Scenario: User is able to drag and drop component
    When user selects "Droppable" option available under Interactions
    Then use is able to drag the "Drag me to my target" component to "Drop here" component
    
    
 
Scenario: User is able to select items on Selectable tab
    When user selects "Selectable" option available under Interactions Tab
    Then user is able to select multiple Options
    |Item 1  |
    |Item 3  |
    |Item 7  |
    
     
    Scenario: User is able to select items on ControlGroup tab
    When user selects "Controlgroup" option available under Widgets
    And user selects "SUV" from the dropdown
    And selects type as "Automatic"
    And checks "Insurance"
    And input number of cars as "2"
    And selects "Truck" from the dropdown
    And car type as "Standard"
    And checks "Insurance" in the rental car option
    And Number of Cars as "1"
    And click on "Book Now!" Button
    
    
    
    