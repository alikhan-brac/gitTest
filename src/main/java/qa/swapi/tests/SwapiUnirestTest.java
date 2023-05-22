package qa.swapi.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import qa.swapi.util.Constants;
import qa.swapi.util.JsonDataPuller;
import qa.swapi.util.RandomSelection;
import qa.swapi.util.StaticDataProvider;
import qa.swapi.util.UnirestApiUtil;

public class SwapiUnirestTest {

	@Test(priority = 1)
	public void movieTitleCheck() {
		HttpResponse<JsonNode> response;
		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/allFilms");
		response = UnirestApiUtil.getResponse(urlForData);
		assertEquals(response.getStatus(), Constants.STATUS_CODE_OK, "Status code is NOT 200");
		List<String> filmTitleFromApi = UnirestApiUtil.getMovieNameFromApi(response);
		List<String> filmTitleFromTestdata = Constants.getMovieNameFromTestData(Constants.MOVIE_TITLES);
		filmTitleFromApi.retainAll(filmTitleFromTestdata);
		assertTrue(filmTitleFromTestdata.containsAll(filmTitleFromApi),
				"Response NOT containing movie titles as required.");
	}

	@Test(priority = 2, dataProvider = "dataForTest2", dataProviderClass = StaticDataProvider.class)
	public void producerNameCheck(String filmId) {
		HttpResponse<JsonNode> response;
		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/allFilms") + filmId;
		response = UnirestApiUtil.getResponse(urlForData);
		assertEquals(response.getStatus(), Constants.STATUS_CODE_OK, "Status code is NOT 200");
		assertEquals(UnirestApiUtil.getProducerNameFromApi(response),
				JsonDataPuller.dataPuller(Constants.TEST_DATA, "/producerName"), "Producer Name NOT matching.");
	}

	@Test(priority = 3)
	public void peopleCheck() {
		HttpResponse<JsonNode> response;
		String urlForData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.API_REQUESTS, "/allPeople")
				+ RandomSelection.randomPeopleSelection();
		response = UnirestApiUtil.getResponse(urlForData);
		assertEquals(response.getStatus(), Constants.STATUS_CODE_OK, "Status code is NOT 200");
		assertTrue(UnirestApiUtil.checkIfCharacterHasAtLeastOneFilm(response), "Producer Name NOT matching.");
	}
}
