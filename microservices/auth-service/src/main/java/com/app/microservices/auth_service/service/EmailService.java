package com.app.microservices.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


//This class is responsible only for sending email.

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public void sendOtpEmail(String email, String otp) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(email);
		message.setSubject("Password Reset OTP - Quantity Measurement App");
		message.setText("Hello, \n\n" + 
						"Your otp for password reset is: " + otp + " \n\n" +
						"This otp is only valid for 5 minutes.\n\n"
		);
		
		javaMailSender.send(message);
	}
}