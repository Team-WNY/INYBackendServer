package com.iny.opensoftware.application.mail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class MailSendTests {
	
	@Autowired
	private MailService mailService;
	
	@Test
	public void 메일발송() {
		String to = "tmp@mail.com";
        String subject = "Test Email";
        String text = "This is a test email sent from Spring Boot.";

        mailService.sendEmail(to, subject, text);
	}
}