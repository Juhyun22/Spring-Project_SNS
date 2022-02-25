package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// 웹의 이미지 주소와 실제 파일 경로를 Mapping해주는 설정 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")  // **은 자식의 자식까지 // http://localhost/images/image파일 이름/이미지 이름.png
		.addResourceLocations("file:/Users/jyhyun/Desktop/6_spring_project/sns/sns_workspace/images/");  // 실제 파일 저장 위치 
	}
}
