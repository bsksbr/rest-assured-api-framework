/**
 * 
 */
package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * RequestManager class handles API requests for the test framework
 * 
 * Usage:
 * - This class provides reusable methods to send API request
 * - Helps in Maintaining class test code by centralizing request logic
 * - Future enhancements like Authentication, Logging or retries can be added here
 * 
 */
public class RequestManager {
	
	/*
	 * Send a GET Request to the given endpoint.
	 * @param endpoint API endpoint (relative URL)
	 * @return Response from the API
	 *  
	 * */
	
	public static Response sendGetRequest(String endpoint) {
		return RestAssured
				.given()
				.baseUri(ConfigManager.getBaseUrl()) // we Fetch Base URL dynamically
				.get(endpoint);
	}

}
