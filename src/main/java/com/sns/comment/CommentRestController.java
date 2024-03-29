package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글 작성 
	 * @param postId
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping("/create")
	public Map<String, Object> writeComment(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시해주세요.");
			logger.error("[comment create] 로그인 세션이 없습니다. userId:{}, postId:{}", userId, postId);
			return result;
		}
		
		commentBO.createComment(userId, postId, content);
		
		return result;
	}
	
	/**
	 * 댓글 삭제
	 * @param commentId
	 * @param request
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("result", "error");
			result.put("errorMessage", "로그인을 다시 해주세요.");
			logger.error("[comment delete] 로그인 세션이 없습니다. userId:{}, commentId:{}", userId, commentId);
			return result;
		}
		
		commentBO.deleteCommentByUserIdAndCommentId(userId, commentId);
		
		return result;
	}
}
