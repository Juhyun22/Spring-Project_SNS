package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.ContentBO;
import com.sns.timeline.model.ContentView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private	ContentBO contentBO; 

	/**
	 * 타임라인 view
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeline_list_view")
	public String timelineListView(
			Model model, 
			HttpServletRequest request) {
		
		// 글쓴이의 정보를 가져오기 위해 세션에서 userId를 꺼낸다.
		HttpSession session = request.getSession();
		// 로그인 되어 있든 되어있지 않든 허용 - null허용하기 위해 Integer로 받음 
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) { // 로그인이 되지 않았을 때, 
			model.addAttribute("viewName", "user/sign_in");
			return "template/layout";
		}
		
		
		// 하나의 카드 => ContentView 객체 (View용 객체) 
		List<ContentView> contentList = contentBO.generateContentViewList(userId);
		
		model.addAttribute("contentList", contentList);
		model.addAttribute("viewName", "timeline/timeline_list");
		
		return "template/layout";
	}
	
}
