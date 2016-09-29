package com.edvantis.workopolis.MongoDB;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.edvantis.workopolis.model.skill.Importance;
import com.edvantis.workopolis.model.skill.TechSkillCategory;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.skill.TechnicalSkillLevel;
import com.edvantis.workopolis.model.vacancy.VacancyState;

@Controller
@SessionAttributes("vacancy")
public class MongoController {

	ApplicationContext ctx = new AnnotationConfigApplicationContext(
			SpringMongoConfig.class);

	@Autowired
	MongoOperations mongoOperation = (MongoOperations) ctx
			.getBean("mongoTemplate");

	@RequestMapping(value = "/mongo_vacancies", method = RequestMethod.GET)
	public String showVacancies(
			Model model,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "salary", required = false) Integer salary,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "location", required = false) String location) {
		Query query = new Query();
		if (keyword != null) query.addCriteria(Criteria.where("title").regex(keyword));
		if (salary != null) query.addCriteria(Criteria.where("salary").gte(salary));
		if (direction != null) query.addCriteria(Criteria.where("direction").regex(direction));
		if (location != null) query.addCriteria(Criteria.where("location").regex(location));
		List<Vacancy> vacancies = mongoOperation.find(query, Vacancy.class);
		model.addAttribute("vacancies", vacancies);
		model.addAttribute("vacancyCount", vacancies.size());
		return "/mongo_vacancies";
	}

	@RequestMapping(value = "/mongo_add_vacancy", method = RequestMethod.GET)
	public String newVacancy(Model model) {
		Vacancy newVacancy = new Vacancy();
		model.addAttribute("vacancy", newVacancy);
		return "employer/mongo_add_vacancy";
	}

	@RequestMapping(value = "/mongo_add_vacancy", method = RequestMethod.POST)
	public String saveVacancy(Model model, @ModelAttribute Vacancy vacancy) {
		vacancy.setPostDate(new Date().toLocaleString());
		mongoOperation.save(vacancy);
		System.err.println(vacancy);
		return "redirect:mongo_vacancies";
	}

	@RequestMapping(value = "/mongo_add_technical_skill", method = RequestMethod.GET)
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
		return "/employer/mongo_add_vacancy";
	}

	@RequestMapping(value = "/mongo_delete_technical_skill", method = RequestMethod.GET)
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
		return "/employer/mongo_add_vacancy";
	}

}
