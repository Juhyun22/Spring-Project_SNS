package com.sns.common;

import java.security.MessageDigest;

public class EncryptUtils {
	
	public static String Hashing(String message, String Salt) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256"); // SHA-256 해시 함수 사용
		
		byte[] password = message.getBytes();
		
		// key-streaching
		for(int i = 0; i < 10000; i++) {
			String temp = message + Salt; // password + salt = 새로운 문자열 
			md.update(temp.getBytes());
			password = md.digest();
		}
		
		return Byte_to_String(password);
	}
	
	// 바이트 값을 16진수로 바꿔준다 
	private static String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for (byte a: temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
	
}
