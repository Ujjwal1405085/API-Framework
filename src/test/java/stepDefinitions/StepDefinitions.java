package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitions extends Utils {

	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	public static String place_id;
	TestDataBuild data = new TestDataBuild();


@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String Name, String Language, String Address) throws IOException {
		//resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req = given().spec(requestSpecification()).body(data.AddPlacePayload(Name,Language,Address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if(method.equalsIgnoreCase("POST"))
			response = req.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = req.when().get(resourceAPI.getResource());

	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(int code) {
		assertEquals(response.getStatusCode(), code);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String ExpectedResult) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, keyvalue), ExpectedResult);

	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  place_id = getJsonPath(response,"place_id");
	  req =  given().spec(requestSpecification()).queryParam("place_id", place_id);
	  user_calls_with_http_request(resourceName,"GET");
	  String actualName = getJsonPath(response,"name");
	  assertEquals(expectedName,actualName);
	}
	
	@Given("deletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	
	}


}
