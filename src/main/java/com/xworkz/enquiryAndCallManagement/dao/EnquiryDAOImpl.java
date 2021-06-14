package com.xworkz.enquiryAndCallManagement.dao;

import java.util.List;
import java.util.Objects;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.enquiryAndCallManagement.entity.EnquiryEntity;

import lombok.Setter;

@Setter
@Repository
public class EnquiryDAOImpl implements EnquiryDAO {

	private static final Logger logger = LoggerFactory.getLogger(EnquiryDAOImpl.class);

	@Autowired
	private SessionFactory factory;

	public EnquiryDAOImpl() {
		logger.debug("created " + this.getClass().getSimpleName());
	}
	
	@Override
	public boolean saveEnquiry(EnquiryEntity entity) {
		logger.debug("invoked saveEnquiry()...");
		Session session = factory.openSession();
		try {
			if (Objects.nonNull(session)) {
				session.beginTransaction();
				logger.debug("transcation stared..");
				session.save(entity);
				session.getTransaction().commit();
				logger.debug("Transaction commited");
				logger.debug("Entity is not null , the entity is saved");
				return true;
			}
			else {
				logger.debug("not able to create Session");
				return false;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return false;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnquiryEntity> getLatestMonthEnquiries() {
		logger.debug("invoked getLatestMonthEnquiries()...");
		Session session = factory.openSession();
		Transaction transaction = null;
		List<EnquiryEntity> enquiryList = null;
		
		String sqlQuery = "SELECT * FROM enquiry_details WHERE TIME_STAMP BETWEEN NOW() - INTERVAL 30 DAY AND NOW();";
		
		try {
			if (Objects.nonNull(session)) {
				transaction = session.beginTransaction();
				logger.debug("transcation stared..");
				SQLQuery  query  = session.createSQLQuery(sqlQuery);
				query.addEntity(EnquiryEntity.class);
				enquiryList = query.list();
				transaction.commit();
				logger.debug("Transaction commited");
				logger.debug("Enquiries are fetched");
				return enquiryList;
			}
			else {
				logger.debug("not able to create Session");
				return enquiryList;
			}
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return enquiryList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnquiryEntity> getCustomDatesEnquiries(String fromDate, String toDate) {
		logger.debug("invoked getCustomDatesEnquiries()...");
		Session session = factory.openSession();
		Transaction transaction = null;
		List<EnquiryEntity> enquiryList = null;
		
		String sqlQuery = "SELECT * FROM enquiry_details WHERE TIME_STAMP BETWEEN '"+fromDate+"' AND '"+toDate+"'";
		
		try {
			if (Objects.nonNull(session)) {
				transaction = session.beginTransaction();
				logger.debug("transcation stared..");
				SQLQuery  query  = session.createSQLQuery(sqlQuery);
				query.addEntity(EnquiryEntity.class);
				enquiryList = query.list();
				transaction.commit();
				logger.debug("Transaction commited");
				logger.info("Enquiries are fetched");
				return enquiryList;
			}
			else {
				logger.debug("not able to create Session");
				return enquiryList;
			}
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return enquiryList;
		
	}

	@Override
	public EnquiryEntity checkEnquiryByEmail(String emailId) {
		logger.debug("invoked checkEnquiryByEmail()...");
		Session session = factory.openSession();
		Transaction transaction = null;
		EnquiryEntity enquiry = null;
		
		try {
			if (Objects.nonNull(session)) {
				transaction = session.beginTransaction();
				logger.debug("transcation stared..");
				Query query= session.getNamedQuery("fetchEnquiryByEmail");
				query.setParameter("email", emailId);
				enquiry = (EnquiryEntity) query.uniqueResult();
				transaction.commit();
				logger.debug("Transaction commited");
				logger.info("Enquiry checked");
				return enquiry;
			}
			else {
				logger.debug("not able to create Session");
				return enquiry;
			}
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return enquiry;
		
	}

	@Override
	public EnquiryEntity getEnquiryById(int enquiryId) {
		logger.debug("invoked getEnquiryById()...");
		Session session = factory.openSession();
		Transaction transaction = null;
		EnquiryEntity enquiryEntity = null;
		
		try {
			if (Objects.nonNull(session)) {
				transaction = session.beginTransaction();
				logger.debug("transcation stared..");
				Query query= session.getNamedQuery("fetchEnquiryById");
				query.setParameter("id", enquiryId);
				enquiryEntity = (EnquiryEntity) query.setMaxResults(1).uniqueResult();
				transaction.commit();
				logger.debug("Transaction commited");
				logger.info("Enquiry checked:{}",enquiryEntity);
				return enquiryEntity;
			}
			else {
				logger.debug("not able to create Session");
				return enquiryEntity;
			}
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return enquiryEntity;
		
	}

	@Override
	public boolean updateEnquiryById(EnquiryEntity entity) {
		logger.debug("invoked updateEnquiryById()...");
		Session session = factory.openSession();
		try {
			if (Objects.nonNull(session)) {
				session.beginTransaction();
				logger.debug("transcation stared..");
				session.update(entity);
				session.getTransaction().commit();
				logger.debug("Transaction commited");
			    logger.debug("Entity is not empty , the entity is Updated");
			    return true;
				}
			
			else {
				logger.debug("not able to create Session");
				logger.debug("Entity is not updated");
				return false;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return false;
	}

	@Override
	public EnquiryEntity getEnquiryByFullName(String fullName) {
		logger.debug("invoked getEnquiryByFullName()...");
		Session session = factory.openSession();
		Transaction transaction = null;
		EnquiryEntity enquiry = null;
		
		try {
			if (Objects.nonNull(session)) {
				transaction = session.beginTransaction();
				logger.debug("transcation stared..");
				Query query= session.getNamedQuery("fetchEnquiryByFullName");
				query.setParameter("name", fullName);
				enquiry = (EnquiryEntity) query.setMaxResults(1).uniqueResult();
				transaction.commit();
				logger.debug("Transaction commited");
				logger.info("Enquiry checked");
				return enquiry;
			}
			else {
				logger.debug("not able to create Session");
				return enquiry;
			}
		} catch (Exception e) {
			transaction.rollback();
			logger.error(e.getMessage(), e);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return enquiry;
		
	}

	@Override
	public EnquiryEntity getEnquiryByMobileNo(String mobileNo) {
		logger.debug("invoked getEnquiryByFullName()...");
		Session session = factory.openSession();
		Transaction transaction = null;
		EnquiryEntity enquiry = null;
		
		try {
			if (Objects.nonNull(session)) {
				transaction = session.beginTransaction();
				logger.debug("transcation stared..");
				Query query= session.getNamedQuery("fetchEnquiryByMobileNo");
				query.setParameter("mobileNo", mobileNo);
				enquiry = (EnquiryEntity) query.setMaxResults(1).uniqueResult();
				transaction.commit();
				logger.debug("Transaction commited");
				logger.info("Enquiry checked");
				return enquiry;
			}
			else {
				logger.debug("not able to create Session");
				return enquiry;
			}
		} catch (NonUniqueResultException nu) {
			transaction.rollback();
			logger.error(nu.getMessage(), nu);
		}

		finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.debug("session closed");
			}
		}
		return enquiry;
		
	}

}
