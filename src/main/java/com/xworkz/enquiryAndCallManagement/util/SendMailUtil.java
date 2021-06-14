package com.xworkz.enquiryAndCallManagement.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendMailUtil {

	static Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

	static {
		String path = "./properties/httpConnection.properties";
		System.setProperty("java.util.logging.config.file", path);
	}

}