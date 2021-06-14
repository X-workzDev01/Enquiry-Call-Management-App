package com.xworkz.enquiryAndCallManagement.configuration;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import com.xworkz.enquiryAndCallManagement.util.EncryptionHelper;

@Configuration
public class AppConfig {
	
	@Autowired
	private EncryptionHelper encryptionHelper;
	@Value("${JavaMailSenderImpl.username}")
	private String username;
	@Value("${JavaMailSenderImpl.password}")
	private String password;
	
	@Bean
	public JavaMailSender getMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(encryptionHelper.decrypt(username));
		mailSender.setPassword(encryptionHelper.decrypt(password));
		
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");
		
		
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
}
