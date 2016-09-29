package com.edvantis.workopolis.controller;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	
	static Logger log = Logger.getLogger(LoginController.class.getName());   

	@RequestMapping(method = RequestMethod.GET)
    public String indexPage() {
		log.info("index");
        Collection collectionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        log.debug("role--> " + collectionRole);
        
        Iterator itr = collectionRole.iterator();
        
        while(itr.hasNext()) {
        	Object element = itr.next();
            System.err.print("element "+element + " ");

            if(element.toString().equals("ROLE_ADMIN")) 
            	return "redirect:/admin";
            if(element.toString().equals("ROLE_EMPLOYER"))
            	return "redirect:/employer";
            if(element.toString().equals("ROLE_CANDIDATE")) 
            	return "redirect:/candidate";
            if(element.toString().equals("ROLE_RECRUITER")) 
            	return "redirect:/recruiter";
            if(element.toString().equals("ROLE_INTERVIEWER")) 
            	return "redirect:/interviewer";            
            if(element.toString().equals("ROLE_MANAGER")) 
            	return "redirect:/manager";
        }
        return "index";
    }
	
//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView home() {
//		ModelAndView model = new ModelAndView();
//		model.setViewName("index");
//		return model;
//	}
	
	@RequestMapping(value = "/contact_form", method = RequestMethod.GET)
	public String contactForm(Model model) {
		return "contact_form";
	}
	
	@RequestMapping(value = "/about_us", method = RequestMethod.GET)
	public ModelAndView aboutUs() {
		 ModelAndView model = new ModelAndView();
	     model.setViewName("about_us");
	     return model;
	}

}
