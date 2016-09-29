package com.edvantis.workopolis.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.interview.InterviewDAO;
import com.edvantis.workopolis.dao.interviewer.InterviewerDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.interview.InterviewType;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.service.SendMessage;
import com.edvantis.workopolis.util.BcryptHashing;
import com.edvantis.workopolis.util.ListToJSONConverter;

@Controller
@RequestMapping(value = "/interviewer")
@SessionAttributes("interviewer")
public class InterviewerController {

	static Logger log = Logger.getLogger(InterviewerController.class.getName());
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	private InterviewerDAO interviewerDAO;
	
	@Autowired
	private InterviewDAO interviewDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	//Interviewer interviewer;
	
	@PreAuthorize("hasRole('ROLE_INTERVIEWER')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView interviewerProfile(ModelMap model) {
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        //if(interviewer==null) 
        Interviewer interviewer  = interviewerDAO.getInterviewerByEmail(login);
        ModelAndView modelAndView = new ModelAndView();	
		modelAndView.addObject("interviewer", interviewer);
		modelAndView.setViewName("interviewer/interviewer-profile");
		return modelAndView;
	}
	
	@PreAuthorize("hasRole('ROLE_INTERVIEWER')")
    @RequestMapping(value = "/interviews", method = RequestMethod.GET)
	public String interviewerInterviews(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    log.debug("login--> "+login);
	    //if(interviewer==null)     
	    Interviewer interviewer  = interviewerDAO.getInterviewerByEmail(login);
	    List<Interview> interviews = interviewDAO.getInterviewerInterviews(interviewer.getId());
	    model.addAttribute("interviews", interviews);
	    return "/interviewer/interviewer-interviews";
	}
	
	@PreAuthorize("hasRole('ROLE_INTERVIEWER')")
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String interviewerSchedule(Model model) throws JSONException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        //if(interviewer==null) 
        Interviewer interviewer = interviewerDAO.getInterviewerByEmail(login);
        List<Interview> interviews = interviewDAO.getInterviewerInterviews(interviewer.getId());
        
        JSONObject interviewerInterviews =  ListToJSONConverter.getInterviewsInJSON(interviews);
        
        model.addAttribute("interviews", interviewerInterviews.toString());
        return "/interviewer/interviewer-schedule";
		
	}
	
	@PreAuthorize("hasRole('ROLE_INTERVIEWER')")
    @RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
    public String getEditInterviewerProfilePage (Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        //if(interviewer==null) 
        Interviewer	interviewer = interviewerDAO.getInterviewerByEmail(email);
		model.addAttribute("interviewer", interviewer);
        return "interviewer/edit_interviewer_profile";
    }
	
	@PreAuthorize("hasRole('ROLE_INTERVIEWER')")
    @RequestMapping(value = "/edit_interviewer_personal_info", method = RequestMethod.POST)
    public String editUserInfo (
    		@ModelAttribute Interviewer interviewer,
    		Model model,
    		@RequestParam(value = "firstName", required = false) String firstName,
    		@RequestParam(value = "middleName", required = false) String middleName,
    		@RequestParam(value = "lastName", required = false) String lastName,
    		@RequestParam(value = "number", required = false) Integer telNumber,
    		@RequestParam(value = "skype", required = false) String skype) {
    	interviewer.setFirstName(firstName);
    	interviewer.setMiddleName(middleName);
    	interviewer.setLastName(lastName);
    	interviewer.setTelNumber(telNumber);
    	interviewer.setSkype(skype);
    	System.err.println(interviewer.toString());
    	interviewerDAO.update(interviewer);
		model.addAttribute("interviewer", interviewer);
        return "redirect:edit_profile";
    }
	
	@RequestMapping(value = "/add_int_field", method = RequestMethod.GET)
	public String addKnowledgeField(@ModelAttribute Interviewer interviewer, Model model,
			@RequestParam(value = "category", required = false) InterviewType knowledgeFiled) {
				interviewer.getKnowledgeField().add(knowledgeFiled);
				System.err.println(knowledgeFiled);
				System.err.println(interviewer);
				model.addAttribute("interviewer", interviewer);
				return "interviewer/edit_interviewer_profile";
			}
	
	@RequestMapping(value = "/delete_int_field", method = RequestMethod.GET)
	public String removeKnowledgeField(@ModelAttribute Interviewer interviewer,	Model model,
			@RequestParam(value = "category", required = false) InterviewType knowledgeFiled) {
				interviewer.getKnowledgeField().remove(knowledgeFiled);
				System.err.println(knowledgeFiled);
				System.err.println(interviewer);
				model.addAttribute("interviewer", interviewer);
				return "interviewer/edit_interviewer_profile";
			}
	
	 @PreAuthorize("hasRole('ROLE_INTERVIEWER')")
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
					+ SendMessage.getFullURL(context)+ "interviewer/change_email_confirm?uuid=" + uuid + "&new_email=" + newEmail;
	    	
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
	    	//if(interviewer==null)	
	    	Interviewer	interviewer = (Interviewer) userDAO.getUser(newEmail);
	    	interviewer.setEmail(newEmail);
			modelAndView.addObject("interviewer", interviewer);
			modelAndView.setViewName("interviewer/interviewer-profile");
			modelAndView.addObject("msg", "Email was chenged");
	    	log.info("end changeEmailConfirm");
			return modelAndView;
	    }
	    
	    @PreAuthorize("hasRole('ROLE_INTERVIEWER')")
	    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
	    public ModelAndView changePassword(@RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2) {
	    	log.info("start change_password");
	    	ModelAndView modelAndView = new ModelAndView();	
	    	log.debug("newPassword1 " + newPassword1);
	    	log.debug("newPassword2 " + newPassword2);
	    	
	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String login = auth.getName();
            Interviewer interviewer = interviewerDAO.getInterviewerByEmail(login);
	    	if(! newPassword1.equals(newPassword2)) {
	    		modelAndView.addObject("msg", "You entered not same password");
	    	} else {
	    		String hashedPassword = BcryptHashing.BcryptHash(newPassword1);
	    		interviewer.setPassword(hashedPassword);
	    		userDAO.update(interviewer);
	    		modelAndView.addObject("msg", "Password successfully changed");
	    	}
			modelAndView.addObject("employer", interviewer);
			modelAndView.setViewName("interviewer/edit_interviewer_profile");
	    	log.info("end change_password");
			return modelAndView;
	    }
	
	
	
	
	
	
}
