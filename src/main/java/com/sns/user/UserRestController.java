package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	@RequestMapping("/is_duplicated_id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		Map<String, Object> result = new HashMap<>();
		boolean isDuplicate = userBO.existLoginId(loginId);
		result.put("result", isDuplicate);
		
		return result;
	}
	
	@PostMapping("/sign_up")
	public Map<String, Object> signUpForSubmit(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("profileImg") MultipartFile profileImg) throws Exception {
		
		// 비밀번호 암호화 
		String encryptPassword = EncryptUtils.Hashing(password, loginId);
		
		
		Map<String, Object> result = new HashMap<>();
				
		
		return result;
	}
	
	
	
}





