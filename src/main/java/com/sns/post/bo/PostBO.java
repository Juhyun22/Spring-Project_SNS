package com.sns.post.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManager;

	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public Post getPostByPostIdAndUserId(int postId, int userId) {
		return postDAO.selectPostByPostIdAndUserId(postId, userId);
	}
	
	public void addPost(int userId, String userLoginId, MultipartFile file, String content) {
		String imagePath = null;
		if (file != null) { // 파일에 값이 있다면, 
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		// insert DAO
		postDAO.insertPost(userId, imagePath, content);
	}
	
	public void deletePostByPostIdAndUserId(int postId, int userId) {
		// postId로 select Post 
		Post post = getPostByPostIdAndUserId(postId, userId);
		
		if (post == null) {
			logger.error("[delete post] 삭제할 게시물이 없습니다. postId:{}", postId);
			return;
		}
		
		// 이미지가 있으면 이미지 삭제 
		if (post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		
		// 글 삭제 byPostIdUserId
		postDAO.deletePostByPostIdAndUserId(postId, userId);
		
		// 댓글들 삭제 byPostId
		commentBO.deleteCommentByPostId(postId);
		
		// 좋아요들 삭제 byPostId
		likeBO.deleteLikeByPostId(postId);
	}
}
