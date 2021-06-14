package com.xworkz.enquiryAndCallManagement.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "enquiry_details")
  @NamedQueries({ 
	  @NamedQuery(name = "fetchEnquiryByEmail", query ="from EnquiryEntity e WHERE e.emailId =:email"),
	  @NamedQuery(name = "fetchEnquiryById", query ="from EnquiryEntity e WHERE e.enquiryId =:id"),
	  @NamedQuery(name = "fetchEnquiryByFullName", query ="from EnquiryEntity e WHERE e.fullName =:name"),
	  @NamedQuery(name = "fetchEnquiryByMobileNo", query ="from EnquiryEntity e WHERE e.mobileNo =:mobileNo")})

public class EnquiryEntity {
     
	@Id
	@GenericGenerator(name = "enquiry", strategy = "increment")
	@GeneratedValue(generator = "enquiry")
	@Column(name = "Enquiry_ID")
	private int enquiryId;
	
	@Column(name = "TIME_STAMP")
	private Timestamp dateTime;
		
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "ALTERNATE_MOBILE")
	private String alternateMobileNo;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "COURSE")
	private String course;
	
	@Column(name = "BATCH_TYPE")
	private String batchType;
	
	@Column(name = "SOURCE")
	private String source;
	
	@Column(name = "BRANCH")
	private String branch;
	
	@Column(name = "REFRENCE")
	private String refrenceName;
	
	@Column(name = "REFRENCE_MOBILE")
	private String refrenceMobileNo;
	
	@Column(name = "COUNSELOR_NAME")
	private String counselor;
	
	@Column(name = "COMMENTS")
	private String comments;
}
