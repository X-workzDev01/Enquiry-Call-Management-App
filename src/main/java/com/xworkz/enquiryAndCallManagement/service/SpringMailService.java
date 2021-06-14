package com.xworkz.enquiryAndCallManagement.service;

import org.springframework.mail.javamail.MimeMessagePreparator;

public interface SpringMailService {

	public boolean validateAndSendMailByMailId(MimeMessagePreparator messagePreparator);
}
