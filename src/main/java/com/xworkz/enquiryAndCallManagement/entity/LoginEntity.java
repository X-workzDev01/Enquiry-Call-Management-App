package com.xworkz.enquiryAndCallManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "enquiry_Login")
@NamedQueries({ @NamedQuery(name = "checkUser", query = "from LoginEntity le where le.userName=:user"),
	            @NamedQuery(name = "updatePassByEmail", query = "update LoginEntity le set le.password=:pass where le.userName=:user")})
public class LoginEntity {

	private static Logger logger = LoggerFactory.getLogger(LoginEntity.class);

	@Id
	@GenericGenerator(name = "user", strategy = "increment")
	@GeneratedValue(generator = "user")
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USER_PASSWORD")
	private String password;

	public LoginEntity() {
		logger.info("created " + this.getClass().getSimpleName());
	}
}
