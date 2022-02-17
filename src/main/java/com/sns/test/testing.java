package com.sns.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testing {
	
	@RequestMapping("testing")
	@ResponseBody
	public String test() {
		return "Helloooo";
	}
}
