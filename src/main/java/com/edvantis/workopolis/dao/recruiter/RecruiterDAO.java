package com.edvantis.workopolis.dao.recruiter;

import java.util.List;

import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.vacancy.Vacancy;


public interface RecruiterDAO {

	public List<Recruiter> getRecruiters();
	
	public  Recruiter getRecruiterById(int Id);
	
	public void addRecruiter (Recruiter recruiter);
	
	public void deleteRecruiter (int id); 
	
	public void assignVacancy(Vacancy vacancy, Recruiter recruiter);//int recruiterId
	
	public Recruiter getRecruiter(String email);
	
	public List<Vacancy> getVacanciesByRecruiter(Recruiter recruiter);

	void editRecruiter(Recruiter recruiter);
	
	public Recruiter getRecruiterByEmail(String email);
	
	public void update(Recruiter recruiter);
	
}
