package qa.swapi.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Constants {
	public static final int STATUS_CODE_OK = 200;
	public static final String CONFIG_DATA = ".\\src\\test\\resources\\config.json";
	public static final String API_REQUESTS = ".\\src\\test\\resources\\apirequest.json";
	public static final String MOVIE_TITLES = ".\\src\\test\\resources\\movieTitles.json";
	public static final String TEST_DATA = ".\\src\\test\\resources\\testData.json";
	public static final String SELECTED_MOVIE = ".\\src\\test\\resources\\selectedMovie.json";
	public static final int MAC_POSSIBLE_SELECTION = 83;
	public static final int MIN_POSSIBLE_SELECTION = 1;

	public static final List<String> getMovieNameFromTestData(String fileName) {
		ObjectMapper mapper = new ObjectMapper();
		List<String> filmTitle = null;
		try {
			filmTitle = mapper.readValue(new File(fileName), new TypeReference<ArrayList<String>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filmTitle;
	}
}
