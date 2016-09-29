package com.edvantis.workopolis.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.education.EducationDAO;
import com.edvantis.workopolis.dao.experience.ExperienceDAO;
import com.edvantis.workopolis.dao.university.UniversityDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.Experiance;
import com.edvantis.workopolis.model.education.Education;
import com.edvantis.workopolis.model.education.EducationDegree;
import com.edvantis.workopolis.model.education.EducationField;
import com.edvantis.workopolis.model.education.University;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.location.Address;
import com.edvantis.workopolis.dao.interview.InterviewDAO;
import com.edvantis.workopolis.dao.location.CityDAO;
import com.edvantis.workopolis.dao.location.CountryDAO;
import com.edvantis.workopolis.dao.location.StateDAO;
import com.edvantis.workopolis.dao.offer.OfferDAO;
import com.edvantis.workopolis.model.skill.CommunicationSkill;
import com.edvantis.workopolis.model.skill.CommunicationSkillLevel;
import com.edvantis.workopolis.model.skill.Importance;
import com.edvantis.workopolis.model.skill.Language;
import com.edvantis.workopolis.model.skill.PersonalSkill;
import com.edvantis.workopolis.model.skill.PersonalSkillLevel;
import com.edvantis.workopolis.model.skill.PersonalSkillName;
import com.edvantis.workopolis.model.skill.TechSkillCategory;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.skill.TechnicalSkillLevel;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Offer;
import com.edvantis.workopolis.model.user.employer.OfferState;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.util.BcryptHashing;
import com.edvantis.workopolis.util.ListToJSONConverter;

@Controller
@SessionAttributes(value = {"candidate"})
@RequestMapping(value = "candidate")
public class CandidateController {
	
	static Logger log = Logger.getLogger(CandidateController.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@Autowired
	private CandidateDAO candidateDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CountryDAO countryDAO;
	
	@Autowired
	private StateDAO stateDAO;
	
	@Autowired
	private CityDAO cityDAO;
	
	@Autowired
	private VacancyDAO vacancyDAO;
	
	@Autowired
	private InterviewDAO interviewDAO;
	
	@Autowired
	private OfferDAO offerDAO;
	
	@Autowired
	private UniversityDAO univesityDAO;
	
	@Autowired
	private EducationDAO educationDAO;
	
	@Autowired
	private ExperienceDAO experianceDAO;
	
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView candidateProfile(ModelMap model) {
        log.info("ROLE_CANDIDATE");
        Collection collectionRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator itr = collectionRole.iterator();
        Object role = null;
        while(itr.hasNext()) {
        	role = itr.next();
        }
        model.addAttribute("role", role);   
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        
        model.addAttribute("name", login);
		ModelAndView modelAndView = new ModelAndView();	
		User candidate = candidateDAO.getCandidateByEmail(login);
		log.debug("candidate " + candidate);
		modelAndView.addObject("candidate", candidate);
		modelAndView.setViewName("candidate/candidate_profile");
        return modelAndView;
    }
	
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/edit_profile", method = RequestMethod.GET)
    public String candidateProfileEdit(Model model) {
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String login = auth.getName();
         Candidate candidate = candidateDAO.getCandidateByEmail(login);
         model.addAttribute("candidate", candidate);
    	return "candidate/edit_profile";    	
    }
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/edit_personal_info", method = RequestMethod.POST)
    public String candidateInfoEdit(
    		Model model,
    		@ModelAttribute Candidate candidate,
    		String name,
    		String lastname,
    		String middleName,
    		Integer telNumber,
    		java.sql.Date dateOfBirth,
    		String additionalInformation,
    		String skypeUrl,
    		String facebookUrl,
    		String linkedInUrl) {
         candidate.setFirstName(name);
         candidate.setLastName(lastname);
         candidate.setMiddleName(middleName);
         candidate.setTelNumber(telNumber);
         candidate.setDateofBirth(dateOfBirth);
         candidate.setAdditionalInformation(additionalInformation);
         candidate.setSkypeUrl(skypeUrl);
         candidate.setFacebookUrl(facebookUrl);
         candidate.setLinkedInUrl(linkedInUrl);
         candidateDAO.updateCandidate(candidate);
         model.addAttribute("candidate", candidate);
    	return "redirect:/candidate/edit_profile";    	
    }
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/edit_location_info", method = RequestMethod.POST)
    public String candidateLocationInfoEdit(
    		Model model,
    		Integer country,
    		Integer state,
    		Integer city,
    		Integer zip,
    		String street) {
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         String login = auth.getName();
         Candidate candidate = candidateDAO.getCandidateByEmail(login);
         if (country != null) candidate.getAddress().setCountry(countryDAO.getCountryById(country));
         if (state != null) candidate.getAddress().setState(stateDAO.getStateById(state));
         if (city != null) candidate.getAddress().setCity(cityDAO.getCityById(city));
         candidate.getAddress().setZip(zip);
         candidate.getAddress().setStreet(street);
         candidateDAO.updateCandidate(candidate);
         model.addAttribute("candidate", candidate);
    	return "redirect:/candidate/edit_profile";    	
    }
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/edit_professional_info", method = RequestMethod.POST)
    public String candidateProffesionalInfoEdit(
    		Model model,
    		@ModelAttribute Candidate candidate,
    		@RequestParam(required = false) Direction direction){
    	if (direction != null) candidate.setInterstedDirection(direction);
    	//System.err.println(candidate.getCandidateTechnicalSkills());
        candidateDAO.updateCandidate(candidate);
        model.addAttribute("candidate", candidate);
    	return "redirect:/candidate/edit_profile";
    }
    
	// Controllers for technical skills
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/add_technical_skill", method = RequestMethod.GET)
	public String saveTechnicalSkill(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "category", required = false) TechSkillCategory category,
			@RequestParam(value = "level", required = false) TechnicalSkillLevel level,
			@RequestParam(value = "importance", required = false) Importance importance) {
		TechnicalSkill technicalSkill = new TechnicalSkill();
		technicalSkill.setName(name);
		technicalSkill.setCategory(category);
		technicalSkill.setLevel(level);
		technicalSkill.setImportance(importance);
		//System.err.println(technicalSkill);
		candidate.setTechnicalSkill(technicalSkill);
		//System.err.println(candidate.getCandidateTechnicalSkills());
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/delete_technical_skill", method = RequestMethod.GET)
	public String deleteTechnicalSkill(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "category", required = false) TechSkillCategory category,
			@RequestParam(value = "level", required = false) TechnicalSkillLevel level) {
		TechnicalSkill technicalSkill = new TechnicalSkill();
		technicalSkill.setName(name);
		technicalSkill.setCategory(category);
		technicalSkill.setLevel(level);
		for (Iterator<TechnicalSkill> it = candidate.getCandidateTechnicalSkills()
				.iterator(); it.hasNext();) {
			TechnicalSkill skill = it.next();
			if (skill.equals(technicalSkill))
				candidate.getCandidateTechnicalSkills().remove(skill);
		}
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}

	// Controllers for communication skills
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/add_communication_skill", method = RequestMethod.GET)
	public String saveCommunicationSkill(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = false) Language name,
			@RequestParam(value = "level", required = false) CommunicationSkillLevel level) {
		CommunicationSkill communicationSkill = new CommunicationSkill();
		communicationSkill.setName(name);
		communicationSkill.setLevel(level);
		System.err.println(communicationSkill);
		candidate.setCommunicationSkill(communicationSkill);
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/delete_communication_skill", method = RequestMethod.GET)
	public String deleteCommunicationSkill(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = false) Language name,
			@RequestParam(value = "level", required = false) CommunicationSkillLevel level) {
		CommunicationSkill communicationSkill = new CommunicationSkill();
		communicationSkill.setName(name);
		communicationSkill.setLevel(level);
		for (Iterator<CommunicationSkill> it = candidate.getCandidateCommunicationSkills()
				.iterator(); it.hasNext();) {
			CommunicationSkill skill = it.next();
			if (skill.equals(communicationSkill))
				candidate.getCandidateCommunicationSkills().remove(skill);
		}
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}

	// Controllers for personal skills
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/add_personal_skill", method = RequestMethod.GET)
	public String savePersonalSkill(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = false) PersonalSkillName name,
			@RequestParam(value = "level", required = false) PersonalSkillLevel level) {
		PersonalSkill personalSkill = new PersonalSkill();
		personalSkill.setPersonalSkillName(name);
		personalSkill.setPersonalSkillLevel(level);
		candidate.setPersonalSkill(personalSkill);
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
	
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/delete_personal_skill", method = RequestMethod.GET)
	public String deletePersonalSkill(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = false) PersonalSkillName name,
			@RequestParam(value = "level", required = false) PersonalSkillLevel level) {
		PersonalSkill personalSkill = new PersonalSkill();
		personalSkill.setPersonalSkillName(name);
		;
		personalSkill.setPersonalSkillLevel(level);
		for (Iterator<PersonalSkill> it = candidate.getCandidatePersonalSkills()
				.iterator(); it.hasNext();) {
			PersonalSkill skill = it.next();
			if (skill.equals(personalSkill))
				candidate.getCandidatePersonalSkills().remove(skill);
		}
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
	
	//Controllers for education
	@RequestMapping(value="/add_new_education", method = RequestMethod.GET)
	public String saveCandidateEducation(
			Model model,
			@RequestParam(value = "name", required = true)String universityName,
			@RequestParam(value = "country", required = true)Integer country,
			@RequestParam(value = "state", required = false)Integer state,
			@RequestParam(value = "city", required = false)Integer city,
			@RequestParam(value = "edField", required = false)EducationField edField,
			@RequestParam(value = "edDegree", required = false)EducationDegree edDegree,
			@RequestParam(value = "startDate", required = false)java.sql.Date startDate,
			@RequestParam(value = "finishDate", required = false)java.sql.Date finishDate){
			
    	System.err.println("START.");
    	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Candidate candidate = candidateDAO.getCandidateByEmail(login);
        
        Address address = new Address();
        University university = new University();
        Education education = new Education();
        
        if (country != null) address.setCountry(countryDAO.getCountryById(country));
        if (state != null) address.setState(stateDAO.getStateById(state));
        if (city != null) address.setCity(cityDAO.getCityById(city));        
		
        university.setName(universityName);
		university.setAddress(address);
		university.setStartDate(startDate);
		university.setFinishDate(finishDate);
		
		education.setUniversity(university);
		education.setEducationDegree(edDegree);
		education.setEducationField(edField);
		
		candidate.getEducations().add(education);
		

        System.err.println("FINISH.");
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/edit_education_info", method = RequestMethod.POST)
    public String candidateEducationalInfoEdit(
    		Model model,
    		@ModelAttribute Candidate candidate){

        candidateDAO.updateCandidate(candidate);
        model.addAttribute("candidate", candidate);
    	return "redirect:/candidate/edit_profile";
    }
//	/delete_candidate_education
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/delete_education", method = RequestMethod.GET)
	public String candidateDeleteEducation(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = true)String universityName,
			@RequestParam(value = "startDate", required = false)java.sql.Date startDate,
			@RequestParam(value = "finishDate", required = false)java.sql.Date finishDate
			){
		
		
		for (Education education : candidate.getEducations()) {
			LocalDate date1 = education.getUniversity().getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = education.getUniversity().getFinishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String uniName = education.getUniversity().getName();
			
			if(date1.toString().equals(startDate.toString()) && date2.toString().equals(finishDate.toString()) && uniName.equals(universityName)){
				System.err.println("START DELETING" + education);
				candidate.getEducations().remove(education);
				educationDAO.deleteEducation(education.getId());
			}
		}
		
		
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
	
//	Candidate experience controllers
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value="/add_new_experience", method = RequestMethod.GET)
	public String saveCandidateExperience(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = true) String companyName,
			@RequestParam(value = "position", required = true)String position,
			@RequestParam(value = "startDate", required = false)java.sql.Date startDate,
			@RequestParam(value = "finishDate", required = false)java.sql.Date finishDate,
			@RequestParam(value = "responsibilities", required = true)String responsibilities){
		
		Experiance experiance = new Experiance();
		experiance.setCompany(companyName);
		experiance.setPosition(position);
		experiance.setStartDate(startDate);
		experiance.setFinishdate(finishDate);
		experiance.setResponsibilities(responsibilities);
		
		candidate.getExperiances().add(experiance);
		
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/edit_experiance_info", method = RequestMethod.POST)
    public String candidateExperienceInfoEdit(
    		Model model,
    		@ModelAttribute Candidate candidate){

        candidateDAO.updateCandidate(candidate);
        model.addAttribute("candidate", candidate);
    	return "redirect:/candidate/edit_profile";
	}
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/delete_experience", method = RequestMethod.GET)
	public String deleteCandidateExperience(
			Model model,
			@ModelAttribute Candidate candidate,
			@RequestParam(value = "name", required = true)String companyName,
			@RequestParam(value = "position", required  = true)String position,
			@RequestParam(value = "responsibilities", required = true)String responsibilies){
		
		for (Experiance experiance : candidate.getExperiances()) {
			if (experiance.getCompanyName().equals(companyName) && experiance.getPosition().equals(position) && experiance.getResponsibilities().equals(responsibilies)){
				candidate.getExperiances().remove(experiance);
				experianceDAO.deleteExperience(experiance.getId());
			}
		}
		
		model.addAttribute("candidate", candidate);
		return "/candidate/edit_profile";
	}
	
	
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/delete_cv", method = RequestMethod.GET)
	public ModelAndView deleteCv() {
		log.info("start delete_cv");
		String candidateEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("candidateEmail " + candidateEmail);
		String cvName = candidateDAO.getCvNameByEmail(candidateEmail);
		log.debug("cvName " + cvName);
		Path path = Paths.get("webapps\\ROOT\\resources\\candidate\\candidate_cv\\" + cvName);//
		try {
			Files.delete(path);
			candidateDAO.deteteCandidateCV(candidateEmail);
			
		} catch (IOException e) {
			log.error(e);
		}
		log.info("end delete_cv");
		return new ModelAndView("redirect:/candidate");
	} 
  
    
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/upload_cv", method = RequestMethod.POST)
	//public @ResponseBody
	public ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file) {
		log.info("start uploadFileHandler");
		String candidateEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		ModelAndView model = new ModelAndView();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = "webapps\\ROOT\\resources\\candidate\\candidate_cv";
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();
				String filename = candidateEmail + file.getOriginalFilename();
				//log.debug("filename " + filename);
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()+ File.separator + filename);//
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				log.debug("candidateEmail " + candidateEmail);
				candidateDAO.addCandidateCV(filename, candidateEmail);
				return new ModelAndView("redirect:/candidate");
			} catch (Exception e) {
				log.error(e);
				return new ModelAndView("redirect:/candidate");
			}
		} else {
			log.debug("Your file is empty");
			model.addObject("msg", "Your file is empty");
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String login = auth.getName();
			User candidate = userDAO.getUser(login);
			model.addObject("candidate", candidate);
			model.setViewName("candidate/candidate_profile");
			
			return model;
		}
	}
	
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException {
		log.info("start download");
		String candidateEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    	String cvName = candidateDAO.getCvNameByEmail(candidateEmail);
    	log.debug("cvName " + cvName);
        File file = new File("webapps/ROOT/resources/candidate/candidate_cv/" + cvName);
        InputStream is = new FileInputStream(file);
 
        // MIME type of the file
        response.setContentType("application/octet-stream");
        // Response header
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + file.getName() + "\"");
        // Read from the file and write into the response
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.flush();
        os.close();
        is.close();
        log.info("end download");
    }
    
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/change_photo", method = RequestMethod.POST)
	public ModelAndView changePhoto(@RequestParam("file") MultipartFile file) {
		log.info("start change_photo");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
		log.info("start delete");
		String candidateEmail = SecurityContextHolder.getContext().getAuthentication().getName();
		log.debug("candidateEmail " + candidateEmail);
		String filename = candidateDAO.getPhotoNameByEmail(candidateEmail);
		log.debug("filename " + filename);
		if(filename == null){
			filename = login + "_" + file.getOriginalFilename();
			//save to db
			candidateDAO.addPhoto(filename, login);
		}
		Path path = Paths.get("webapps\\ROOT\\resources\\candidate\\photos\\" + filename);
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
			String rootPath = "webapps\\ROOT\\resources\\candidate";
			File dir = new File(rootPath + File.separator + "photos");
			if (!dir.exists())
				dir.mkdirs();
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath()+ File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			log.info("end upload");
			log.info("end change_photo");
			return new ModelAndView("redirect:/candidate");
		} catch (Exception e) {
			log.error(e);
			return new ModelAndView("redirect:/candidate");
		}
		
		
	}
	
	
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String apply(Model model, @RequestParam("id")int vacancyId) {
		log.info("start apply vacancy");
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ANONYMOUS]")){
			log.info("[ROLE_ANONYMOUS]");
			log.info("id " + vacancyId);
			return "redirect:login";
		}
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_CANDIDATE]")){
			log.info("[ROLE_CANDIDATE]");
			log.debug("vacancyId " + vacancyId);
			String candidateEmail = SecurityContextHolder.getContext().getAuthentication().getName();
			log.debug("candidateEmail " + candidateEmail);
			Candidate candidate = candidateDAO.getCandidateByEmail(candidateEmail);
			log.debug("candidate " + candidate);
			int candidateId = candidate.getId();
			log.debug("candidateId " + candidateId);
			candidateDAO.applyVacancy(candidateId, vacancyId);
		}
		
		log.info("end apply vacancy");
		return "redirect:/vacancy/" + vacancyId;
	}
	
	@ModelAttribute(value = "candidate")
	public Candidate getCandidate(){
		return new Candidate();
	}
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/interviews", method = RequestMethod.GET)
    public ModelAndView interviews(ModelMap model) {
		log.info("start myInterviews");
        
        ModelAndView modelAndView = new ModelAndView();	
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
    	Candidate candidate = (Candidate) userDAO.getUser(email);
        
        List <Interview> interviews = interviewDAO.getCandidateInterviews(candidate.getId());
		
		log.debug("candidateInterviews " + interviews);
		modelAndView.addObject("candidateInterviews", interviews);
		
		modelAndView.setViewName("candidate/candidate_interview");
		log.info("end myInterviews");
        return modelAndView;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String goToCandidateProfile(Model model, @PathVariable("id") int candidateId){
		Candidate candidate = candidateDAO.getCandidateById(candidateId);
		
		model.addAttribute("candidate", candidate);
		return "candidate/candidate_profile";
		
	}
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String recruiterSchedule(Model model) throws JSONException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        log.debug("login--> "+login);
        Candidate candidate = (Candidate) userDAO.getUser(login);
        List<Interview> interviews = interviewDAO.getCandidateInterviews(candidate.getId());
        List<Offer> offers = offerDAO.getCandidateOffers(candidate.getId());
        
        JSONObject candidateInterviews =  ListToJSONConverter.getInterviewsInJSON(interviews);
        JSONObject candidateOffers = ListToJSONConverter.getOffersInJSON(offers);
        
        model.addAttribute("interviews", candidateInterviews.toString());
        model.addAttribute("offers", candidateOffers.toString());
        return "/candidate/candidate-schedule";
	}
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String getCandidateOffers(Model model){
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Candidate candidate = (Candidate) userDAO.getUser(login);
	    
    	List<Offer> candidateOffers = offerDAO.getCandidateOffers(candidate.getId());
	    model.addAttribute("offers", candidateOffers);
    	return "candidate/candidate-offers";
    }
	
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/response-offer-state", method = RequestMethod.GET)
    public String changeOfferState(
    		@RequestParam(value = "id", required = true)int offerId,
    		@RequestParam(value = "state", required = true)OfferState state){
    	
    	Offer offer = offerDAO.getOfferById(offerId);
    	offer.setState(state);
    	offerDAO.updateOffer(offer);
    	System.err.println(offer);
    	return "candidate/candidate-offers";
    }
    
	@PreAuthorize("hasRole('ROLE_CANDIDATE')")
	@RequestMapping(value = "/applied_vacancies", method = RequestMethod.GET)
	public ModelAndView listOfAppliedVacancies(ModelMap model) {
		log.debug("start applied_vacancies");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String login = auth.getName();
	    Candidate candidate = candidateDAO.getCandidateByEmail(login);  
        Set<Vacancy> candidateVacancies = candidate.getAppliedVacancys();   
        log.debug("candidateVacancies--> " + candidateVacancies);
		ModelAndView modelAndView = new ModelAndView();        
		modelAndView.addObject("candidateVacancies",candidateVacancies);
		modelAndView.setViewName("candidate/applied_vacancies");
		log.info("end applied_vacancies");
        return modelAndView;
	}
	
    @PreAuthorize("hasRole('ROLE_CANDIDATE')")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ModelAndView changePassword(@RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2) {
    	log.info("start change_password");
    	ModelAndView modelAndView = new ModelAndView();	
    	log.debug("newPassword1 " + newPassword1);
    	log.debug("newPassword2 " + newPassword2);
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Candidate interviewer = candidateDAO.getCandidateByEmail(login);
    	if(! newPassword1.equals(newPassword2)) {
    		modelAndView.addObject("msg", "You entered not same password");
    	} else {
    		String hashedPassword = BcryptHashing.BcryptHash(newPassword1);
    		interviewer.setPassword(hashedPassword);
    		userDAO.update(interviewer);
    		modelAndView.addObject("msg", "Password successfully changed");
    	}
		modelAndView.addObject("employer", interviewer);
		modelAndView.setViewName("candidate/edit_profile");
    	log.info("end change_password");
		return modelAndView;
    }

	


}
