Feature: Validating Reqres API
Scenario: Verify if Get Request is Run succesfully for reqres API
	Given URI "https://reqres.in" and user calls with GET http request
	Then the API call got success with status code 200
	And "first_name" for id "10" in response body is "Byron"
	

Scenario: Verify if Post is succesful
	Given Json body with name as "Bryant" and job as "BA"
	When user calls with "POST" http request
	Then the API Post call got success with status code 201
	And id must be generated
	 