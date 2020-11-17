Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is getting added successfully using addPlaceAPI
	Given Add Place Payload with "<Name>" "<Language>" "<Address>"
	When user calls "addPlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<Name>" using "getPlaceAPI"

Examples:
	| Name | Language  | Address |
	|Ujjwal| Bengali   | 7191    |
	|Deepti| English   | Tirumala|
	
@DeletePlace	
Scenario: Verify is deletePlace scenario is working
	Given deletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"