package com.sns.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	/**
	 * 회원 가입 view 
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/sign_up");
		return "template/layout";
	}
	
	/**
	 * 로그인 view 
	 * @param model
	 * @return
	 */
	@RequestMapping("/sign_in_view")
	public String SignInView(Model model) {
		model.addAttribute("viewName", "user/sign_in");
		return "template/layout";
	}
	
	/**
	 * 로그아웃 
	 * @param request
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		// 로그아웃 - 세션에 있는 키 값들을 지운다.
		HttpSession session = request.getSession();
		session.removeAttribute("userLoginId");
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		// 리다이렉트 - 로그인 화면 
		return "redirect:/user/sign_in_view";
	}
}
