package qa.restapi.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Ordering;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import qa.restapi.model.Posts;
import qa.restapi.util.Constants;
import qa.restapi.util.JsonDataPuller;
import qa.restapi.util.LipsumGenerator;
import qa.restapi.util.FeignController;

public class RestApiTest {
	private static String urlForData = "";
	private static String postTitle = LipsumGenerator
			.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.testData, "/randomWordCount")));
	private static String postBody = LipsumGenerator
			.getRandomSentence(Integer.valueOf(JsonDataPuller.dataPuller(Constants.testData, "/randomWordCount")));
	private static String postUserId = JsonDataPuller.dataPuller(Constants.testData, "/userIDForfourthStep");

	@Test
	public void movieTitleCheck() {

		urlForData = JsonDataPuller.dataPuller(Constants.configData, "/apiurl")
				+ JsonDataPuller.dataPuller(Constants.configData, "/post");
		FeignController feignControl = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.target(FeignController.class, urlForData);

		// Step#1
		List<Posts> post1 = feignControl.getAll();
		List<Integer> idList = new ArrayList<>();
		for (Posts p : post1) {
			idList.add(Integer.valueOf(p.getId()));
		}
		assertTrue(Ordering.natural().isOrdered(idList), "Post id not Ordered");

		// Step#2
		Posts post2 = feignControl
				.getSpecificPost(JsonDataPuller.dataPuller(Constants.testData, "/postIDToGetSecondStep"));
		assertEquals(post2.getId(), JsonDataPuller.dataPuller(Constants.testData, "/postIDToCompare"),
				"id 99 mismatched");
		assertNotNull(post2.getTitle(), "title is empty");
		assertNotNull(post2.getBody(), "body is empty");

		// Step#3

		// --step4
		Posts postData = new Posts(postUserId, postTitle, postBody);
		feignControl.createPost(postData);
		FeignController feignControl4 = Feign.builder().encoder(new GsonEncoder()).decoder(new GsonDecoder())
				.target(FeignController.class, urlForData);
		Posts post4 = feignControl4.getSpecificPost("101");
		System.out.println(post4.toString());
		assertNotNull(post4.getId(), "id is absent");
		assertEquals(post4.getTitle(), postTitle, "title mismatched");
		assertEquals(post4.getBody(), postBody, "body mismatched");

		// Step#5

		// Step#6

	}

}
