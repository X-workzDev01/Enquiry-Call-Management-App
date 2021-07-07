package com.xworkz.enquiryAndCallManagement.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int enquiryId;
	private Timestamp dateTime;
	private String fullName;
	private String mobileNo;
	private String alternateMobileNo;
	private String emailId;
	private String course;
	private String batchType;
	private String source;
	private String refrenceName;
	private String refrenceMobileNo;
	private String branch;
	private String comments;
	private String counselor;

}
