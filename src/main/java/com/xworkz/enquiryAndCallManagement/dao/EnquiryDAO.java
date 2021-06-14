package com.xworkz.enquiryAndCallManagement.dao;

import java.util.List;
import com.xworkz.enquiryAndCallManagement.entity.EnquiryEntity;

public interface EnquiryDAO {

	public boolean saveEnquiry(EnquiryEntity enquiryEntity);

	public List<EnquiryEntity> getLatestMonthEnquiries();

	public List<EnquiryEntity> getCustomDatesEnquiries(String fromDate, String toDate);

	public EnquiryEntity checkEnquiryByEmail(String emailId);

	public EnquiryEntity getEnquiryById(int enquiryId);

	public boolean updateEnquiryById(EnquiryEntity entity);

	public EnquiryEntity getEnquiryByFullName(String fullName);

	public EnquiryEntity getEnquiryByMobileNo(String mobileNo);
}
