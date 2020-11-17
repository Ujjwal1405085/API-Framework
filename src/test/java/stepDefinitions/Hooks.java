package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		StepDefinitions m = new StepDefinitions();

		if (StepDefinitions.place_id == null) {
			System.out.println("Running Hooks class code");
			m.add_Place_Payload_with("Hello", "German", "Germany");
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Hello", "getPlaceAPI");
		}

	}

}
