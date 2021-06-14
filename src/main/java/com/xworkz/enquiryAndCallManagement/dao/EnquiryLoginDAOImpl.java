package com.xworkz.enquiryAndCallManagement.dao;

import java.util.Objects;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.enquiryAndCallManagement.entity.LoginEntity;

import lombok.Setter;

@Setter
@Repository
public class EnquiryLoginDAOImpl implements EnquiryLoginDAO {

	private Logger logger = LoggerFactory.getLogger(EnquiryLoginDAOImpl.class);

	@Autowired
	private SessionFactory factory;

	public EnquiryLoginDAOImpl() {
		logger.debug("created " + this.getClass().getSimpleName());
	}
	
	@Override
	public LoginEntity fecthByUserName(String email) {
		Session session = factory.openSession();

		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("checkUser");
			query.setParameter("user", email);
			Object result = query.uniqueResult();
			
			if (Objects.nonNull(result)) {
				logger.debug("entity found for =" + email);
				LoginEntity entity = (LoginEntity) result;
				session.getTransaction().commit();
				return entity;
			}

			else {
				logger.debug("entity not found for =" + email);
				logger.debug("emailID is wrong!");
				return null;
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
		return null;
	}

	@Override
	public boolean upadtePassByEmail(String newRandomPassword, String userName) {
		logger.debug("invoked upadtePassByEmail()");
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			Query query = session.getNamedQuery("updatePassByEmail");
			query.setParameter("user", userName);
			query.setParameter("pass", newRandomPassword);

			Object result = query.executeUpdate();
			if (Objects.nonNull(result)) {
			logger.debug("entity available=" + userName);
			session.getTransaction().commit();
			logger.debug("Transaction commited.");
			logger.info("For UserName=" + userName +" Upadted Password Succesfuly");
            return true;
			} 
			else {
				logger.info("entity not available=" + userName);
				return false;
			}

		}

		catch (Exception e) {
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
}
