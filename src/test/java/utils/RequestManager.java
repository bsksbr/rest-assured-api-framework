/**
 * 
 */
package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * RequestManager class handles API requests for the test framework
 * 
 * Usage:
 * - This class provides reusable methods to send API request
 * - Helps in Maintaining class test code by centralizing request logic
 * - Supports Future enhancements like Authentication, Logging or retries can be added here
 * 
 */
public class RequestManager {
	
	//	Logger Instance 
	private static final Logger logger = LoggerFactory.getLogger(RequestManager.class);
	
	
	
	/*
	 * Send a GET Request to the given endpoint.
	 * @param endpoint API endpoint (relative URL)
	 * @return Response from the API
	 *  
	 * */
	public static Response sendGetRequest(String endpoint) {
		String url = ConfigManager.getBaseUrl()+ endpoint;
		
		// Log API request details 
		logger.info("Sending GET Request to: {}",url);
		
		Response response =RestAssured
						.given()
						.baseUri(ConfigManager.getBaseUrl()) // we Fetch Base URL dynamically
						.get(endpoint);
		
		logger.info("Response Status Code: {}",response.getStatusCode());
		logger.info("Response Body: \n"+ response.getBody().asPrettyString());
		
		return response;
	}

}
