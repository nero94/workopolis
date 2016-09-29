package com.edvantis.workopolis.dao.interview;

import java.util.List;

import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public interface InterviewDAO {
	
	public List<Interview> getInterviews();
	
	public Interview getInterviewById(int id);
	
	public void createInterview(Interview interview);
	
	public void updateInterview(Interview interview);
	
	public void deleteInterview(int id);
	
	public List<Interview> getRecruiterInterviews(int recruiterId);
	
	public List<Interview> getInterviewerInterviews(int interviewerId);
	
	public List<Interview> getCandidateInterviews(int candidateId);
	
	public List<Interview> getEmployerInterviewes(List<Vacancy> employerVacancies, Integer vacancyId);
	

}
