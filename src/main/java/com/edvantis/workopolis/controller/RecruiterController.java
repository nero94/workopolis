package com.edvantis.workopolis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.company.CompanyDAO;
import com.edvantis.workopolis.dao.interview.InterviewDAO;
import com.edvantis.workopolis.dao.location.AddressDAO;
import com.edvantis.workopolis.dao.location.CountryDAO;
import com.edvantis.workopolis.dao.recruiter.RecruiterDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.model.vacancy.VacancyState;
import com.edvantis.workopolis.service.SendMessage;
import com.edvantis.workopolis.util.BcryptHashing;
import com.edvantis.workopolis.util.ListToJSONConverter;
import com.edvantis.workopolis.util.MatchCandidatesToVacancy;

@Controller
@RequestMapping(value = "/recruiter")
public class RecruiterController {
	
	static Logger log = Logger.getLogger(RecruiterController.class.getName());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private VacancyDAO vacancyDAO;
	
	@Autowired
	private RecruiterDAO recruiterDAO;
	
	@Autowired
	private InterviewDAO interviewDAO;
	
	@Autowired
	private CandidateDAO candidateDAO;
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	//Recruiter recruiter;
	
	public MatchCandidatesToVacancy mather = new MatchCandidatesToVacancy();
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView recruiterProfile(ModelMap model) {
		log.info("ROLE_RECRUITER");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        Recruiter recruiter = recruiterDAO.getRecruiter(login);
        log.debug("recruiter " + recruiter);
        ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("recruiter", recruiter);
		modelAndView.setViewName("recruiter/recruiter_profile");
        return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/new_vacancies", method = RequestMethod.GET)
    public String vacancies(Model model, String keyword, Direction direction, String company, String country, Integer offset, Integer maxResults) {
		log.info("start newVacancies");
		List<Vacancy> notAssignVacancies  = vacancyDAO.getNotAssignVacancies(keyword, direction, company, country, offset, maxResults);
		model.addAttribute("notAssignVacancies", notAssignVacancies);
		model.addAttribute("keyword", keyword);
		model.addAttribute("direction", direction);
		model.addAttribute("company", company);
		model.addAttribute("country", country);
		model.addAttribute("offset", offset);
		model.addAttribute("count", vacancyDAO.count(keyword, direction, company, country, offset, maxResults));
		model.addAttribute("countries", countryDAO.getCountries());
		model.addAttribute("companies", companyDAO.getCompanies());
		
		log.info("end newVacancies");
        return "/recruiter/vacancies";
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/my_interviews", method = RequestMethod.GET)
    public ModelAndView interviews(ModelMap model) {
		log.info("start myInterviews");
        
        ModelAndView modelAndView = new ModelAndView();	
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        Recruiter recruiter  = recruiterDAO.getRecruiter(login);
        
        List <Interview> interviews = interviewDAO.getRecruiterInterviews(recruiter.getId());
		
		log.debug("recruiterInterviews " + interviews);
		modelAndView.addObject("recruiterInterviews", interviews);
		
		modelAndView.setViewName("recruiter/my_interviews");
		log.info("end myInterviews");
        return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/assignVacancy", method = RequestMethod.GET)
    public String assignVacancy(Model midel, @RequestParam("id")int vacancyId, String keyword, Direction direction, String companyName, String address, Integer offset, Integer maxResults) {
		log.info("start assignVacancy");
		log.debug("vacancyId " + vacancyId);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        
        Vacancy vacancy = vacancyDAO.getVacancyById(vacancyId);
        Recruiter recruiter  = recruiterDAO.getRecruiter(login);
        vacancy.setState(VacancyState.PROCESSING);
        vacancyDAO.updateVacancy(vacancy);
        recruiterDAO.assignVacancy(vacancy, recruiter);
		
		List<Vacancy> notAssignVacancies  = vacancyDAO.getNotAssignVacancies(keyword, direction, companyName, address, offset, maxResults);
		log.debug("notAssignVacancies " + notAssignVacancies);
		
		log.info("end assignVacancy");
        return "redirect:/recruiter/my_vacancies";
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/my_vacancies", method = RequestMethod.GET)
    public ModelAndView myVacancies(ModelMap model) {
		log.info("start myVacancies");
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        Recruiter recruiter  = recruiterDAO.getRecruiter(login);
        
        Set<Vacancy> recruiterVacancies = recruiter.getAssignedVacancies(); 
        
		ModelAndView modelAndView = new ModelAndView();
        
		modelAndView.addObject("recruiterVacancies",  recruiterVacancies);
		modelAndView.setViewName("recruiter/my_vacancies");
		log.info("end myVacancies");
        return modelAndView;
	}
	
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/applied_candidates", method = RequestMethod.GET)
    public ModelAndView appliedCandidates(@RequestParam("id")int vacancyId) {
		log.info("start applied_candidates");
		log.debug("vacancyId " + vacancyId);
		ModelAndView modelAndView = new ModelAndView();
		Set<Candidate> appliedCandidates = vacancyDAO.getVacancyById(vacancyId).getAppliedCandidates();
		log.debug("appliedCandidates==> " + appliedCandidates);
		modelAndView.addObject("appliedCandidates",  appliedCandidates);
		modelAndView.setViewName("recruiter/applied_candidates");
		log.info("end applied_candidates");
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/applied_candidate", method = RequestMethod.GET)
    public ModelAndView appliedCandidateProfile(@RequestParam("id")int candidateId) {
		log.info("start appliedCandidateProfile");
		log.debug("candidateId " + candidateId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("candidate/candidate_profile");
		log.info("end appliedCandidateProfile");
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/candidates", method = RequestMethod.GET)
    public String candidates(Model model, Direction interstedDirection, String country, Integer ageFrom, Integer ageTo, Integer offset, Integer maxResults) {
		log.info("start candidates");
		log.debug("country==> " + country);
		log.debug("ageFrom==> " + ageFrom);
		log.debug("interstedDirection==> " + interstedDirection);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("recruiter/applied_candidates");
		model.addAttribute("appliedCandidates", candidateDAO.getCandidates(interstedDirection, country, ageFrom, ageTo, offset, maxResults));
		
		model.addAttribute("interstedDirection", interstedDirection);
		
		//Country country = countryDAO.getCountryByName(address);
		//log.debug("country---> " + country);
		
		model.addAttribute("address", country);
		model.addAttribute("ageFrom", ageFrom);
		model.addAttribute("ageTo", ageTo);
		model.addAttribute("count", candidateDAO.count(interstedDirection, country, ageFrom, ageTo));
		model.addAttribute("offset", offset);
		
		model.addAttribute("countries", countryDAO.getCountries());
		
		log.debug("model==> " + model);
		log.info("end candidates");
		return "/recruiter/applied_candidates";
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/recruiter-schedule", method = RequestMethod.GET)
	public String recruiterSchedule(Model model) throws JSONException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        Recruiter recruiter  = recruiterDAO.getRecruiter(login);
        List<Interview> interviews = interviewDAO.getRecruiterInterviews(recruiter.getId());
        
        JSONObject recruiterInterviews =  ListToJSONConverter.getInterviewsInJSON(interviews);
        
        model.addAttribute("interviews", recruiterInterviews.toString());
        return "/recruiter/recruiter-schedule";
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
	@RequestMapping(value = "/find-candidates", method = RequestMethod.GET)
	public String findCandidates(Model  model, @RequestParam(value = "id", required  = true) int vacancyId){
		System.err.println(vacancyId);
		Vacancy vacancy = vacancyDAO.getVacancyById(vacancyId);
		List<Candidate> allCandidates = candidateDAO.getAllCandidates();
		
		mather.clearMathingData();
		mather.initialize();
		List<Candidate> foundCandidates = mather.findCandidates(vacancy, allCandidates);
		ArrayList<String> mathedPercentage = mather.getMathedPercentage();
		mather.sortResult(mathedPercentage);
		
		System.err.println(foundCandidates);
		System.err.println(mathedPercentage + " " + mathedPercentage.size());
		
		model.addAttribute("size", foundCandidates.size());
		model.addAttribute("candidates", foundCandidates);
		model.addAttribute("percentages", mathedPercentage);
		return "recruiter/found-candidates";
	}
	
	@RequestMapping(value = "/check_candidate_email_exist", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(@RequestParam(value = "email") String email) {
		log.info("start check_candidate_email_exist");
		System.out.println("candEmail " + email);
		Candidate candidate; 
		candidate = candidateDAO.getCandidateByEmail(email);
		String result="";
		if(candidate==null) {
			result = "User with this email does not exist" ;
		} else {
			User user = userDAO.getUser(email);
			user.getFirstName();
			result = user.getFirstName() + " " + user.getLastName() ;
		}
		
		log.debug("candidate " + candidate);
		return result;
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
    public String getEditInterviewerProfilePage (Model model) {
		log.info("edit_profile");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        //if(recruiter==null) 
        Recruiter recruiter = recruiterDAO.getRecruiter(email);
		model.addAttribute("recruiter", recruiter);
        return "recruiter/edit_profile";
    }
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
    @RequestMapping(value = "/edit_recruiter_personal_info", method = RequestMethod.POST)
    public String editUserInfo (Model model, @RequestParam String name,
    		@RequestParam String middleName,
    		@RequestParam String lastname) {
		log.info("start edit_recruiter_personal_info");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Recruiter recruiter = (Recruiter) userDAO.getUser(email);
        recruiter.setFirstName(name);
        recruiter.setMiddleName(middleName);
        recruiter.setLastName(lastname);
    	
    	recruiterDAO.update(recruiter);
		model.addAttribute("recruiter", recruiter);
        return "redirect:edit_profile";
    }
	
	 @PreAuthorize("hasRole('ROLE_RECRUITER')")
	 @RequestMapping(value = "/check_email_exist_btn", method = RequestMethod.POST)
	 @ResponseBody
	 public String changeEmailRequest(@RequestParam(value = "email") String newEmail) {
	    	log.info("start changeEmailRequest");
	    	log.debug("newEmail " + newEmail);
	    	
	    	User user = null;
	    	user = userDAO.getUser(newEmail);
	    	String result="";
	    	if(user != null) {
	    		log.debug("user != null");
	    		result = "<div style=\"color:red\">User with this email already exist</div>" ;
	    	} else {
	    		log.debug("user == null");
	    		UUID uuid = UUID.randomUUID();
	    		log.debug("uuid==> " + uuid);
	    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    		String oldEmail = auth.getName();
	    		log.debug("oldEmail" + oldEmail);
	    		String message = "For email change, please, follow this link" + "\n"
					+ SendMessage.getFullURL(context)+ "recruiter/change_email_confirm?uuid=" + uuid + "&new_email=" + newEmail;
	    	
	    		//send mail to confirm new email
	    		SendMessage.send(newEmail, "Changing email", message);
	    		//save cod to db
	    		int interviewerId = userDAO.getUser(oldEmail).getId();
	    		userDAO.saveUUID(uuid.toString(), interviewerId);
	    		result = "<div style=\"color:green\">To confirm the email, click on the link sent to new email</div>" ;
	    		log.info("end changeEmailRequest");
	    		SecurityContextHolder.getContext().setAuthentication(null);
	    	}
	        return result;
	  }
	 
	   
	    @RequestMapping(value = "/change_email_confirm")
	    public ModelAndView changeEmailConfirm(@RequestParam("uuid")String uuid, @RequestParam("new_email") String newEmail) {
	    	log.info("start changeEmailConfirm");
	    	log.debug("uuid " + uuid);
	    	
	    	log.debug("newEmail " + newEmail);
	    	userDAO.confirm(uuid, newEmail);
	    	
	    	ModelAndView modelAndView = new ModelAndView();	
	    	//if(recruiter==null)	
	    	Recruiter recruiter = (Recruiter) userDAO.getUser(newEmail);
	    	recruiter.setEmail(newEmail);
			modelAndView.addObject("recruiter", recruiter);
			modelAndView.setViewName("recruiter/recruiter_profile");
			modelAndView.addObject("msg", "Email was chenged");
	    	log.info("end changeEmailConfirm");
			return modelAndView;
	    }
	    
	    @PreAuthorize("hasRole('ROLE_RECRUITER')")
	    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
	    public ModelAndView changePassword(@RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2) {
	    	log.info("start change_password");
	    	ModelAndView modelAndView = new ModelAndView();	
	    	log.debug("newPassword1 " + newPassword1);
	    	log.debug("newPassword2 " + newPassword2);
	    	
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String login = auth.getName();
            Recruiter recruiter = recruiterDAO.getRecruiter(login);
	    	
	    	if(! newPassword1.equals(newPassword2)) {
	    		modelAndView.addObject("msg", "You entered not same password");
	    	} else {
	    		String hashedPassword = BcryptHashing.BcryptHash(newPassword1);
	    		recruiter.setPassword(hashedPassword);
	    		userDAO.update(recruiter);
	    		modelAndView.addObject("msg", "Password successfully changed");
	    	}
			modelAndView.addObject("recruiter", recruiter);
			modelAndView.setViewName("recruiter/edit_profile");
	    	log.info("end change_password");
			return modelAndView;
	    }
	

}
