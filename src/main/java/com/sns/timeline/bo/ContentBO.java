package com.sns.timeline.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.post.bo.PostBO;
import com.sns.timeline.model.ContentView;

@Service
public class ContentBO {
	
	@Autowired
	private PostBO postBO;

//	@Autowired
//	private CommentBO commentBO;
//	
//	@Autowired
//	private LikeBO likeBO;
//	
	// 로그인 되지 않아도 타임라인은 볼 수 있으므로 userId Integer 
	// 객체 가공하는것 해보기 
	// public List<ContentView> generateContentViewList(Integer userId) {
		
		// 글 List를 가져온다. => 반복문 돌림
		
		
	// }
}







