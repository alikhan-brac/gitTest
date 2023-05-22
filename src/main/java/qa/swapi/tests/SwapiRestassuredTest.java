package qa.swapi.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import qa.swapi.util.Constants;
import qa.swapi.util.JsonDataPuller;
import qa.swapi.util.RandomSelection;
import qa.swapi.util.RestassuredApiUtil;
import qa.swapi.util.StaticDataProvider;

public class SwapiRestassuredTest {

	@Test(priority = 1)
	public void movieTitleCheck() {
		Response response;
		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/allFilms");
		response = RestassuredApiUtil.getResponse(urlForData);
		assertEquals(response.getStatusCode(), Constants.STATUS_CODE_OK, "Status code is NOT 200");
		List<String> filmTitleFromApi = RestassuredApiUtil.getMovieNameFromApi(response);
		List<String> filmTitleFromTestdata = Constants.getMovieNameFromTestData(Constants.MOVIE_TITLES);
		filmTitleFromApi.retainAll(filmTitleFromTestdata);
		assertTrue(filmTitleFromTestdata.containsAll(filmTitleFromApi),
				"Response NOT containing movie titles as required.");
	}

	@Test(priority = 2, dataProvider = "dataForTest2", dataProviderClass = StaticDataProvider.class)
	public void producerNameCheck(String filmId) {
		Response response;
		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/allFilms") + filmId;
		response = RestassuredApiUtil.getResponse(urlForData);
		assertEquals(response.getStatusCode(), Constants.STATUS_CODE_OK, "Status code is NOT 200");
		assertEquals(RestassuredApiUtil.getProducerNameFromApi(response),
				JsonDataPuller.dataPuller(Constants.TEST_DATA, "/producerName"), "Producer Name NOT matching.");
	}

	@Test(priority = 3)
	public void peopleCheck() {
		Response response;
		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/allPeople")
				+ RandomSelection.randomPeopleSelection();
		response = RestassuredApiUtil.getResponse(urlForData);
		assertEquals(response.getStatusCode(), Constants.STATUS_CODE_OK, "Status code is NOT 200");
		assertTrue(RestassuredApiUtil.checkIfCharacterHasAtLeastOneFilm(response), "Producer Name NOT matching.");
	}
}
