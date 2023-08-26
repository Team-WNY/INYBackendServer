package com.iny.opensoftware.application.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {
	
	private final MailService mailService;

	// 인증 번호를 저장할 Map (데이터베이스 대신 사용), DB로 변경 필요
    private Map<String, String> verificationCodes = new HashMap<>();

    // 인증 번호 생성
    public String generateVerificationCode(String email) {
        Random random = new Random();
        String verificationCode = String.format("%04d", random.nextInt(10000)); // 4자리 숫자로 생성
        this.verificationCodes.put(email, verificationCode);
        return verificationCode;
    }

    // 생성된 인증 번호를 이메일로 전송
    public void sendVerificationCode(String email, String verificationCode) {
        String emailContent = "인증 번호는 " + verificationCode + " 입니다.";
        this.mailService.sendEmail(email, "이메일 인증 번호", emailContent);
    }

    // 사용자가 입력한 인증 번호를 검증
    public boolean verifyVerificationCode(String email, String inputCode) {
        String storedCode = this.verificationCodes.get(email);
        return storedCode != null && storedCode.equals(inputCode);
    }
}