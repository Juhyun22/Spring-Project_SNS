package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	public List<Post> selectPostList();
	
	public Post selectPostByPostIdAndUserId (
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void insertPost (
			@Param("userId") int userId,
			@Param("imagePath") String imagePath,
			@Param("content") String content);
	
	public void deletePostByPostIdAndUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
