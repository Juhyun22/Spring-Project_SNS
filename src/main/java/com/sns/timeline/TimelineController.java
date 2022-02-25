package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@Autowired
	private PostBO postBO; 

	/**
	 * 타임라인 view
	 * @param model
	 * @return
	 */
	@RequestMapping("/timeline_list_view")
	public String timelineListView(Model model) {
		
		// 하나의 카드 => ContentView 객체 (View용 객체) 
		// List<ContentView> contentList = new ArrayList<>();
		
		
		// post 내용 
		List<Post> postList = postBO.getPostList();
		
		model.addAttribute("viewName", "timeline/timeline_list");
		model.addAttribute("postList", postList);
		
		return "template/layout";
	}
	
}
