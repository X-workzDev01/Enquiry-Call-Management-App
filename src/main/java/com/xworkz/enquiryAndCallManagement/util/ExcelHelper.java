package com.xworkz.enquiryAndCallManagement.util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.xworkz.enquiryAndCallManagement.dto.EnquiryDTO;

@Component
public class ExcelHelper {

	static Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

	public ExcelHelper() {
		logger.info("{} is created", this.getClass().getSimpleName());
	}
	
	public ByteArrayInputStream readExcelFile(URI url){
		ByteArrayInputStream inputStream = null;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity(url, byte[].class);
		if (Objects.nonNull(responseEntity)) {
			byte[] result = responseEntity.getBody();

			logger.debug("Staring..........");
			inputStream = new ByteArrayInputStream(result);
		}
		return inputStream;
	}
	
	public List<String> getColumnHeading(InputStream inputStream) {
		List<String> columnHeadingList = new ArrayList<String>();
		try {
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet excelSheet = workbook.getSheetAt(0);
			logger.info("Excel file Is opened");
			if (Objects.nonNull(excelSheet)) {
				DataFormatter dataFormatter = new DataFormatter();
				Row row = excelSheet.getRow(0);
				int columns = row.getLastCellNum();
				for (int i = 0; i < columns; i++) {
					columnHeadingList.add(dataFormatter.formatCellValue(row.getCell(i,MissingCellPolicy.RETURN_BLANK_AS_NULL)));
				}
			} else {
				logger.error("ExcelSheet is Empty!");
			}
			
		}catch (Exception e) {
			logger.error("error is {} and message is {}", e, e.getMessage());
		}
		return columnHeadingList;
	}
	
	public List<String> getFieldsNameFromPropertiesFile(){
		List<String> FieldsName = new ArrayList<String>();
		try {
			Properties prop = new Properties();
			FileReader reader=new FileReader("/src/main/resources/prop/excelFileField.properties");
			//InputStream inputStream =this.getClass().getResourceAsStream("/excelFileField.properties");
			prop.load(reader);
			Set<Object> keys = prop.keySet();
			for(Object k:keys){
				FieldsName.add(prop.getProperty((String)k));
			}
		} catch (FileNotFoundException e) {
			logger.error("error is {} and message is {}", e, e.getMessage());
		} catch (IOException e) {
			logger.error("error is {} and message is {}", e, e.getMessage());
		}
		return FieldsName;
	}

	@SuppressWarnings("deprecation")
	public List<String> getContactListFromInputStream(InputStream inputStream) {
		List<String> mobileNumList = new ArrayList<String>();
		try {
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet excelSheet = workbook.getSheetAt(0);
			logger.info("Last Row Number Is: " + excelSheet.getLastRowNum());
			logger.info("Excel file Is opened");
			// logger.info("Size of Excel is {}", excelSheet.getLastRowNum());
			Row row;
			for (int i = 0; i <= excelSheet.getLastRowNum(); i++) {
				row = excelSheet.getRow(i);
				logger.debug("Data type is {}", row.getCell(0).getCellType());
				if (row.getCell(0).getCellType() == 0) {
					Long long1 = (long) row.getCell(0).getNumericCellValue();
					logger.info("Data: {} is read and stored.", long1);
					mobileNumList.add(String.valueOf(long1));
				}
			}
		} catch (Exception e) {
			logger.error("error is {} and message is {}", e, e.getMessage());
		}
		return mobileNumList;
	}

	public List<EnquiryDTO> getEnquiryListFromInputStream(InputStream inputStream) {
		List<EnquiryDTO> enquiryList = new ArrayList<EnquiryDTO>();
		try {
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet excelSheet = workbook.getSheetAt(0);
			logger.info("Last Row Number Is: " + excelSheet.getLastRowNum());
			logger.info("Excel file Is opened");
			Date today = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			String todayDate = dateFormat.format(today);
			if (Objects.nonNull(excelSheet)) {
				DataFormatter dataFormatter = new DataFormatter();
				for (Row row : excelSheet) {
					EnquiryDTO enquiryDTO = new EnquiryDTO();
					String date = dataFormatter.formatCellValue(
							row.getCell(EnquiryExcelFileColumn.TIMESTAMP, MissingCellPolicy.RETURN_BLANK_AS_NULL));
					Date eDate = null;
					String enquiryDate = null;
					try {
						eDate = dateTimeFormat.parse(date);
						enquiryDate=dateFormat.format(eDate);
					} catch (ParseException e) {
						logger.error(e.getMessage());
					}

					if (Objects.nonNull(enquiryDate)) { 
						if (enquiryDate.equals(todayDate)) {
							enquiryDTO.setFullName(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.NAME, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setMobileNo(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.MOBILE, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setAlternateMobileNo(dataFormatter.formatCellValue(row.getCell(
									EnquiryExcelFileColumn.ALTERNATE_MOBILE, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setEmailId(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.EMAIL_ID, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setCourse(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.COURSE, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setBatchType(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.BATCH, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setSource(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.SOURCE, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setRefrenceName(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.REFRENCE, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setRefrenceMobileNo(dataFormatter.formatCellValue(row.getCell(
									EnquiryExcelFileColumn.REFRENCE_MOBILE, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setBranch(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.BRANCH, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setComments(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.COMMENTS, MissingCellPolicy.RETURN_BLANK_AS_NULL)));
							enquiryDTO.setCounselor(dataFormatter.formatCellValue(
									row.getCell(EnquiryExcelFileColumn.COUNSELOR, MissingCellPolicy.RETURN_BLANK_AS_NULL)));

							if (Objects.nonNull(enquiryDTO.getFullName()) && Objects.nonNull(enquiryDTO.getEmailId())
									&& Objects.nonNull(enquiryDTO.getMobileNo())) {
								enquiryList.add(enquiryDTO);
							} else {
								logger.info("Fields Has empty values in Excel at Row:" + row.getRowNum());
							}
						}
					}
				}
				//excelSheet.forEach(row -> {});
				
			} else {
				logger.error("ExcelSheet is Empty!");
			}
		} catch (Exception e) {
			logger.error("error is {} and message is {}", e, e.getMessage());
		}
		return enquiryList;
	}
}
