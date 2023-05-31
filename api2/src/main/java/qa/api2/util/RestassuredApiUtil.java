package qa.api2.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import aquality.selenium.browser.AqualityServices;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestassuredApiUtil {

	public static Response getResponse(String urlForData) {
		return RestAssured.get(urlForData);
	}

	public static <T> T parseResponseToObject(Response response, Class<T> planetClass) {
		ObjectMapper omData = new ObjectMapper();
		T result = null;
		try {
			String jsonString = response.getBody().asString();
			result = omData.readValue(jsonString, planetClass);
		} catch (IOException e) {
			AqualityServices.getLogger().info("exception is thrown: "+e);		
		}
		return result;
	}
}
