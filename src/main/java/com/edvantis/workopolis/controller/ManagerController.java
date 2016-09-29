package com.edvantis.workopolis.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.recruiter.RecruiterDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.manager.ManagerDAO;
import com.edvantis.workopolis.model.user.Manager;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.model.vacancy.VacancyState;
import com.edvantis.workopolis.service.SendMessage;
import com.edvantis.workopolis.util.BcryptHashing;
import com.edvantis.workopolis.util.ListToJSONConverter;

@Controller
@SessionAttributes(value = {"manager"})
@RequestMapping(value = "/manager")
public class ManagerController {
	

	@Autowired
	private HttpServletRequest context;
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@Autowired
	private ManagerDAO managerDAO;
	
	@Autowired
	private VacancyDAO vacancyDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RecruiterDAO recruiterDAO;
	
	static Logger log = Logger.getLogger(ManagerController.class.getName());
	
	@Autowired
	private EmployerDAO employerDAO;

	Manager manager;
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView managerProfile(ModelMap model) throws JSONException {
        log.info("ROLE_MANAGER"); 
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        
        model.addAttribute("name", login);
		ModelAndView modelAndView = new ModelAndView();	
		if(manager==null)	
			manager = managerDAO.getManagerByEmail(login);
		log.debug("manager " + manager);
		modelAndView.addObject("manager", manager);
		modelAndView.addObject("vacancies", ListToJSONConverter.getVacanciesForStatistics(vacancyDAO.getAllVacancies()));
		modelAndView.setViewName("manager/profile");
        return modelAndView;
    }
	//////////////////////////////////////////////////////////////////////////////////////
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/new_vacancies", method = RequestMethod.GET)
	public String listOfNewVacancies(Model model) {
		model.addAttribute("vacancies", vacancyDAO.getNotApprovedVacancies());
		return "manager/vacancies";
	}
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/closed_vacancies", method = RequestMethod.GET)
	public String listOfClosedVacancies(Model model) {
		model.addAttribute("vacancies", vacancyDAO.getClosedVacancies());
		return "manager/vacancies";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/approveVacancy", method = RequestMethod.GET)
    public String approveVacancy(Model model, @RequestParam("id")int vacancyId, String keyword, Direction direction, String companyName, String address, Integer offset, Integer maxResults) {
		log.info("start assignVacancy");
		log.debug("vacancyId " + vacancyId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if(manager==null) 
    		manager = managerDAO.getManagerByEmail(email);
		model.addAttribute("manager", manager);
		
        Vacancy vacancy = vacancyDAO.getVacancyById(vacancyId);
        
        vacancy.setState(VacancyState.POSTED);
        vacancyDAO.updateVacancy(vacancy);
       	
		List<Vacancy> notApprovedVacancies  = vacancyDAO.getNotApprovedVacancies();
		log.debug("notAssignVacancies " + notApprovedVacancies);
		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("manager/vacancies");
        return "redirect:/manager/new_vacancies";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
    public String getEditManagerProfilePage (Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if(manager==null) 
    		manager = managerDAO.getManagerByEmail(email);
		model.addAttribute("manager", manager);
        return "manager/edit_profile";
    }
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/edit_personal_info", method = RequestMethod.POST)
    public String editUserInfo (
    		Model model,
    		@RequestParam(value = "firstName", required = false) String firstName,
    		@RequestParam(value = "middleName", required = false) String middleName,
    		@RequestParam(value = "lastName", required = false) String lastName,
    		@RequestParam(value = "skype", required = false) String skype) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
    	manager = managerDAO.getManagerByEmail(email); 
    	manager.setFirstName(firstName);
    	manager.setMiddleName(middleName);
    	manager.setLastName(lastName);
    	manager.setSkypeUrl(skype);
    	managerDAO.update(manager);
		model.addAttribute("manager", manager);
        return "redirect:edit_profile";
    }
	
	 	@PreAuthorize("hasRole('ROLE_MANAGER')")
	    @RequestMapping(value = "/check_email_exist_btn", method = RequestMethod.POST)
	 	@ResponseBody
	    public String changeEmailRequest(@RequestParam(value = "email") String newEmail) {
	    	log.info("start changeEmailRequest");
	    	log.debug("newEmail " + newEmail);
	    	User user = null;
	    	user = userDAO.getUser(newEmail);
	    	String result="";
	    	if(user != null) {
	    		result = "<div style=\"color:red\">User with this email already exist</div>" ;
	    	} else {
	    		UUID uuid = UUID.randomUUID();
	    		log.debug("uuid==> " + uuid);
	    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        String oldEmail = auth.getName();
		    	log.debug("oldEmail" + oldEmail);
				String message = "For email change, please, follow this link" + "\n"
						+ SendMessage.getFullURL(context)+ "manager/change_email_confirm?uuid=" + uuid + "&new_email=" + newEmail;
		    	
		    	//send mail to confirm new email
		    	SendMessage.send(newEmail, "Changing email", message);
		    	//save cod to db
		    	int interviewerId = userDAO.getUser(oldEmail).getId();
		    	userDAO.saveUUID(uuid.toString(), interviewerId);
	
				result = "<div style=\"color:green\">To confirm the email, click on the link sent to new email</div>" ;
				log.info("end changeEmailRequest");
	    	}
	        return result;
	    }
	    
	    @PreAuthorize("hasRole('ROLE_MANAGER')")
	    @RequestMapping(value = "/change_email_confirm")
	    public ModelAndView changeEmailConfirm(@RequestParam("uuid")String uuid, @RequestParam("new_email") String newEmail) {
	    	log.info("start changeEmailConfirm");
	    	log.debug("uuid " + uuid);
	    	
	    	log.debug("newEmail " + newEmail);
	    	userDAO.confirm(uuid, newEmail);
	    	
	    	ModelAndView modelAndView = new ModelAndView();	
	    	if(manager==null)	
	    		manager = (Manager) userDAO.getUser(newEmail);
	    	manager.setEmail(newEmail);
			modelAndView.addObject("manager", manager);
			modelAndView.setViewName("manager/profile");
			
	    	log.info("end changeEmailConfirm");
			return modelAndView;
	    }
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
	    @PreAuthorize("hasRole('ROLE_MANAGER')")
	    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
	    public ModelAndView changePassword(@RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2) {
	    	log.info("start change_password");
	    	ModelAndView modelAndView = new ModelAndView();	
	    	log.debug("newPassword1 " + newPassword1);
	    	log.debug("newPassword2 " + newPassword2);
	    	if(! newPassword1.equals(newPassword2)) {
	    		modelAndView.addObject("msg", "You entered not same password");
	    	} else {
	    		String hashedPassword = BcryptHashing.BcryptHash(newPassword1);
	    		manager.setPassword(hashedPassword);
	    		userDAO.update(manager);
	    		modelAndView.addObject("msg", "Password successfully changed");
	    	}
			modelAndView.addObject("manager", manager);
			modelAndView.setViewName("manager/edit_profile");
			
	    	log.info("end change_password");
			return modelAndView;
	    }
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
    @RequestMapping(value = "/recruiters_information", method = RequestMethod.GET)
    public ModelAndView recruitersInformation(ModelMap model) {
        log.info("ROLE_MANAGER");
		ModelAndView modelAndView = new ModelAndView();	
		List<Recruiter> recruiters = recruiterDAO.getRecruiters();
		int assignedVacanciesSize; 
		List<Integer> cp = new ArrayList<Integer>();
		List<Integer> cc = new ArrayList<Integer>();
		for(int i=0; i<recruiters.size(); i++) {
			int countProcessing = 0, countClosed = 0;
			Set<Vacancy> assignedVacancies = recruiters.get(i).getAssignedVacancies();
			
			assignedVacanciesSize = recruiters.get(i).getAssignedVacancies().size();
			System.out.println("==> " + assignedVacanciesSize);
			System.out.println("==> " + recruiters.get(i).getAssignedVacancies());
			
			for (Vacancy v : assignedVacancies) {
			    System.out.println(v.getState());
			    if(v.getState().equals(VacancyState.PROCESSING))
			    	countProcessing++;
			    if(v.getState().equals(VacancyState.CLOSED))
			    	countClosed++;
			}
			cp.add(countProcessing);
			cc.add(countClosed);
		}
		modelAndView.addObject("countProcessing", cp);
		modelAndView.addObject("countClosed", cc);
		System.out.println("countProcessing==> " + cp);
		System.out.println("countClosed==> " + cc);
		/*VacancyState state = assignedVacancies.iterator().next().getState();
		System.out.println("state==> " + state);*/
		
		modelAndView.addObject("recruiters", recruiters);
		modelAndView.setViewName("manager/recruiters_information");
        return modelAndView;
    }
	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/employer_list", method = RequestMethod.GET)
	public String showAllEmployers(
    		Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if(manager==null) 
    		manager = managerDAO.getManagerByEmail(email);
		model.addAttribute("manager", manager);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manager/employer_list");
			List<Employer> allEmployers = employerDAO.getAllEmployers();
			model.addAttribute("allEmployers",allEmployers);
		
		return "manager/employer_list";
	}

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/employer_profile", method = RequestMethod.GET)
	public ModelAndView showEmployerProfile(@RequestParam("id") int id) {
	
		log.debug("idEmployer " + id);
		ModelAndView model = new ModelAndView();

		Employer employer = employerDAO.getEmployerById(id);
		
		model.addObject("employer", employer);

		model.setViewName("employer/employer_profile");
		return model;
	}
	
	
}
