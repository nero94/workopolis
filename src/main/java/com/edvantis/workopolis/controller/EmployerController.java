package com.edvantis.workopolis.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.interview.InterviewDAO;
import com.edvantis.workopolis.dao.location.CityDAO;
import com.edvantis.workopolis.dao.location.CountryDAO;
import com.edvantis.workopolis.dao.location.StateDAO;
import com.edvantis.workopolis.dao.offer.OfferDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.model.user.employer.Offer;
import com.edvantis.workopolis.model.user.employer.OfferState;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.model.vacancy.VacancyState;
import com.edvantis.workopolis.service.SendMessage;
import com.edvantis.workopolis.util.BcryptHashing;
import com.edvantis.workopolis.util.ListToJSONConverter;

@Controller
@SessionAttributes(value = {"employer"})
@RequestMapping(value = "employer")
public class EmployerController {
	
	static Logger log = Logger.getLogger(EmployerController.class.getName());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private StateDAO stateDAO;
	
	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private EmployerDAO employerDAO;
	
	@Autowired
	private VacancyDAO vacancyDAO;
	
	@Autowired
	private OfferDAO offerDAO;
	
	@Autowired
	private InterviewDAO interviewDAO;
	
	@Autowired
	private CandidateDAO candidateDAO;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	//static Employer employer;
	
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView employerPage (ModelMap model) {
        log.info("ROLE_EMPLOYER");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("name", login);
		ModelAndView modelAndView = new ModelAndView();	
		Employer employer = employerDAO.getEmployerByEmail(login);
		modelAndView.addObject("employer", employer);

		modelAndView.setViewName("employer/employer_profile");
        return modelAndView;
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
    public String getEditProfilePage (Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Employer employer = employerDAO.getEmployerByEmail(email);
		model.addAttribute("employer", employer);
        return "employer/edit_profile";
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/edit_user_info", method = RequestMethod.POST)
    public String editUserInfo (
    		Model model,
    		@ModelAttribute Employer employer,
    		@RequestParam String name,
    		@RequestParam String middleName,
    		@RequestParam String lastname) {
    	log.debug("name " + name);
    	log.debug("middleName " + middleName);
    	log.debug("lastname " + lastname);
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
    	employer = (Employer) userDAO.getUser(email);
    	employer.setFirstName(name);
    	employer.setMiddleName(middleName);
    	employer.setLastName(lastname);
    	employerDAO.editEmployer(employer);
		model.addAttribute("employer", employer);
        return "redirect:edit_profile";
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/edit_company_info", method = RequestMethod.POST)
    public String editCompanyInfo (
    		Model model,
    		@ModelAttribute Employer employer,
    		String name,
    		Integer country,
    		Integer state,
    		Integer city) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
    	employer.getCompany().setName(name);
    	if (country != null) employer.getCompany().getAddress().setCountry(countryDAO.getCountryById(country));
    	if (state != null) employer.getCompany().getAddress().setState(stateDAO.getStateById(state));
    	if (city != null) employer.getCompany().getAddress().setCity(cityDAO.getCityById(city));
    	employerDAO.editEmployer(employer);
		model.addAttribute("employer", employer);
        return "redirect:edit_profile";
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/change_emp_photo", method = RequestMethod.POST)
    public ModelAndView changeCompanyPhoto (@RequestParam("file") MultipartFile file) {
    	log.info("change_emp_photo");
    	String email = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("candidateEmail " + email);
		String filename = employerDAO.getPhotoNameByEmail(email);
		log.debug("filename " + filename);
		if(filename == null){
			filename = email + "_" + file.getOriginalFilename();
			//save to db
			employerDAO.addLogo(filename, email);
		}
		Path path = Paths.get("webapps\\ROOT\\resources\\employer\\img\\" + filename);//src\\main\\webapp\\resources\\employer\\img\\
		try {
			Files.delete(path);
			log.info("photo deleted");
			} catch (IOException e) {
			log.error(e);
		}
		log.info("end delete");
		log.info("start upload");
		try {
			byte[] bytes = file.getBytes();
			// Creating the directory to store file
			String rootPath = "webapps\\ROOT\\resources\\employer";//src\\main\\webapp\\resources\\employer
			File dir = new File(rootPath + File.separator + "img");
			if (!dir.exists())
				dir.mkdirs();
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath()+ File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			log.info("end upload");
			log.info("end change_photo");
			return new ModelAndView("redirect:/employer");
		} catch (Exception e) {
			log.error(e);
			return new ModelAndView("redirect:/employer");
		}
		
    }
   
    @RequestMapping(value = "/change_email_confirm")
    public ModelAndView changeEmailConfirm(@RequestParam("uuid")String uuid, @RequestParam("new_email") String newEmail) {
    	log.info("start changeEmailConfirm");
    	log.debug("uuid " + uuid);
    	log.debug("newEmail " + newEmail);
    	employerDAO.confirm(uuid, newEmail);
    	ModelAndView modelAndView = new ModelAndView();	
    	Employer employer = employerDAO.getEmployerByEmail(newEmail);
    	employer.setEmail(newEmail);
		modelAndView.addObject("employer", employer);
		modelAndView.addObject("msg", "Email was chenged");
		modelAndView.setViewName("login");
    	log.info("end changeEmailConfirm");
		return modelAndView;
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ModelAndView changePassword(@ModelAttribute Employer employer, @RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2) {
    	log.info("start changeEmailConfirm");
    	ModelAndView modelAndView = new ModelAndView();	
    	log.debug("newPassword1 " + newPassword1);
    	log.debug("newPassword2 " + newPassword2);
    	if(! newPassword1.equals(newPassword2)) {
    		modelAndView.addObject("msg", "You entered not same password");
    	} else {
    		String hashedPassword = BcryptHashing.BcryptHash(newPassword1);
    		employer.setPassword(hashedPassword);
    		userDAO.update(employer);
    		modelAndView.addObject("msg", "Password successfully changed");
    	}
		modelAndView.addObject("employer", employer);
		modelAndView.setViewName("employer/edit_profile");
    	log.info("end changeEmailConfirm");
		return modelAndView;
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/my_offers", method = RequestMethod.GET)
    public String getEmployerOffers(Model model){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Employer employer = (Employer)userDAO.getUser(login);
    	List<Offer> employerOffers = offerDAO.getEmployerOffers(employer.getId());
	    model.addAttribute("offers", employerOffers);
    	return "employer/my_employer_offers";
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/change-offer-state", method = RequestMethod.GET)
    public String changeOfferState(
    		@RequestParam(value = "id", required = true)int offerId,
    		@RequestParam(value = "state", required = true)OfferState state){
    	
    	Offer offer = offerDAO.getOfferById(offerId);
    	offer.setState(state);
    	offerDAO.updateOffer(offer);
    	System.err.println(offer);
    	return "employer/my_employer_offers";
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/interviews", method = RequestMethod.GET)
    public String employerInterviews(Model model, Integer vacancyId){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Employer employer = (Employer)userDAO.getUser(login);
	    List<Vacancy> vacancies = employer.getCreatedVacancies();
	    List<Interview> employerInterviews = interviewDAO.getEmployerInterviewes(vacancies, vacancyId);
	    model.addAttribute("interviews", employerInterviews);
	    model.addAttribute("vacancies", vacancies);
	    return "employer/employer-interviews";
    }
    
    @PreAuthorize("hasRole('ROLE_EMPLOYER')")
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
    public String employerSchedule(Model model) throws JSONException{
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Employer employer = (Employer)userDAO.getUser(login);
	    List<Interview> employerInterviews = interviewDAO.getEmployerInterviewes(employer.getCreatedVacancies(), null);
    	List<Offer> employerOffers = new ArrayList<Offer>();
    	employerOffers.addAll(employer.getCreatedOffers());
	    
    	JSONObject employerInterviewsInJSON = ListToJSONConverter.getInterviewsInJSON(employerInterviews);
	    JSONObject employerOffersInJSON = ListToJSONConverter.getOffersInJSON(employerOffers);
	    
	    model.addAttribute("interviews", employerInterviewsInJSON.toString());
	    model.addAttribute("offers", employerOffersInJSON.toString());
    	return "employer/employer-schedule";
    }
    
    @RequestMapping(value = "/delete_vacancy", method = RequestMethod.GET)
    public String deleteVacancy(Model model, @RequestParam Integer id){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Employer employer = (Employer)userDAO.getUser(login);
	    Vacancy vacancy = vacancyDAO.getVacancyById(id);
	    vacancy.setState(VacancyState.CLOSED);
	    vacancyDAO.updateVacancy(vacancy);
	    employer.getCreatedVacancies().remove(vacancy);
	    employerDAO.editEmployer(employer);
	    model.addAttribute(employer);
		return "employer/employer_profile";
    }
    
    @RequestMapping(value = "/check_email_exist_btn", method = RequestMethod.POST)
    @ResponseBody
    public String checkEmailExist(@RequestParam(value = "email") String newEmail) {
    	System.out.println("check_email_exist_btn");
    	log.debug("newEmail " + newEmail);
    	User user = null;
    	user = userDAO.getUser(newEmail);
    	
    	String result="";
    	if(user != null) {
    		result = "<div style=\"color:red\">User with this email already exist</div>" ;
    	} else {
    		log.info("start changeEmailRequest");
        	UUID uuid = UUID.randomUUID();
    		log.debug("uuid==> " + uuid);
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String oldEmail = auth.getName();
        	log.debug("oldEmail" + oldEmail);
    		String message = "For email change, please, follow this link" + "\n"
    				+ SendMessage.getFullURL(context)+ "employer/change_email_confirm?uuid=" + uuid + "&new_email=" + newEmail;
        	
        	//send mail to confirm new email
        	SendMessage.send(newEmail, "Changing email", message);
        	//save code to db
        	int employerId = userDAO.getUser(oldEmail).getId();
        	employerDAO.saveUUID(uuid.toString(), employerId);
    		result = "<div style=\"color:green\">To confirm the email, click on the link sent to new email</div>" ;
    		log.info("end changeEmailRequest");
    		SecurityContextHolder.getContext().setAuthentication(null);
    		//return "redirect:/login"; 
    		
		}
		return result;
    }
    
    
    
    
 }
    
    
    
    

