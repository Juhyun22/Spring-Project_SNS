package com.sns.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/whyyy")
	public List<Post> posts() {
		
		List<Post> postList = postBO.getPostList();
		
		return postList;
	}
}
