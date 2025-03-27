/**
 * 
 */
package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.RequestManager;

/**
 *  GetStatusTest Class to verify API health/status using a GET request.
 *   
 */
public class GetStatusTest extends BaseTest {
	
	/*
	 * Sends a GET request to check if the API us running
	 * 
	 * @endpoint /status (Modify this based on the actual API)
	 * @expected 200 OK response with valid uptime data.
	 * 
	 * */
	
	@Test
	public void testGetAPIStatus() {
		
		//Define API endpoint (Modify if needed)
		String endpoint="/status";
		
		// Send GET request using RequestManager
		Response response=RequestManager.sendGetRequest(endpoint);
		
		// Print response for Debugging 
		System.out.println("Response: "+response.getBody().asPrettyString() );
		
		// Validate response status code
		Assert.assertEquals(response.getStatusCode(),200,"Status Code is not 200");
		
		// Validate response body
		Assert.assertEquals(response.getBody().asString().contains("UP"),"Response body does not contain UP" );
		
		
	}
}
