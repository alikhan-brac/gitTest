package qa.restapi.util;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import qa.restapi.model.Posts;

public interface FeignController {
	
	@RequestLine("GET")
	List<Posts> getAll();

	@RequestLine("GET /{specificPostId}")
	Posts getSpecificPost(@Param("specificPostId") String postId);
	
    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void createPost(Posts post);
    
}
