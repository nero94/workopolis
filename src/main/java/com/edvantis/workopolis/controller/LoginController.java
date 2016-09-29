package com.edvantis.workopolis.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@Controller
public class LoginController {
	
	static Logger log = Logger.getLogger(LoginController.class.getName());   
        
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "login_error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
    	log.info("login");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		model.setViewName("login");
		return model;
	}
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	log.info("start logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        log.info("finish logout");
        return "login";
    }
    
    /*@RequestMapping(value = "/registrationPage", method = RequestMethod.POST)
	public ModelAndView  registrationPage(@ModelAttribute User accaunt) {
		ModelAndView modelAndView = new ModelAndView();		
		modelAndView.addObject("user", accaunt);
		modelAndView.setViewName("registrationUser");
		return modelAndView;
	}*/
    
    
}
