package com.xworkz.enquiryAndCallManagement.service;

import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.xworkz.enquiryAndCallManagement.dao.EnquiryLoginDAO;
import com.xworkz.enquiryAndCallManagement.dto.LoginDTO;
import com.xworkz.enquiryAndCallManagement.entity.LoginEntity;

import lombok.Setter;

@Setter
@Service
public class EnquiryLoginServiceImpl implements EnquiryLoginService {

	private Logger logger = LoggerFactory.getLogger(EnquiryLoginServiceImpl.class);

	@Autowired
	private EnquiryLoginDAO enuiryDAO;

	@Autowired
	private SpringMailService emailService;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Value("${passResetMailSubject}")
	private String passResetMailSubject;
	@Value("${mailFrom}")
	private String mailFrom;
	@Value("${ccmailID}")
	private String[] ccmailID;

	@Override
	public String validateLogin(LoginDTO loginDTO) {
		logger.debug("invoked validateAndLogin...");
		logger.debug("givenUser: {}", loginDTO.getUserName());

		try {
			LoginEntity entity = new LoginEntity();
			BeanUtils.copyProperties(loginDTO, entity);
			logger.debug("ready to fecth....");
			LoginEntity originalData = enuiryDAO.fecthByUserName(loginDTO.getUserName());

			if (Objects.nonNull(originalData)) {
				String originalPassword = originalData.getPassword();
				if (originalPassword.equals(loginDTO.getPassword())) {
					return "loginSuccess";
				} else {
					return "loginfaild";
				}
			} 
			else {
				logger.info("Data is not available for given userName");
				return "loginfaild";
			 }

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "loginfaild";
	}

	@Override
	public boolean validateAndResetPassword(LoginDTO loginDTO) {
		logger.debug("invoked validateAndResetPassword...");
		logger.debug("Enterd Email=" + loginDTO.getUserName());
		
		try {
			LoginDTO entity = new LoginDTO();
			BeanUtils.copyProperties(loginDTO, entity);
			logger.debug("ready to fecth....");
			LoginEntity originalData = enuiryDAO.fecthByUserName(entity.getUserName());

			if (Objects.isNull(originalData)) {
				logger.info("Password Reset Faild! , Email Does Not Exicit {}", entity.getUserName());
				return false;
			  } 
			else {
				String userName = originalData.getUserName();
				logger.debug("user Name {}", userName);

				String newRandomPassword = RandomStringUtils.randomAlphabetic(8);
				entity.setPassword(newRandomPassword);

				if (Objects.nonNull(newRandomPassword)) {
					boolean isUpdated = enuiryDAO.upadtePassByEmail(newRandomPassword, userName);
					if (Objects.nonNull(isUpdated)) {
						logger.debug("Password updated in data");
						MimeMessagePreparator messagePreparator = extracted(userName, newRandomPassword);

						if (Objects.nonNull(messagePreparator)) {
							boolean isMailSent = emailService.validateAndSendMailByMailId(messagePreparator);
							if (isMailSent) {
								logger.info("Password successfuly sent to MailID {}", userName);
								return true;
							} else {
								logger.info("Password Not able to sent to Mail {}", userName);
							}
						} else {
							logger.debug("Password Not able to sent to Mail {}", userName);
						}
					}
				}

			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	private MimeMessagePreparator extracted(String userName, String newRandomPassword) {
		Context context = new Context();
		context.setVariable("newRandomPassword", newRandomPassword);
		context.setVariable("userName", userName);

		String content = templateEngine.process("resetPasswordTemplate", context);
		MimeMessagePreparator messagePreparator = mimeMessage -> {

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(mailFrom);
			messageHelper.setCc(ccmailID);
			messageHelper.setTo(userName);
			messageHelper.setSubject(passResetMailSubject);
			messageHelper.setText(content, true);

		};
		return messagePreparator;
	}

}
