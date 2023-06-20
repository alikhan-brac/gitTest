package qa.feign.util;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface FeignForPosts {
	
	@RequestLine("GET")
	Response getAll();
	
	@RequestLine("GET /{specificPostId}")
	Response getSpecificPost(@Param("specificPostId") String postId);
	
    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    Response createPost(@Param("userId") String postUserId,@Param("title") String postTitle,@Param("body") String postBody);
    
}
