package com.iny.opensoftware.application.encrypt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
@Slf4j
public class EncryptTests {
	
	@Autowired
	private EncryptModule encryptService;
	
	@Test
	public void 암호화_테스트() {
		String tmp = "안녕하세요";
		
		log.info(encryptService.encrypt(tmp));
	}
}
