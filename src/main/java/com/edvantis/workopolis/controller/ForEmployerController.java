package com.edvantis.workopolis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.service.SendMessage;

@Controller
public class ForEmployerController {
	
	@RequestMapping(value = "/for-employer", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName("for_employer");
		return model;
	}
	
	@RequestMapping(value = "/send_contact_form", method = RequestMethod.POST)
	public ModelAndView sendContactForm(@RequestParam("name") String name,
			@RequestParam("surname") String surname,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("message") String message) {
		ModelAndView model = new ModelAndView();
		
		String message1 = "Message from: " + name + " " + surname + "\n" +
		"Message from email: " + email + "\n" + 
		"Contact phone: " + phone + "\n" + 
		"Message: " + message;
		
		SendMessage.send("slavek_k@ukr.net", "Contact form message", message1);
		model.addObject("msg","Message was sent");
		model.setViewName("contact_form");
		return model;
	}
	
	

}
