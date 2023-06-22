package qa.feign.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.hc.core5.http.HttpStatus;
import com.google.common.collect.Ordering;

import feign.Feign;
import feign.Response;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import qa.feign.model.Posts;
import qa.feign.model.User;
import qa.feign.util.Constants;
import qa.feign.util.DataHandler;
import qa.feign.util.FeignForPosts;
import qa.feign.util.JsonDataPuller;
import qa.feign.util.LipsumGenerator;

public class RestApiTest {

	private String postTitle = LipsumGenerator
			.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
	private String postBody = LipsumGenerator
			.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/randomWordCount")));
	private String postUserId = JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDForfourthStep");

	private String urlForPostData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
			+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/post");
	private FeignForPosts feignForPost = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
			.target(FeignForPosts.class, urlForPostData);

	private String urlForUserData = JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/apiurl")
			+ JsonDataPuller.dataPuller(Constants.CONFIG_DATA, "/user");
	private FeignForPosts feignControlForUser = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
			.target(FeignForPosts.class, urlForUserData);
	private User userFromApi = new User();

	@Test(priority = 1)
	public void checkAllPosts() {
		// Step#1
		Response response = feignForPost.getAll();
		assertTrue(response.status() == HttpStatus.SC_OK, "Status code is not 200");
		assertTrue(response.headers().get("Content-Type").toString().contains(Constants.CONTENT_TYPE.toString()),
				"Response body is not JSON");
		List<Integer> idList = DataHandler.idListToCompare(response).stream().map(p -> Integer.valueOf(p.getId()))
				.collect(Collectors.toList());
		assertTrue(Ordering.natural().isOrdered(idList), "Post id not Ordered");
	}

	@Test(priority = 2)
	public void checkPost99() {
		// Step#2
		Response response2 = feignForPost
				.getSpecificPost(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToGetSecondStep"));
		assertTrue(response2.status() == HttpStatus.SC_OK, "Status code is not 200");
		Posts post2 = DataHandler.postToCompare(response2);
		assertEquals(post2.getUserId(), JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIDToCompare"),
				"userId 10 mismatched");
		assertEquals(post2.getId(), JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToCompare"),
				"id 99 mismatched");
		assertNotNull(post2.getTitle(), "title is empty");
		assertNotNull(post2.getBody(), "body is empty");
	}

	@Test(priority = 3)
	public void checkPost150() {
		// Step#3
		Response response3 = feignForPost
				.getSpecificPost(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/postIDToGetThirdStep"));
		assertTrue(response3.status() == HttpStatus.SC_NOT_FOUND, "Status code is not 404");
		assertTrue(DataHandler.postToCompareIfEmpty(response3).contentEquals("{}"), "Content is not empty");
	}

	@Test(priority = 4)
	public void createPostRecord() {
		// Step#4
		Response response4 = feignForPost.createPost(postUserId, postTitle, postBody);
		assertTrue(response4.status() == HttpStatus.SC_CREATED, "Status code is not 201");
		Posts post4 = DataHandler.postToCompare(response4);
		assertNotNull(post4.getId(), "id is absent");
		assertEquals(post4.getTitle(), postTitle, "title mismatched");
		assertEquals(post4.getBody(), postBody, "body mismatched");
	}

	@Test(priority = 5)
	public void checkAllUsers() {
		// Step#5

		Response response5 = feignControlForUser.getAll();
		assertTrue(response5.status() == HttpStatus.SC_OK, "Status code is not 200");
		assertTrue(response5.headers().get("Content-Type").toString().contains(Constants.CONTENT_TYPE.toString()),
				"Response body is not JSON");
		List<User> userList = DataHandler.userListToCompare(response5);
		userFromApi = userList.stream().filter(
				p -> p.getId().equals(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToFetchDataFor5thStep")))
				.findAny().orElse(null);
		User userFromTestData = DataHandler
				.predefinedUserDataToCompare(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToCompare"));
		assertEquals(userFromApi, userFromTestData, "Respective user data mismatched between api and testData");

	}

	@Test(priority = 6)
	public void checkUser5() {
		// Step#6
		Response response6 = feignControlForUser
				.getSpecificPost(JsonDataPuller.dataPuller(Constants.TEST_DATA, "/userIdToFetchDataFor5thStep"));
		assertTrue(response6.status() == HttpStatus.SC_OK, "Status code is not 200");
		User specificUserFromApi = DataHandler.userToCompare(response6);
		assertEquals(userFromApi, specificUserFromApi, "Respective user data mismatched between api and testData");
	}
}