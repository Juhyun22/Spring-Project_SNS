package com.sns.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManagerService;
import com.sns.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private PermissionInterceptor interceptor; 

	// 웹의 이미지 주소와 실제 파일 경로를 Mapping해주는 설정 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")  // **은 자식의 자식까지 // http://localhost/images/image파일 이름/이미지 이름.png
		.addResourceLocations("file:" + FileManagerService.FILE_UPLOAD_PATH);  // 실제 파일 저장 위치 
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")  // ** 아래 디렉토리까지 확인 
		.excludePathPatterns("/static/**", "/error", "/user/sign_out");  // 권한 검사를 안하는 path 처리(예외) 
	}
}
