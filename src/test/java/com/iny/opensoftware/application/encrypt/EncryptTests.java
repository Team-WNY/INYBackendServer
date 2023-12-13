package com.iny.opensoftware.application.encrypt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
@Slf4j
public class EncryptTests {
	
	@Autowired
	private EncryptModule encryptService;

	@Test
	public void 암복호화_테스트() throws Exception {
		SecretKey key = AESCryptoUtil.getKey();
		IvParameterSpec iv = AESCryptoUtil.getIv();

		String plainText = "안녕하세요";
		String ciperText = this.encryptService.encrypt(plainText, key, iv);
		log.info("암호화_테스트!!! : " + ciperText);
		log.info("복호화_테스트!!! : " + this.encryptService.decrypt(ciperText, key, iv));
	}

	@Test
	public void 난수_발급() throws Exception {
		log.info("난수_발급!!! : " + this.encryptService.tmpRandomPwd());
	}

	@Test
	public void 해쉬_함수() throws Exception {
		String plainText = "비밀번호123!";
		log.info("해쉬_함수!!! : " + this.encryptService.hashEncrypt(plainText));
	}

	@Test
	public void 해쉬_매칭() throws Exception {
		log.info("난수_발급!!! : " + this.encryptService.hashMatch("비밀번호123!", "$2a$10$fzOftQk6M19GMqD/mW5PveifFAcvxCOM.IcyNGlFet9CfHzlDm6m."));
	}
}
