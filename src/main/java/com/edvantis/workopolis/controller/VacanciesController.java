package com.edvantis.workopolis.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.edvantis.workopolis.dao.employer.EmployerDAO;
import com.edvantis.workopolis.dao.location.CountryDAO;
import com.edvantis.workopolis.dao.skill.TechnicalSkillDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.vacancy.VacancyDAO;
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
import com.edvantis.workopolis.model.user.employer.Employer;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;
import com.edvantis.workopolis.model.vacancy.VacancyState;

@Controller
@SessionAttributes(value = { "vacancy", "technicalSkill" })
public class VacanciesController {

	static Logger log = Logger.getLogger(VacanciesController.class.getName());

	@Autowired
	private VacancyDAO vacancyDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private TechnicalSkillDAO technicalSkillDAO;

	@Autowired
	private EmployerDAO employerDAO;
	
	@Autowired
	private CountryDAO countryDAO;

	@ModelAttribute(value = "vacancy")
	public Vacancy getVacancy() {
		return new Vacancy();
	}

	@RequestMapping(value = "/vacancies", method = RequestMethod.GET)
	public String list(Model model, String keyword, Integer salary,
			Direction direction, String country, Integer offset,
			Integer maxResults) {
		List<Vacancy> vacancies = vacancyDAO.getVacancies(keyword, salary, direction, country, offset, maxResults);
		model.addAttribute("vacancies", vacancies);
		model.addAttribute("keyword", keyword);
		model.addAttribute("salary", salary);
		model.addAttribute("direction", direction);
		model.addAttribute("country", country);
		model.addAttribute("count",
				vacancyDAO.count(keyword, salary, direction, country));
		model.addAttribute("offset", offset);
		model.addAttribute("countries", countryDAO.getCountries());
		return "/vacancies";
	}

	@RequestMapping(value = "/vacancy/{id}", method = RequestMethod.GET)
	public ModelAndView vacancyDescription(@PathVariable("id") int id) {
		log.info("start vacancyDescription");
		log.debug("idVacancy " + id);
		ModelAndView model = new ModelAndView();

		Vacancy vacancy = vacancyDAO.getVacancyById(id);
		log.debug("vacancy " + vacancy);
		model.addObject("vacancy", vacancy);

		model.setViewName("vacancyDescription");
		return model;
	}

	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/add_vacancy", method = RequestMethod.GET)
	public String newVacancy(Model model) {

		model.addAttribute("vacancy", getVacancy());
		return "employer/add_vacancy";
	}
	
	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/delete_vacancy", method = RequestMethod.GET)
	public String deleteVacancy(Model model, Integer id) {
		vacancyDAO.deteteVacancy(id);
		return "redirect:/employer";
	}
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/delete_vacancy_as_manager", method = RequestMethod.GET)
	public String deleteVacancyAsManager(Model model, Integer id) {
		vacancyDAO.deteteVacancy(id);
		return "redirect:/manager/vacancies";
	}	
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RequestMapping(value = "/deactivate_vacancy", method = RequestMethod.GET)
	public String deactivateVacancy(Model model,Integer id, @ModelAttribute Employer employer) {
		model.addAttribute("employer", employer);
		Vacancy vacancy = vacancyDAO.getVacancyById(id);		
		vacancy.setState(VacancyState.CLOSED);
		vacancyDAO.updateVacancy(vacancy);
		return "redirect:/manager/employer_profile?id=" + employer.getId();
	}
	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/close_vacancy", method = RequestMethod.GET)
	public ModelAndView closeVacancy(Model model,Integer id) {
		Vacancy vacancy = vacancyDAO.getVacancyById(id);
		Date closeDate = new Date();
		vacancy.setState(VacancyState.CLOSED);
		vacancy.setCloseDate(closeDate);
		vacancyDAO.updateVacancy(vacancy);
		
		ModelAndView modelAndView = new ModelAndView();	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
		Employer employer = employerDAO.getEmployerByEmail(login);
		modelAndView.addObject("employer", employer);
		modelAndView.setViewName("employer/employer_profile");
		return modelAndView;
	}
	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/feedback_about_vacancy", method = RequestMethod.GET)
	public String leaveFeadbackAboutVacancy(Model model, Integer id) {		
		Vacancy vacancy = vacancyDAO.getVacancyById(id);
		model.addAttribute("vacancy", vacancy);
		return "employer/feedback_about_vacancy";
	}
		
	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/feedback_about_vacancy", method = RequestMethod.POST)
	public String leaveFeadback(Model model, @ModelAttribute Vacancy vacancy){
		model.addAttribute("vacancy", vacancy);
		vacancyDAO.updateVacancy(vacancy);
		return "redirect:/vacancy/"+vacancy.getId();
	}
	
	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/edit_vacancy", method = RequestMethod.GET)
	public String editVacancyPage(Model model, Integer id) {
		Vacancy vacancy = vacancyDAO.getVacancyById(id);
		model.addAttribute("vacancy", vacancy);
		return "employer/edit_vacancy";
	}

	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/edit_vacancy", method = RequestMethod.POST)
	public String editVacancy(Model model, @ModelAttribute Vacancy vacancy) {
		model.addAttribute("vacancy", vacancy);
		vacancyDAO.updateVacancy(vacancy);
		return "redirect:edit_vacancy?id=" + vacancy.getId();
	}

	@PreAuthorize("hasRole('ROLE_EMPLOYER')")
	@RequestMapping(value = "/add_vacancy", method = RequestMethod.POST)
	public String saveVacancy(Model model, @ModelAttribute Vacancy vacancy) {
		log.info("start add_vacancy");
		
		log.debug("vacancy " + vacancy);

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String email = auth.getName();
		Employer employer = (Employer) userDAO.getUser(email);
		
		
		vacancy.setCompany(employer.getCompany());
		vacancy.setPostDate(new Date());
		vacancy.setState(VacancyState.NOT_APPROVED);
		employer.addVacancy(vacancy);
		vacancyDAO.addVacancy(vacancy);
		employerDAO.editEmployer(employer);
		System.err.println(vacancy);
		return "redirect:/employer";
	}

	// Controllers for technical skills
	@RequestMapping(value = "/add_technical_skill", method = RequestMethod.GET)
	public String saveTechnicalSkill(
			Model model,
			@ModelAttribute Vacancy vacancy,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "category", required = false) TechSkillCategory category,
			@RequestParam(value = "level", required = false) TechnicalSkillLevel level,
			@RequestParam(value = "importance", required = false) Importance importance) {
		TechnicalSkill technicalSkill = new TechnicalSkill();
		technicalSkill.setName(name);
		technicalSkill.setCategory(category);
		technicalSkill.setLevel(level);
		technicalSkill.setImportance(importance);
		System.err.println(technicalSkill);
		vacancy.setTechnicalSkill(technicalSkill);
		model.addAttribute("vacancy", vacancy);
		log.info(vacancy + "passed to controller");
		return "/employer/add_vacancy";
	}

	@RequestMapping(value = "/delete_technical_skill", method = RequestMethod.GET)
	public String deleteTechnicalSkill(
			Model model,
			@ModelAttribute Vacancy vacancy,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "category", required = false) TechSkillCategory category,
			@RequestParam(value = "level", required = false) TechnicalSkillLevel level,
			@RequestParam(value = "importance", required = false) Importance importance) {
		TechnicalSkill technicalSkill = new TechnicalSkill();
		technicalSkill.setName(name);
		technicalSkill.setCategory(category);
		technicalSkill.setLevel(level);
		technicalSkill.setImportance(importance);
		for (Iterator<TechnicalSkill> it = vacancy.getTechnicalSkills()
				.iterator(); it.hasNext();) {
			TechnicalSkill skill = it.next();
			if (skill.equals(technicalSkill))
				vacancy.getTechnicalSkills().remove(skill);
		}
		model.addAttribute("vacancy", vacancy);
		return "/employer/add_vacancy";
	}

	// Controllers for communication skills
	@RequestMapping(value = "/add_communication_skill", method = RequestMethod.GET)
	public String saveCommunicationSkill(
			Model model,
			@ModelAttribute Vacancy vacancy,
			@RequestParam(value = "name", required = false) Language name,
			@RequestParam(value = "level", required = false) CommunicationSkillLevel level,
			@RequestParam(value = "importance", required = false) Importance importance) {
		CommunicationSkill communicationSkill = new CommunicationSkill();
		communicationSkill.setName(name);
		communicationSkill.setLevel(level);
		communicationSkill.setImportance(importance);
		System.err.println(communicationSkill);
		vacancy.setCommunicationSkill(communicationSkill);
		model.addAttribute("vacancy", vacancy);
		log.info(vacancy + "passed to controller");
		return "/employer/add_vacancy";
	}

	@RequestMapping(value = "/delete_communication_skill", method = RequestMethod.GET)
	public String deleteCommunicationSkill(
			Model model,
			@ModelAttribute Vacancy vacancy,
			@RequestParam(value = "name", required = false) Language name,
			@RequestParam(value = "level", required = false) CommunicationSkillLevel level,
			@RequestParam(value = "importance", required = false) Importance importance) {
		CommunicationSkill communicationSkill = new CommunicationSkill();
		communicationSkill.setName(name);
		communicationSkill.setLevel(level);
		communicationSkill.setImportance(importance);
		for (Iterator<CommunicationSkill> it = vacancy.getCommunicationSkills()
				.iterator(); it.hasNext();) {
			CommunicationSkill skill = it.next();
			if (skill.equals(communicationSkill))
				vacancy.getCommunicationSkills().remove(skill);
		}
		model.addAttribute("vacancy", vacancy);
		return "/employer/add_vacancy";
	}

	// Controllers for personal skills
	@RequestMapping(value = "/add_personal_skill", method = RequestMethod.GET)
	public String savePersonalSkill(
			Model model,
			@ModelAttribute Vacancy vacancy,
			@RequestParam(value = "name", required = false) PersonalSkillName name,
			@RequestParam(value = "level", required = false) PersonalSkillLevel level) {
		PersonalSkill personalSkill = new PersonalSkill();
		personalSkill.setPersonalSkillName(name);
		personalSkill.setPersonalSkillLevel(level);
		vacancy.setPersonalSkill(personalSkill);
		model.addAttribute("vacancy", vacancy);
		return "/employer/add_vacancy";
	}

	@RequestMapping(value = "/delete_personal_skill", method = RequestMethod.GET)
	public String deletePersonalSkill(
			Model model,
			@ModelAttribute Vacancy vacancy,
			@RequestParam(value = "name", required = false) PersonalSkillName name,
			@RequestParam(value = "level", required = false) PersonalSkillLevel level) {
		PersonalSkill personalSkill = new PersonalSkill();
		personalSkill.setPersonalSkillName(name);
		personalSkill.setPersonalSkillLevel(level);
		for (Iterator<PersonalSkill> it = vacancy.getPersonalSkills()
				.iterator(); it.hasNext();) {
			PersonalSkill skill = it.next();
			if (skill.equals(personalSkill))
				vacancy.getPersonalSkills().remove(skill);
		}
		model.addAttribute("vacancy", vacancy);
		return "/employer/add_vacancy";
	}
	

}
