package com.edvantis.workopolis.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.admin.AdminDAO;
import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.role.RoleDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.user.Administrator;
import com.edvantis.workopolis.model.user.Role;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.util.BcryptHashing;
import com.edvantis.workopolis.util.ListToJSONConverter;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	static Logger log = Logger.getLogger(AdminController.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private VacancyDAO vacancyDAO;
	
	@Autowired
	private CandidateDAO candidateDAO;
	
	@Autowired
	private EmployerDAO employerDAO;
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView adminPage() throws JSONException {
        log.info("admin Page...");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login); 
    	Administrator administrator  = adminDAO.getAdminByEmail(login);
    	
    	ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("administrator", administrator);
		modelAndView.addObject("candidates", ListToJSONConverter.candidateRegiDates(candidateDAO.getAllCandidates()));
		modelAndView.addObject("employers", ListToJSONConverter.employerRegiDates(employerDAO.getAllEmployers()));
		modelAndView.setViewName("admin/administrator_profile");
        return modelAndView;
    }
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/remove")
	public ModelAndView  deleteUser(@RequestParam("id")int UserId) {
		log.info("start deleteUser");
		log.debug("UserId " + UserId);
		userDAO.removeUser(UserId);
		
		List<User> users = adminDAO.getUsers();
		log.debug("users" + users);
		
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("users", users);
		modelAndView.setViewName("admin/all_profiles");
		
    	log.info("end deleteUser");
		return modelAndView;
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/isActive")
	public String  isActiveUser(@RequestParam("id")int idAccount) {
		
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		User account = (User)session.get(User.class, idAccount);
		boolean isActive = account.getIsActive();
		if(isActive == true) {
			account.setIsActive(false);
		}
		if(isActive == false) {
			account.setIsActive(true);
		}
		session.getTransaction().commit(); 
    	session.close();
    	
		return "redirect:/admin/all_profiles";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/create_profile")
	public ModelAndView createProfile() {
		log.info("create_profile redirect");
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("admin/create_profile");
		return modelAndView;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all_profiles")
	public ModelAndView allProfiles() {
		log.info("all_profiles redirect");
		ModelAndView modelAndView = new ModelAndView();	
		
		List<User> users = adminDAO.getUsers();
		log.debug("users" + users);
		
		modelAndView.addObject("users", users);
		modelAndView.setViewName("admin/all_profiles");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration_by_admin", method = RequestMethod.POST)
	public ModelAndView  registrationByAdmin(@ModelAttribute Employer user) {
		log.info("start registrationByAdmin");
		log.debug("user==> " + user);
		String roleName = user.getRole().getName();
		log.debug("roleName==> " + roleName);
		Role userRole = roleDAO.getRoleByName(roleName);
		log.debug("userRole==> " + userRole);
		user.setRole(userRole);
		user.setIsActive(true);
		log.debug("user==> " + user);
		UUID uuid = UUID.randomUUID();
		log.debug("uuid==> " + uuid);
		String password = user.getPassword();
		String hashedPassword = BcryptHashing.BcryptHash(password);
		user.setPassword(hashedPassword);
		Date date = new Date();
		user.setRegistrationDate(date);
		ModelAndView modelAndView = new ModelAndView();	
		try {
			userDAO.registerByAdmin(user, uuid);
			modelAndView.addObject("msg","User registered.");
			modelAndView.setViewName("redirect:/admin/create_profile");
		} catch (NonUniqueResultException e){
			modelAndView.addObject("msg","User with this login already exist");
			modelAndView.setViewName("redirect:/admin/create_profile");
		}
		log.info("end employer_registration");
		return modelAndView;
	}
	
	
}
