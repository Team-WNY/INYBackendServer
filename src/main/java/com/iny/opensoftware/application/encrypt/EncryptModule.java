package com.iny.opensoftware.application.encrypt;

import com.iny.opensoftware.application.encrypt.exception.CryptoException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Author : 박준모 
 * Encrypt version 0.1
 */

public interface EncryptModule {
	
	// 암호화를 진행하는 함수
	String encrypt(String plainText, SecretKey key, IvParameterSpec iv) throws CryptoException;
	
	// 복호화를 진행하는 함수
	String decrypt(String cipherText, SecretKey key, IvParameterSpec iv) throws CryptoException;
	
	// 난수 발급
	String tmpRandomPwd();

	// 해쉬 함수
	String hashEncrypt(String plainText);

	// 해쉬 매칭
	boolean hashMatch(String plainText, String ciperText);
}
