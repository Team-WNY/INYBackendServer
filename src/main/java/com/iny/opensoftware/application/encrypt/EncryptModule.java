package com.iny.opensoftware.application.encrypt;

/**
 * Author : 박준모 
 * Encrypt version 0.1
 */

public interface EncryptModule {
	
	// 암호화를 진행하는 함수
	String encrypt(String plainText);
	
	// 복호화를 진행하는 함수
	String decrypt(String cipherText);
	
	// 난수 발급
	String randomString();
	
	// 해쉬 함수
	String hash(String plainText);
}
