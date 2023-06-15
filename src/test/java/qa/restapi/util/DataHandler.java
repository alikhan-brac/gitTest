package qa.restapi.util;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import qa.restapi.model.User;

public class DataHandler {
	
	public static final User userToCompare(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		try {
			user = objectMapper.readValue(jsonString, User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
}
