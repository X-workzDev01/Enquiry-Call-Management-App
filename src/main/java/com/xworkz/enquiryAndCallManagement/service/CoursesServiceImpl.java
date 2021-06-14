package com.xworkz.enquiryAndCallManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.enquiryAndCallManagement.dao.CoursesDAO;

@Service
public class CoursesServiceImpl implements CoursesService{

	private static final Logger logger = LoggerFactory.getLogger(CoursesServiceImpl.class);
	
	@Autowired
	private CoursesDAO coursesDAO;
	
	@Override
	public List getAllCourses() {
		List list = null;
		try {
			list = coursesDAO.getAllCourses();
			if(Objects.nonNull(list)) {
				logger.debug("get all the courses");
			}else {
				logger.debug("Courses not available");
				list = new ArrayList();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		
		return list;
	}
}
