package com.edvantis.workopolis.dao.vacancy;

import java.util.List;

import com.edvantis.workopolis.model.skill.PersonalSkill;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.vacancy.Direction;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public interface VacancyDAO {

	public List<Vacancy> getAllVacancies();
	
	public List<Vacancy> getVacancies(
			String keyword,
			Integer salary,
			Direction direction,
			String location,
			Integer offset,
			Integer maxResults);

	public List<Vacancy> getNotApprovedVacancies();
	
	public List<Vacancy> getClosedVacancies();
	
	public Long count(String keyword, Integer salary, Direction direction, String location);
	
	public Long count(String keyword, Direction direction);

	public Vacancy getVacancyById(int id);

	public void addVacancy(Vacancy vaccancy);

	public void updateVacancy(Vacancy vacancy);

	public void deteteVacancy(int id);

	public void addSkill(PersonalSkill skill);

	public List<Vacancy> getNotAssignVacancies(String keyword, Direction direction, String companyName, String location, Integer offset, Integer maxResults);

	public List<Vacancy> getRecruiterVacancies(Integer offset, Integer maxResults, Recruiter recruiter);

	public Long count(String keyword, Direction direction, String company, String country, Integer offset, Integer maxResults);
	
	

}
