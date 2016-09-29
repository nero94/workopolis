package com.edvantis.workopolis.controller;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.offer.OfferDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.model.user.employer.Offer;
import com.edvantis.workopolis.model.user.employer.OfferState;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.service.EmailSendingService;

@Controller
public class OffersController {

	static Logger log = Logger.getLogger(EmployerController.class.getName());
	
	@Autowired
	private OfferDAO offerDAO;
	
	@Autowired
	private EmployerDAO employerDAO;
	
	@Autowired
	private VacancyDAO vacancyDAO;
	
	@Autowired
	private CandidateDAO candidateDAO;
	
	@ModelAttribute(value = "offer")
	public Offer getOffer(){
		return new Offer();
	}
	
	private EmailSendingService emailService = new EmailSendingService();
	
	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/create-offer", method = RequestMethod.GET)
    public String newOffer(Model model) {
	    
	    if (!model.containsAttribute("offer")) {
	        model.addAttribute("offer", getOffer());
	    }
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
    	Employer employer = employerDAO.getEmployerByEmail(email);
    	List <Vacancy> vacancies = employer.getCreatedVacancies();
	    
    	model.addAttribute("vacancies", vacancies);
    
	return "employer/create-offer";
    }
	
	@RequestMapping(value = "/select-candidate", method = RequestMethod.GET)
	public String selectCandidate(Model model, @RequestParam int vacancy) {
		
		Set <Candidate> candidates = vacancyDAO.getVacancyById(vacancy).getAppliedCandidates();
		model.addAttribute("candidates", candidates);
		return "employer/select-vacancy-candidate/select-candidate";
	}
	
	 @PreAuthorize("hasRole('ROLE_EMPLOYER')")
	 @RequestMapping(value = "/create-offer", method = RequestMethod.POST)
	 public String saveOffer(
			 Model model,
			 @ModelAttribute Offer offer,
			 @RequestParam(value = "vacancyId", required = false) Integer vacancyId,
			 @RequestParam(value = "candidateId", required = false) Integer candidateId
			 ){
		 Vacancy vacancy = vacancyDAO.getVacancyById(vacancyId);
		 Candidate candidate = candidateDAO.getCandidateById(candidateId);
		 offer.setVacancy(vacancy);
		 offer.setCandidate(candidate);
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String email = auth.getName();
	    	Employer employer = employerDAO.getEmployerByEmail(email);
		 
		 employer.addOffer(offer);
		 offer.setState(OfferState.CREATED_BY_EMPLOYER);
		 
		 offerDAO.createOffer(offer);
		 employerDAO.editEmployer(employer);
		 	
		 String subject = emailService.createOfferEmailSubject();
		 String message = emailService.createOfferEmailMessage(offer.getOfferDate().toString(), offer.getCandidate().getFirstName(), offer.getCandidate().getLastName(), offer.getVacancy().getTitle(), offer.getVacancy().getCompany().getName(), employer.getEmail());
		 emailService.sendEmail(offer.getCandidate().getEmail(), message, subject);
		 
		 
		 log.info("Offer created");
		 return "employer/create-offer";
	 }
			 
	 @PreAuthorize("hasRole('ROLE_EMPLOYER')")
	 @RequestMapping(value = "/delete_offer", method = RequestMethod.GET)
	 public String deleteOffer(Model model, Integer id){
		 offerDAO.deleteOffer(id);
		 return "/employer/my_employer_offers";
	 }
	 
}
