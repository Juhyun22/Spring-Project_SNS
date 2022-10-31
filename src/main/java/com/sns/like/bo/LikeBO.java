package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	public void like(int postId, int userId) {
		// 세팅된 좋아요가 있는지 없는지 확인. (로그인이 되어 있는지)
		boolean existLike = existLike(postId, userId);
		
		if (existLike == true) {  // 좋아요가 이미 눌러져 있었으면, 
			likeDAO.deleteLikeByPostIdAndUserId(postId, userId);  // 좋아요 취소
		} else {  // 좋아요가 안눌러져 있었으면
			likeDAO.insertLike(postId, userId);  // 좋아요 추가
		}
	}
	
	public boolean existLike(int postId, Integer userId) {
		// 비로그인된 상태면 세팅된 좋아요는 없다. 
		if (userId == null) {
			return false;
		}
		
		// 해당하는 게시글에 사용자가 좋아요를 눌렀는지 안눌렀는지 확인 
		int count = likeDAO.selectLikeCountByPostIdOrUserId(postId, userId);
		
		return count > 0 ? true : false;
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostId(postId);
	}
	
	public void deleteLikeByPostId(int postId) {
		likeDAO.deleteLikeByPostId(postId);
	}
}
