package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // spring bean
public class FileManagerService {

	public final static String FILE_UPLOAD_PATH = "/Users/jyhyun/Desktop/6_spring_project/sns/sns_workspace/images/";
	
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 경로의 예: userLoginId_시간/이미지 이름.png
		// 파일명이 겹치지 않게 하기 위해 현재 시간을 경로에 붙혀준다. 
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
				
		// 디렉토리 만들기 
		File directory = new File(filePath);
		if (directory.mkdir() == false) {  // 파일 만들기에 실패했으면, 
			return null;  // null 리턴 
		}
		
		// upload file : byte단위로 업로드한다. 
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());  // getOriginalFilename()은 input에 올릴 파일 명이다.(한글 X) 
			Files.write(path, bytes);
			
			// 이미지 url을 리턴한다. (WebMvcConfing에서 매핑한 이미지 path) 
			// ex) http://localhost/images/image파일 이름/이미지 이름.png
			return "/images/" + directoryName + file.getOriginalFilename();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}




