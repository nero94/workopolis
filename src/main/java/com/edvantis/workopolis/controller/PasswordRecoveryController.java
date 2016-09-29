package com.edvantis.workopolis.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.service.SendMessage;
import com.edvantis.workopolis.util.BcryptHashing;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordRecoveryController {
	
	static Logger log = Logger.getLogger(PasswordRecoveryController.class.getName());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpServletRequest context;
	
	@RequestMapping(value = "/passwordRecoveryPage", method = RequestMethod.GET)
	public ModelAndView  passwordRecoveryPage() {
		log.info("passwordRecoveryPage");
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("passwordRecovery");
		return modelAndView;
	}
	
	@RequestMapping(value = "/passwordRecoveryMsg", method = RequestMethod.POST)
	public ModelAndView  passwordRecoveryMsg(@RequestParam String email, @RequestParam String password1, @RequestParam String password2) {
		log.info("start recovery");
		log.debug("email " + email);
		ModelAndView modelAndView = new ModelAndView();	
		UUID uuid = UUID.randomUUID();
		log.debug("uuid==> " + uuid);
		String password = BcryptHashing.BcryptHash(password1);
		String message = "To set a new password click on the link"+ "\n"
				+ SendMessage.getFullURL(context)+ "change_password_confirm?uuid=" + uuid + "&password=" + password;
		
		int id = 0;
		modelAndView.setViewName("login");
		try {
			id = userDAO.getUser(email).getId();
			SendMessage.send(email, "Changing password", message);
			modelAndView.addObject("msg","Instructions for the password recovering was sent on your email");
			userDAO.saveUUID(uuid.toString(), id);
		} catch (Exception e) {
			modelAndView.addObject("msg","User with this email not found");
		}
		log.info("end recovery");
		return modelAndView;
	}
	
	@RequestMapping(value = "/change_password_confirm", method = RequestMethod.GET)
	public ModelAndView  passwordRecovery(@RequestParam("uuid")String uuid, @RequestParam("password")String newPassword) {
		log.info("start passwordRecovery");
		log.debug("uuid " + uuid);
		log.debug("newPassword" + newPassword);
		userDAO.changePassword(uuid, newPassword);
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("login");
		modelAndView.addObject("msg","Password changed");
		log.info("end passwordRecovery");
		return modelAndView;		
	}
	
	

}
