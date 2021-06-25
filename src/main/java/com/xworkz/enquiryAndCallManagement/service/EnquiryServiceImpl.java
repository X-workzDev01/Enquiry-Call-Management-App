package com.xworkz.enquiryAndCallManagement.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.xworkz.enquiryAndCallManagement.dao.EnquiryDAO;
import com.xworkz.enquiryAndCallManagement.dto.EnquiryDTO;
import com.xworkz.enquiryAndCallManagement.entity.EnquiryEntity;
import com.xworkz.enquiryAndCallManagement.util.ExcelHelper;

import lombok.Setter;

@Setter
@Service
public class EnquiryServiceImpl implements EnquiryService {

	private static final Logger logger = LoggerFactory.getLogger(EnquiryServiceImpl.class);

	@Autowired
	private EnquiryDAO enquiryDAO;

	@Autowired
	private ExcelHelper excelHelper;

	@Value("${enquiryFilelink}")
	private String enquiryExcelFilelink;

	public EnquiryServiceImpl() {
		logger.debug("created " + this.getClass().getSimpleName());
	}

	@Override
	public boolean validateAndSaveEnquiry(EnquiryDTO dto) {
		if (dto != null) {
			getTimeStamp(dto);
			EnquiryEntity entity = new EnquiryEntity();
			EnquiryEntity entity2 = enquiryDAO.getEnquiryByFullName(dto.getFullName());
			if (!Objects.nonNull(entity2)) {
				entity2=enquiryDAO.getEnquiryByMobileNo(dto.getMobileNo());
				if(!Objects.nonNull(entity2)) {
					entity2=enquiryDAO.checkEnquiryByEmail(dto.getEmailId());
					if(!Objects.nonNull(entity2)) {
						BeanUtils.copyProperties(dto, entity);
						logger.debug("Data are valid..ready to save");
						boolean isSaved = enquiryDAO.saveEnquiry(entity);
						if (isSaved) {
							logger.debug("Enquiry Saved");
							return true;
						} else {
							logger.debug("Enquiry Not Saved");
							return false;
						}
					}
				}
			}
		} else {
			logger.debug("Data is null..not able to save");
		}
		return false;
	}

	private void getTimeStamp(EnquiryDTO dto) {
		Date date = new Date();
		Timestamp currentTimestamp = new Timestamp(date.getTime());
		logger.debug("Timestamp:{}", currentTimestamp);
		dto.setDateTime(currentTimestamp);
	}

	@Override
	public List<EnquiryDTO> getEnquiryListFromXls(MultipartFile file) {
		if (!file.isEmpty()) {
			List<EnquiryDTO> enquiryList = null;
			try {
				file.getBytes();
				String filename = file.getOriginalFilename();
				logger.debug("File name is {}", filename);

				if ((file.getInputStream() != null)) {
					enquiryList = excelHelper.getEnquiryListFromInputStream(file.getInputStream());
					logger.debug("retuning enquiryList");
				}
				return enquiryList;
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public List<EnquiryDTO> downloadEnquiry() throws URISyntaxException, IOException {
		List<EnquiryDTO> enquiryList = null;
		ByteArrayInputStream inputStream = null;
		URI url = new URI(enquiryExcelFilelink);
		inputStream = excelHelper.readExcelFile(url);
		if (Objects.nonNull(inputStream)) {
			enquiryList = excelHelper.getEnquiryListFromInputStream(inputStream);
			logger.debug("retuning enquiryList");
			return enquiryList;
		} else {
			logger.debug("Not able to read the Excel File at time!");
		}
		return null;
	}

	@Override
	public List<EnquiryEntity> getLatestEnquiries() {
		List<EnquiryEntity> enquiryList = null;
		try {
			enquiryList = enquiryDAO.getLatestMonthEnquiries();
			logger.debug("fetched enquiries");
			return enquiryList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public List<EnquiryEntity> getCustomEnquiries(String fromDate, String toDate) {
		List<EnquiryEntity> enquiryList = null;
		try {
			enquiryList = enquiryDAO.getCustomDatesEnquiries(fromDate, toDate);
			logger.debug("fetched enquiries");
			return enquiryList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public EnquiryDTO getEnquiryByEmail(String emailId) {
		EnquiryDTO enquiry = new EnquiryDTO();
		try {
			EnquiryEntity enquiryEntity = new EnquiryEntity();
			enquiryEntity = enquiryDAO.checkEnquiryByEmail(emailId);
			BeanUtils.copyProperties(enquiryEntity, enquiry);
			logger.debug("fetched enquiry");
			return enquiry;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return enquiry;
	}

	@Override
	public boolean updateEnquiryById(EnquiryDTO dto) {
		boolean result = false;
		try {
			getTimeStamp(dto);
			EnquiryEntity entity = new EnquiryEntity();
			BeanUtils.copyProperties(dto, entity);
			result = enquiryDAO.updateEnquiryById(entity);
			if (result) {
				logger.debug("Updated enquiry for:{}", entity.getFullName());
				return result;
			} else {
				logger.debug("not able to Updated enquiry");
				return result;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public EnquiryDTO getEnquiryById(int enquiryId) {
		EnquiryDTO enquiry = new EnquiryDTO();
		try {
			EnquiryEntity entity = enquiryDAO.getEnquiryById(enquiryId);
			if (Objects.nonNull(entity)) {
				BeanUtils.copyProperties(entity, enquiry);
				logger.debug("fetched enquiry");
				return enquiry;
			} else {
				logger.debug("Enquiry not available");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return enquiry;
	}

	@Override
	public EnquiryDTO getEnquiryByFullName(String fullName) {
		EnquiryDTO enquiry = new EnquiryDTO();
		try {
			EnquiryEntity entity = enquiryDAO.getEnquiryByFullName(fullName);
			if (Objects.nonNull(entity)) {
				BeanUtils.copyProperties(entity, enquiry);
				logger.debug("fetched enquiry");
				return enquiry;
			} else {
				logger.debug("Enquiry not available");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return enquiry;
	}

	@Override
	public EnquiryDTO getEnquiryByMobileNo(String mobileNo) {
		EnquiryDTO enquiry = new EnquiryDTO();
		try {
			EnquiryEntity entity = enquiryDAO.getEnquiryByMobileNo(mobileNo);
			if (Objects.nonNull(entity)) {
				BeanUtils.copyProperties(entity, enquiry);
				logger.debug("fetched enquiry");
				return enquiry;
			} else {
				logger.debug("Enquiry not available");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return enquiry;
	}

}
