/**
 * 
 */
package api;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import utils.ConfigManager;

/**
 *  BaseTest setsup the base configurations for All API test classes
 */
public class BaseTest {
	/*
	 * Sets up the base URI to RestAssured framework before executing tests
	 * 
	 * */
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI=ConfigManager.getBaseUrl();
	}
	
	
}
