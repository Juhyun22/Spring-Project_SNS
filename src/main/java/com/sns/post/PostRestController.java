package com.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * test용 Controller
	 * @return
	 */
	@RequestMapping("/whyyy")
	public List<Post> posts() {
		
		List<Post> postList = postBO.getPostList();
		
		return postList;
	}
	
	public Map<String, Object> create(
			@RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam("content") String content,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 세션에서 글쓴이 정보를 가져온다. 
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		String userLoginId = (String)session.getAttribute("userLoginId");
		// 로그인이 되어 있지 않은 경우 post하는 box가 보이지 않게 timeline_list.jsp에 함.
		
		
		// userId, imagePath, content to DB
	}
}




