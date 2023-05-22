package qa.swapi.util;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestassuredApiUtil {

	public static Response getResponse(String urlForData) {
		Response response;
		response = RestAssured.get(urlForData);
		return response;
	}

	public static List<String> getMovieNameFromApi(Response response) {
		List<String> filmTitle = response.jsonPath().getList("results.title");
		return filmTitle;
	}

	public static String getProducerNameFromApi(Response response) {
		String filmProducer = null;
		filmProducer = response.jsonPath().get("producer").toString();
		return filmProducer;
	}

	public static boolean checkIfCharacterHasAtLeastOneFilm(Response response) {
		boolean atLeastOneFilm = false;
		String filmNames = response.jsonPath().get("films").toString();
		if (!filmNames.isEmpty()) {
			atLeastOneFilm = true;
		}
		return atLeastOneFilm;
	}
}
