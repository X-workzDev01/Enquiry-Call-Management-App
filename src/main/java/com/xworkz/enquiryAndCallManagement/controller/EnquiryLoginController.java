package com.xworkz.enquiryAndCallManagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.xworkz.enquiryAndCallManagement.dto.LoginDTO;
import com.xworkz.enquiryAndCallManagement.service.EnquiryLoginService;

@RestController
@RequestMapping("/")
public class EnquiryLoginController {

	private Logger logger = LoggerFactory.getLogger(EnquiryLoginController.class);

	@Autowired
	private EnquiryLoginService enquiryLoginService;

	public EnquiryLoginController() {
		logger.debug("{} Is Created...........", this.getClass().getSimpleName());
	}

	@RequestMapping("/enquiryLogin.do")
	public ModelAndView enquiryLogin(LoginDTO loginDTO, Model model) {
		logger.debug("invoking on login...");
		logger.debug("Login MailId :{}", loginDTO.getUserName());

		try {
			String datafmDB = this.enquiryLoginService.validateLogin(loginDTO);
			logger.debug("Data From DB:" + datafmDB);

			if (datafmDB.equals("loginSuccess")) {
				logger.info("login Successful");
				model.addAttribute("LoginMsg", "Login Successful " + loginDTO.getUserName());
				return new ModelAndView("Home");
			} else {
				logger.info("Email or Password is wrong");
				model.addAttribute("LoginMsg", "Email or Password is Invalid");
				return new ModelAndView("EnquiryLogin");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		model.addAttribute("LoginMsg", "Email or Password is Incorrect");
		return new ModelAndView("EnquiryLogin");
	}

	@RequestMapping(value = "passReset.do", method = RequestMethod.POST)
	public ModelAndView enquiryResetPass(@ModelAttribute LoginDTO loginDTO) {
		logger.debug("invoked onReset()...");

		ModelAndView modelAndView = new ModelAndView("EnquiryPasswordReset");
		try {
			boolean validation = enquiryLoginService.validateAndResetPassword(loginDTO);
			if (validation) {
				logger.debug("Password Reset Successfull!");
				return modelAndView.addObject("resetedpassword",
						"Password Reset Successfull! ,Password Sent to your MailId");

			} else {
				logger.debug("Password Reset Failed!");
				return modelAndView.addObject("resetfaildbyemail",
						"Password Reset Failed!, Email doesn't Exicit. ");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return modelAndView;
	}

}
