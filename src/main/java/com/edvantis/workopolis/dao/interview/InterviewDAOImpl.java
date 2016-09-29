package com.edvantis.workopolis.dao.interview;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.vacancy.VacancyDAOImpl;
import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public class InterviewDAOImpl implements InterviewDAO {
	
static Logger log = Logger.getLogger(VacancyDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Override
	public List<Interview> getInterviews() {
		log.info("start getInterview");
		Session session = sessionFactory.openSession();
		List<Interview> interviews = session.createQuery("from Interview as Interviews").list();
		log.info("interviews " + interviews);
		session.close();
		return interviews;
	}

	@Override
	public Interview getInterviewById(int id) {
		Session session = sessionFactory.openSession();
		Interview interview = (Interview) session.get(Interview.class, id);
		log.debug("vacancy " + interview);
		session.close();
		return interview;
	}

	@Override
	public void createInterview(Interview interview) {
		Session session = this.sessionFactory.openSession();
		session.save(interview);
		session.close();
		log.info("Interview added successfully");
		
	}

	@Override
	public void updateInterview(Interview interview) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(interview);
		session.getTransaction().commit();
		session.close();
		log.info("Interview updated successfully");
	}

	@Override
	public void deleteInterview(int id) {
		Session session = this.sessionFactory.openSession();
		Interview interview = (Interview)session.load(Interview.class, id);
		if (null  != interview){
			session.delete(interview);
			session.flush();
			log.info("interview deleted");
		}
		session.close();
	}

	@Override
	public List<Interview> getRecruiterInterviews(int recruiterId) {
		
		log.info("start getRecruiterInterviews");
		log.debug("recruiterId " + recruiterId);
		Session session = sessionFactory.openSession();
		List<Interview> interviews = session.createQuery("from Interview as Interviews where Recruiter_FK =" + recruiterId).list();
		
		session.close();
		
		log.info("finish getRecruiterInterviews");
		return interviews;
	}
	
	@Override
	public List<Interview> getInterviewerInterviews(int interviewerId) {
		
		log.info("start getInterviewerInterviews");
		log.debug("interviewerId " + interviewerId);
		Session session = sessionFactory.openSession();
		List<Interview> interviews = session.createQuery("from Interview as Interviews where Interviewer_FK =" + interviewerId).list();
		
		session.close();
		
		log.info("finish getInterviewerInterviews");
		return interviews;
	}
	
	@Override
	public List<Interview> getCandidateInterviews(int candidateId) {
		
		log.info("start getCandidateInterviews");
		log.debug("candidateId " + candidateId);
		Session session = sessionFactory.openSession();
		List<Interview> interviews = session.createQuery("from Interview as Interviews where Candidate_FK =" + candidateId).list();		
		session.close();
		
		log.info("finish getInterviewerInterviews");
		
		return interviews;
	}

	@Override
	public List<Interview> getEmployerInterviewes(List<Vacancy> employerVacancies, Integer vacancyId) {
		List<Interview> employerInterviews = new ArrayList<>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.err.println(vacancyId);
		if (vacancyId != null){
			List<Interview> interviews = session.createQuery("from Interview as Interviews where vacancyId=" + vacancyId).list();
			employerInterviews.addAll(interviews);
		}
		else
		for (int i = 0; i < employerVacancies.size(); i++) {
			List<Interview> interviews = session.createQuery("from Interview as Interviews where vacancyId=" + employerVacancies.get(i).getId()).list();
			employerInterviews.addAll(interviews);
		}
		session.getTransaction().commit();
		session.close();
		return employerInterviews;
	}
	

}
