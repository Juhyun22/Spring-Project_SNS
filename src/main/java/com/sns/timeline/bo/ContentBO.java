package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.model.CommentView;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.ContentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class ContentBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentBO commentBO;
//	
//	@Autowired
//	private LikeBO likeBO;
//	
	// 로그인 되지 않아도 타임라인은 볼 수 있으므로 userId Integer 
	// 객체 가공하는것 해보기 
	public List<ContentView> generateContentViewList(Integer userId) {
		
		List<ContentView> contentViewList = new ArrayList<>();
		
		// 글 List를 가져온다. => 반복문 돌림
		List<Post> postList = postBO.getPostList();
		for (Post post : postList) {
			ContentView content = new ContentView();
			// 글 정보 
			content.setPost(post);
			
			// 글쓴이 정보 
			User user = userBO.getUserById(post.getUserId());
			content.setUser(user);
			
			// 댓글 정보  
			// ${content.commentList.user.name}
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			content.setCommentList(commentList);
			
			// 좋아요 개수 세팅 
			
			// 로그인 된 사용자의 좋아요 여부 세팅 
			
			contentViewList.add(content);
		}
		
		return contentViewList;
	 }
}







