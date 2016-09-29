package com.edvantis.workopolis.dao.result;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.vacancy.VacancyDAOImpl;
import com.edvantis.workopolis.model.interview.InterviewResult;
import com.edvantis.workopolis.model.user.Interviewer;

public class ResultDAOImpl implements ResultDAO {

	
static Logger log = Logger.getLogger(VacancyDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Override
	public void updateOrCreateResult(InterviewResult result) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(result);
		session.getTransaction().commit();
		session.close();
		log.info("Result saved/updated successfuly");
	}

	@Override
	public InterviewResult gerResultById(int id) {
		Session session = sessionFactory.openSession();
		InterviewResult result = (InterviewResult) session.get(Interviewer.class, id);
		log.debug("interviewer " + result);
		session.close();
		
		return result;
	}

}
