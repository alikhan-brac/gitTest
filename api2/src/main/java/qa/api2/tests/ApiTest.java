package qa.api2.tests;

import static org.testng.Assert.assertTrue;

import java.io.File;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import qa.api2.models.Planet;
import qa.api2.util.Constants;
import qa.api2.util.JsonDataPuller;
import qa.api2.util.RestassuredApiUtil;

public class ApiTest {

	@Test
	public void swapiPlanetTest() {

		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/planet");

		// step1
		Response response = RestassuredApiUtil.getResponse(urlForData);

		// step2
		Planet planetApidata = RestassuredApiUtil.parseResponseToObject(response, Planet.class);
		System.out.println("object of step#2 is printed here>> " + planetApidata);

		// step3
		Planet planetTestdata = JsonDataPuller.parseJsonFileToObject(new File(Constants.TEST_DATA), Planet.class);
		System.out.println("testData of step#3 is printed here>> " + planetTestdata);
		assertTrue(planetApidata.getPlanetName().contentEquals(planetTestdata.getPlanetName())
				&& planetApidata.getRotationPeriod().contentEquals(planetTestdata.getRotationPeriod())
				&& planetApidata.getOrbitalPeriod().contentEquals(planetTestdata.getOrbitalPeriod()));
	}
}
