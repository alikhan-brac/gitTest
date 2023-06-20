package qa.feign.util;

import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface FeignForUsers {
	
	@RequestLine("GET")
	Response getAll();
	
	@RequestLine("GET /{specificUserId}")
	Response getSpecificUser(@Param("specificUserId") String userId);

}
