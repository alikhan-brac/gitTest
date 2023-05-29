package qa.api2.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import qa.api2.models.Planet;

public class RestassuredApiUtil {

	public static Response getResponse(String urlForData) {
		Response response;
		response = RestAssured.get(urlForData);
		return response;
	}

	public static <T> Planet parseResponseOrFile(Response response, File jsonFileLocation) {
		ObjectMapper omData = new ObjectMapper();
		Planet planetData = new Planet();
		try {
			if (response != null) {
				String jsonString = response.getBody().asString();
				planetData = omData.readValue(jsonString, Planet.class);
			} else if (jsonFileLocation != null) {
				planetData = omData.readValue(jsonFileLocation, Planet.class);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return planetData;
	}
}
