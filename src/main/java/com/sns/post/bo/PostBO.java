package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManager;

	public List<Post> getPostList() {
		return postDAO.selectPostList();
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
		
		// 이미지가 있으면 이미지 삭제 
		
		// 글 삭제 byPostIdUserId
		
		// 댓글들 삭제 byPostId
		
		// 좋아요들 삭제 byPostId
	}
}
