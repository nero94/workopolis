package com.edvantis.workopolis.dao.recruiter;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.vacancy.Vacancy;

public class RecruiterDAOImpl implements RecruiterDAO {
	
	static Logger log = Logger.getLogger(RecruiterDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;

	@Override
	public List<Recruiter> getRecruiters() {
		Session session = sessionFactory.openSession();
		List<Recruiter> recruiters = session.createQuery("from Recruiter as Recruiters").list();
		session.close();
		return recruiters;
	}

	@Override
	public Recruiter getRecruiterById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRecruiter(Recruiter recruiter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecruiter(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignVacancy(Vacancy vacancy, Recruiter recruiter) {
		log.info("start applyVacancy");
		log.debug("vacancy " + vacancy);
		log.debug("recruiter " + recruiter);
		Set<Vacancy> recruitertAssignedVacancies = recruiter.getAssignedVacancies();
		log.debug("recruitertAssignedVacancies " + recruitertAssignedVacancies);
		recruitertAssignedVacancies.add(vacancy);
		recruiter.setAssignedVacancies(recruitertAssignedVacancies);
		Session session = sessionFactory.openSession();
		Transaction tx=session.beginTransaction();  
		String hql = "UPDATE Vacancy set RecruiterId=:RecruiterId WHERE VacancyId=:VacancyId";
		Query query = session.createQuery(hql);
		query.setParameter("RecruiterId", recruiter.getId());  
		query.setParameter("VacancyId", vacancy.getId());
		int status = query.executeUpdate();  
		tx.commit();
		session.close(); 
		log.info("end applyVacancy");
		
	}

	@Override
	public Recruiter getRecruiter(String email) {
		log.info("start getRecruiter by email");
		Session session = sessionFactory.openSession();
		Recruiter recruiter = (Recruiter) session.createQuery("from Recruiter as Recruiters where Email= '"+email+"'").uniqueResult();
		log.debug("recruiter " + recruiter);
		System.out.println("recruiter.getEmail() " + recruiter.getEmail());
		session.close();
		log.info("end getRecruiter by email");
		return recruiter;
	}

	@Override
	public List<Vacancy> getVacanciesByRecruiter(Recruiter recruiter) {
		log.info("start getVacanciesByRecruiter");
		log.debug("recruiter " + recruiter);
		Session session = sessionFactory.openSession();
		List<Vacancy> vacancies = session.createQuery("from Vacancy as Vacancies where RecruiterId= '"+recruiter.getId()+"'").list();
		log.debug("vacancies " + vacancies);
		session.close();
		log.info("end getVacanciesByRecruiter");
		return vacancies;
	}
	
	@Override
	public void editRecruiter(Recruiter recruiter){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(recruiter);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Recruiter getRecruiterByEmail(String email) {
		log.info("start getRecruiterByEmail");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Recruiter recruiter = (Recruiter) session.createQuery("from Recruiter as Recruiters where Email= '"+email+"'").uniqueResult();
		log.debug("recruiter " + recruiter);
		session.close();
		log.info("end getRecruiterByEmail");
		return recruiter;
	}

	@Override
	public void update(Recruiter recruiter) {
		log.info("start update");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(recruiter);
		//session.saveOrUpdate(recruiter);
		session.getTransaction().commit();
		session.close();
		log.info("end update");
	}
	
	
	
	

}
