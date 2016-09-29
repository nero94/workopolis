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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.edvantis.workopolis.dao.candidate.CandidateDAO;
import com.edvantis.workopolis.dao.interview.InterviewDAO;
import com.edvantis.workopolis.dao.interviewer.InterviewerDAO;
import com.edvantis.workopolis.dao.result.ResultDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.interview.InterviewResult;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.service.EmailSendingService;


@Controller
@SessionAttributes({"interview","vacancies","interviewers"})
public class InterviewsController {

	static Logger log = Logger.getLogger(InterviewsController.class.getName());
	
	@Autowired
	VacancyDAO vacancyDAO;
	
	@Autowired
	InterviewDAO interviewDAO;
	
	@Autowired
	InterviewerDAO interviewerDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CandidateDAO candidateDAO;
	
	@Autowired
	ResultDAO resultDAO;
	
	private EmailSendingService emailService = new EmailSendingService();
	
	@ModelAttribute(value = "interview")
	private Interview getInterview(){
		return new Interview();
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
	@RequestMapping(value = "/schedule-interview", method = RequestMethod.GET)
	public String newInterview(Model model, @RequestParam(value = "candidate", required = false) String candidate) {
	    
	    if (!model.containsAttribute("interview")) {
	        model.addAttribute("interview", getInterview());
	    }
	    List<Interviewer> allInterviewers = interviewerDAO.getInterviewers();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
		Recruiter recruiter = (Recruiter) userDAO.getUser(email);
	    Set<Vacancy> assignedVacancies = recruiter.getAssignedVacancies();
	   
	    model.addAttribute("vacancies", assignedVacancies);
	    model.addAttribute("interviewers", allInterviewers);
	    return "/recruiter/schedule-interview";
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
	@RequestMapping(value = "/schedule-interview", method = RequestMethod.POST)
	public String saveInterview(
			Model model,
			@ModelAttribute Interview interview,
			@RequestParam(value = "vacancyId", required  = false)Integer vacancyId,
			@RequestParam(value = "interviewerId", required = false)Integer interviewerId){
		Vacancy vacancy = vacancyDAO.getVacancyById(vacancyId);
		Interviewer interviewer = interviewerDAO.getInterviewerById(interviewerId);
		log.info("start schedule-interview");
		log.debug("interview" + interview);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
		Recruiter recruiter = (Recruiter) userDAO.getUser(email);
		
		interview.setVacancy(vacancy);
		interview.setInterviewer(interviewer);
		interview.setRecruiter(recruiter);
		
		System.err.println(interview);
		interviewDAO.createInterview(interview);
		
		String subject = emailService.createInterviewEmailSubject();
		String messageForCandidate = emailService.createInterviewEmailMessage(interview.getDate().toString(), interview.getInterviewer().getFirstName(), interview.getCandidate().getLastName(), interview.getVacancy().getTitle(), interview.getVacancy().getCompany().getName(), recruiter.getEmail());
		String messageForInterviewer = emailService.createInterviewEmailMessage(interview.getDate().toString(), interview.getInterviewer().getFirstName(), interview.getCandidate().getLastName(), interview.getVacancy().getTitle(), interview.getVacancy().getCompany().getName(), recruiter.getEmail());
		emailService.sendEmail(interview.getCandidate().getEmail(), messageForCandidate, subject);
		emailService.sendEmail(interviewer.getEmail(), messageForInterviewer, subject);
		
		
		return "redirect:/recruiter/my_interviews";
	}
	
	@RequestMapping(value = "/check-candidate", method = RequestMethod.GET)
	public String checkCandidateByEmail(
			Model model,
			@ModelAttribute Interview interview,
			@RequestParam(value = "email", required = false) String email){
		Candidate candidate = candidateDAO.getCandidateByEmail(email);
		interview.setCandidate(candidate);
		model.addAttribute("interview", interview);
		return "recruiter/schedule-interview";
	}
	
	@PreAuthorize("hasRole('ROLE_RECRUITER')")
	@RequestMapping(value = "/delete-interview", method = RequestMethod.GET)
	public String deleteInterview(@RequestParam("id")int interviewId) {	
		interviewDAO.deleteInterview(interviewId);
		return "redirect:/recruiter/my_interviews";
	}
	
	@PreAuthorize("hasRole('ROLE_INTERVIEWER')")
	@RequestMapping(value = "/add_interview_result", method = RequestMethod.GET)
	public String saveInterviewResult(
			@RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "identifiedSkills", required = false) String identifiedSkills,
			@RequestParam(value = "comment", required = false) String comment){
		
	 	Interview interview = interviewDAO.getInterviewById(id);
	 	
	 	interview.setResult(new InterviewResult());
	 	interview.getResult().setIdentifiedSkills(identifiedSkills);
	 	interview.getResult().setInterviewerComment(comment);
		System.err.println(interview.getResult());
		
		resultDAO.updateOrCreateResult(interview.getResult());
		interviewDAO.updateInterview(interview);
		return "redirect:/interviewer/interviews";
	}
			
}
