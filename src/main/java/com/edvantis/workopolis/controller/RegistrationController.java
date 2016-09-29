package com.edvantis.workopolis.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.service.SendMessage;
import com.edvantis.workopolis.util.BcryptHashing;

@Controller
public class RegistrationController {
	
	static Logger log = Logger.getLogger(RegistrationController.class.getName());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@Autowired
	private EmployerDAO employerDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CandidateDAO candidateDAO;
	
    @RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
    	log.info("register");
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;
	}
    
    @RequestMapping(value = "/cand_regi", method = RequestMethod.GET)
	public ModelAndView candRegistrationRedirect(HttpServletRequest request) {
    	log.info("candRegistration");
    	ModelAndView model = new ModelAndView();
    	String referrer = request.getHeader("referer");
    	System.out.println("referrer "+ referrer);
		model.setViewName("candRegistration");
		return model;
	}
    
    @RequestMapping(value = "/emp_regi", method = RequestMethod.GET)
	public ModelAndView empRegistrationRedirect(HttpServletRequest request) {
    	log.info("empRegistration");
    	ModelAndView model = new ModelAndView();
		model.setViewName("empRegistration");
		return model;
	}
    
	@RequestMapping(value = "/candidate_registration", method = RequestMethod.POST)
	public ModelAndView  candidateRegistration(@ModelAttribute Candidate candidate) {
		log.info("start candidate_registration");
		log.info("account==> " + candidate);
		UUID uuid = UUID.randomUUID();
		log.debug("uuid==> " + uuid);
		
		String password = candidate.getPassword();
		String hashedPassword = BcryptHashing.BcryptHash(password);
		candidate.setPassword(hashedPassword);
		Date date = new Date();
		candidate.setRegistrationDate(date);
		ModelAndView modelAndView = new ModelAndView();		
		try {
			userDAO.register(candidate, uuid);
			modelAndView.addObject("msg","User registered. For the confirmation of this account, please, check email and follow this link");
			modelAndView.setViewName("login");
			
			String to = candidate.getEmail();
			String message = "Dear "+candidate.getFirstName()+ ", "+"\n"+"For the confirmation of this account, please, follow this link"+"\n" + SendMessage.getFullURL(context)+ "confirm?uuid=" + uuid;
			SendMessage.send(to, "Registration", message);
		} catch (NonUniqueResultException e){
			modelAndView.addObject("msg","User with this login already exist");
			modelAndView.setViewName("candRegistration");
		}
		
		log.info("end candidate_registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/employer_registration", method = RequestMethod.POST)
	public ModelAndView  employerRegistration(@ModelAttribute Employer employer) {
		log.info("start employer_registration");
		log.info("account==> " + employer);
		log.info("Company--> " + employer.getCompany());
		UUID uuid = UUID.randomUUID();
		log.debug("uuid==> " + uuid);
		String password = employer.getPassword();
		String hashedPassword = BcryptHashing.BcryptHash(password);
		employer.setPassword(hashedPassword);
		Date date = new Date();
		employer.setRegistrationDate(date);
		ModelAndView modelAndView = new ModelAndView();		
		try {
			userDAO.register(employer, uuid);
			String to = employer.getEmail();
			String message = "Dear "+employer.getFirstName()+ ", "+"\n"+"For the confirmation of this account, please, follow this link"+"\n" + SendMessage.getFullURL(context)+ "confirm?uuid=" + uuid;
			SendMessage.send(to, "Registration", message);
			modelAndView.addObject("msg","User registered. For the confirmation of this account, please, check email and follow this link");
			modelAndView.setViewName("login");
		} catch (NonUniqueResultException e){
			modelAndView.addObject("msg","User with this login already exist");
			modelAndView.setViewName("empRegistration");
		}
		log.info("end employer_registration");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/confirm")
	public ModelAndView  confirm(@RequestParam("uuid")String uuid) {
		log.info("start confirm");
		log.debug("uuid " + uuid);
		userDAO.confirm(uuid);
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("msg", "You confirmed email");
		modelAndView.setViewName("login");
		log.info("end confirm");
		return modelAndView;		
	}
	
    @RequestMapping(value = "/check_email_exist", method = RequestMethod.POST)
    @ResponseBody
    String checkEmailExist(@RequestParam(value = "email") String email) {
    	User user = null;
    	user = userDAO.getUser(email);
    	System.out.println("user==> " + user);
    	String result="";
    	if(user != null) {
    		System.out.println("User with this email already exist");
    		result = "User with this email already exist" ;
    	}
    	System.out.println("result==> " + result);
		return result;
    }
    

	
	

}
