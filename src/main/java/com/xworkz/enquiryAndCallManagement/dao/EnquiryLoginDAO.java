package com.xworkz.enquiryAndCallManagement.dao;

import com.xworkz.enquiryAndCallManagement.entity.LoginEntity;

public interface EnquiryLoginDAO {
	
	public LoginEntity fecthByUserName(String email);

	public boolean upadtePassByEmail(String newRandomPassword, String userName);


}
