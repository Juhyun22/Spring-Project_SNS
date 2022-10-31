package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // spring bean
public class FileManagerService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public final static String FILE_UPLOAD_PATH = "/Users/jyhyun/Desktop/6_spring_project/sns/sns_workspace/images/";

	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 경로의 예: userLoginId_시간/이미지 이름.png
		// 파일명이 겹치지 않게 하기 위해 현재 시간을 경로에 붙혀준다.
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;

		// 디렉토리 만들기
		File directory = new File(filePath);
		if (directory.mkdir() == false) { // 파일 만들기에 실패했으면,
			return null; // null 리턴
		}

		// upload file : byte단위로 업로드한다.
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename()); // getOriginalFilename()은 input에 올릴 파일 명이다.(한글
																			// X)
			Files.write(path, bytes);

			// 이미지 url을 리턴한다. (WebMvcConfing에서 매핑한 이미지 path)
			// ex) http://localhost/images/image파일 이름/이미지 이름.png
			return "/images/" + directoryName + file.getOriginalFilename();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteFile(String imagePath) {
		// imagePath의 /images/image파일 이름/이미지이름에서 /images/를 제거한 path 실제 저장 경로를 붙힌다.
		// /Users/.../memo/memo_workspace/images/ 에서  /images/이미지 파일이름/이미지이름.png 두개 겹쳐서 하나 삭제해야함
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		if (Files.exists(path) == true) { // 이미지 파일이 있으면 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[delete imageFile] 이미지 파일 삭제 오류 path:{}", path);
			}
		}

		// 디렉토리(폴더) 삭제
		path = path.getParent();
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[delete imageFile] 이미지 폴더 삭제 오류 path:{}", path);
			}
		}
	}
}
