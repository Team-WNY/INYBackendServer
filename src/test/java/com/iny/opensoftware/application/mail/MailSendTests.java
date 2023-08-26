package com.iny.opensoftware.application.mail;

import java.util.Scanner;

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
public class MailSendTests {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private EmailVerificationService verificationService;
	
	@Test
	public void 메일발송() {
		String to = "tmp@mail.com";
        String subject = "Test Email";
        String text = "This is a test email sent from Spring Boot.";

        this.mailService.sendEmail(to, subject, text);
	}
	
	@Test
	public void 인증메일발송() {
		String to = "tmp@mail.com";
		String code = this.verificationService.generateVerificationCode(to);
		
//		log.info("인증 번호 : " + this.verificationService.generateVerificationCode(to));
		this.verificationService.sendVerificationCode(to, code);
		
		Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a value: ");
        String input = scanner.nextLine();
        
        log.info("검증 : " + this.verificationService.verifyVerificationCode(to, input));
	}
}