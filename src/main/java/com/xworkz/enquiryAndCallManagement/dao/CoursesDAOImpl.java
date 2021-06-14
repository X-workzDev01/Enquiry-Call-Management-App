package com.xworkz.enquiryAndCallManagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoursesDAOImpl implements CoursesDAO {

	private static final Logger logger = LoggerFactory.getLogger(EnquiryDAOImpl.class);

	@Autowired
	private SessionFactory factory;

	@Override
	public List getAllCourses() {
		logger.info("Invoking {}");
		List list = new ArrayList();
		Session session = null;
		try {
			logger.info("inside try block");
			session = factory.openSession();
			logger.info("executing namedQuery");
			Query query = session.getNamedQuery("getAllCourses");
			logger.info("");
			list = query.list();
			logger.info("featched all the coursses");
		} catch (Exception e) {
			logger.error(""+e.getMessage(),e);
		} finally {
			if (Objects.nonNull(session)) {
				logger.info("closing session");
				session.close();
			}
		}
		return list;
	}

}
