package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	/**
	 * 회원 가입 시, 아이디 중복 확인  
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		Map<String, Object> result = new HashMap<>();
		boolean isDuplicate = userBO.existLoginId(loginId);
		result.put("result", isDuplicate);
		
		return result;
	}
	
	/**
	 * 회원 가입 기능 - ajax 호출 
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUpForSubmit(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email) throws Exception{
		
		// 비밀번호 암호화 
		String encryptPassword = EncryptUtils.Hashing(password, loginId);
		
		// DB insert
		int row = userBO.insertUser(loginId, encryptPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		if (row < 1) {
			result.put("result", "error");
		}
		
		return result;
	}
	
	/**
	 * 로그인
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) throws Exception {
		
		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.Hashing(password, loginId);
		
		// 로그인 id, 암호화 password로 DB에서 select 
		User user = userBO.getUserByLoginIdPassword(loginId, encryptPassword);
		
		// 결과 json return 
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 로그인 성공 시 
		if (user != null) {
			// 로그인 - 세션에 저장(로그인 상태 유지) 
			HttpSession session = request.getSession();
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
		} else {
			result.put("result", "error");
			result.put("errorMessage", "존재하지 않는 사용자입니다. 관리자에게 문의해주세요.");
		}
		
		return result;
	}
}





