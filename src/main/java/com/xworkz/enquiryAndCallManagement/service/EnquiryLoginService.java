package com.xworkz.enquiryAndCallManagement.service;

import com.xworkz.enquiryAndCallManagement.dto.LoginDTO;

public interface EnquiryLoginService {

	public String validateLogin(LoginDTO loginDTO);
	
	public boolean validateAndResetPassword(LoginDTO loginDTO);

}
