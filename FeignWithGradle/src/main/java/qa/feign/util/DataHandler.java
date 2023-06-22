package qa.feign.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import qa.feign.model.Posts;
import qa.feign.model.User;

public class DataHandler {

	public static List<Posts> idListToCompare(Response response) {
		ObjectMapper objectMapper=new ObjectMapper();
		try (InputStream inputStream = response.body().asInputStream()) {
		    String responseBody = new String(inputStream.readAllBytes());
		    List<Posts> posts = objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Posts.class));
		    return posts;
		} catch (IOException e) {
		    throw new RuntimeException("Failed to deserialize response body", e);
		}
	}
	
	public static Posts postToCompare(Response response) {
	    Posts post=new Posts();
	    try (InputStream inputStream = response.body().asInputStream()) {
	    	String responseBody = new String(inputStream.readAllBytes());
	        post = new ObjectMapper().readValue(responseBody, Posts.class);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return post;
	}
	
	public static String postToCompareIfEmpty(Response response) {
		String responseBodyString = null;
		try (InputStream inputStream = response.body().asInputStream()) {
			byte[] responseBodyBytes = inputStream.readAllBytes();
			responseBodyString = new String(responseBodyBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBodyString;
	}
	
	public static List<User> userListToCompare(Response response) {
		ObjectMapper objectMapper=new ObjectMapper();
		try (InputStream inputStream = response.body().asInputStream()) {
		    String responseBody = new String(inputStream.readAllBytes());
		    List<User> users = objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
		    return users;
		} catch (IOException e) {
		    throw new RuntimeException("Failed to deserialize response body", e);
		}
	}	
	
	public static User userToCompare(Response response) {
		User user=new User();
	    try (InputStream inputStream = response.body().asInputStream()) {
	    	String responseBody = new String(inputStream.readAllBytes());
	        user = new ObjectMapper().readValue(responseBody, User.class);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return user;
	}
	
	public static final User predefinedUserDataToCompare(String jsonString) {
		User user = new User();
		try {
			user = new ObjectMapper().readValue(jsonString, User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
