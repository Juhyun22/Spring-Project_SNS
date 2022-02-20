package com.sns.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {

	public List<Post> selectPostList();
}
