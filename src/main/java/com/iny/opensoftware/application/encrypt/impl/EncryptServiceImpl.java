package com.iny.opensoftware.application.encrypt.impl;

import com.iny.opensoftware.application.encrypt.EncryptModule;
import com.iny.opensoftware.application.encrypt.exception.CryptoException;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

// 이름 후보 AESMybatisService
// AESJpaService
@Service
@RequiredArgsConstructor
public class EncryptServiceImpl implements EncryptModule {
	
	private final int TEMP_PASSWORD_LENGTH = 10;
	private final String specName = "AES/CBC/PKCS5Padding";
	
	@Override
	public String encrypt(String plainText, SecretKey key, IvParameterSpec iv) throws CryptoException {
		Assert.hasText(plainText, "평문은 null이거나, 공백일 수 없습니다.");
		Cipher cipher;
		byte[] encrypted = null;
		
		try {
			cipher = Cipher.getInstance(specName);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
			
			encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
			throw new CryptoException("암호화 로직에서 실패하였습니다.");
		}
		
		
		if (encrypted == null){
			throw new CryptoException("암호화된 구문을 반환받지 못하였습니다.");
		}


		return new String(Base64.getEncoder().encode(encrypted));
	}


	@Override
	public String decrypt(String cipherText,SecretKey key, IvParameterSpec iv ) throws CryptoException {
		Assert.hasText(cipherText, "암호은 null이거나, 공백일 수 없습니다.");
		Cipher cipher;
		byte[] decrypted;

		try {
			cipher = Cipher.getInstance(specName);
			cipher.init(Cipher.DECRYPT_MODE, key, iv);

			decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		}catch (Exception e){
			e.printStackTrace();
			throw new CryptoException("복호화 로직에서 실패하였습니다.");
		}

		if (decrypted == null){
			throw new CryptoException("복호화된 구문을 반환받지 못하였습니다.");
		}

		return new String(decrypted, StandardCharsets.UTF_8);
	}

	@Override
	public String tmpRandomPwd() {
		return randomString();
	}

	@Override
	public boolean hashMatch(String plainText, String ciperText){
		Assert.hasText(plainText, "평문은 null이거나, 공백일 수 없습니다.");
		Assert.hasText(ciperText, "암호문은 null이거나, 공백일 수 없습니다.");
		return BCrypt.checkpw(plainText, ciperText);
	}

	@Override
	public String hashEncrypt(String plainText){
		Assert.hasText(plainText, "평문은 null이거나, 공백일 수 없습니다.");
		return BCrypt.hashpw(plainText,BCrypt.gensalt());
	}

	private String randomString() {
		SecureRandom secureRandom = new SecureRandom();

		byte[] randomBytes = new byte[TEMP_PASSWORD_LENGTH];
		secureRandom.nextBytes(randomBytes);

		String temporaryPassword = Base64.getEncoder().encodeToString(randomBytes);

		return temporaryPassword;
	}

}
