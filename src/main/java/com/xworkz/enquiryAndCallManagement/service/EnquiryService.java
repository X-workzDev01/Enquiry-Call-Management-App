package com.xworkz.enquiryAndCallManagement.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.xworkz.enquiryAndCallManagement.dto.EnquiryDTO;
import com.xworkz.enquiryAndCallManagement.entity.EnquiryEntity;

public interface EnquiryService {

	public boolean validateAndSaveEnquiry(EnquiryDTO dto);

	public List<EnquiryDTO> getEnquiryListFromXls(MultipartFile file);

	public List<EnquiryDTO> downloadEnquiry() throws URISyntaxException, IOException;

	public List<EnquiryEntity> getLatestEnquiries();

	public List<EnquiryEntity> getCustomEnquiries(String fromDate, String toDate);

	public EnquiryDTO getEnquiryByEmail(String emailId);

	public boolean updateEnquiryById(EnquiryDTO enquiryDTO);

	public EnquiryDTO getEnquiryById(int enquiryId);

	public EnquiryDTO getEnquiryByFullName(String fullName);

	public EnquiryDTO getEnquiryByMobileNo(String mobileNo);

	public String validateExcelFile();

	

}
