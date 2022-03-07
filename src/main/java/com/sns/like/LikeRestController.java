package com.sns.like;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/like")
@RestController
public class LikeRestController {

	//  like/{postId}
	@RequestMapping("/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId) {
		
		return null;
	}
}
