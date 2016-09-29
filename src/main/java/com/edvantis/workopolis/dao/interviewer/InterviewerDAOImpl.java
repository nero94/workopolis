package com.edvantis.workopolis.dao.interviewer;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.candidate.CandidateDAOImpl;
import com.edvantis.workopolis.dao.role.RoleDAO;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.UserRegistration;


public class InterviewerDAOImpl implements InterviewerDAO {

static Logger log = Logger.getLogger(CandidateDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	
	
	
	
	
	@Override
	public List<Interviewer> getInterviewers() {
		Session session = sessionFactory.openSession();
		List<Interviewer> interviewers = session.createQuery("from Interviewer as Interviewers").list();
		session.close();
		return interviewers;
		
	}

	@Override
	public Interviewer getInterviewerById(int id) {
		Session session = sessionFactory.openSession();
		Interviewer interviewer = (Interviewer) session.get(Interviewer.class, id);
		log.debug("interviewer " + interviewer);
		session.close();
		return interviewer;
	}

	@Override
	public void addInterviewer(Interviewer interviewer) {
		Session session = this.sessionFactory.openSession();
		session.save(interviewer);
		session.close();
		
	}

	@Override
	public void deteteInterviewer(int id) {
		Session session = this.sessionFactory.openSession();
		Interviewer interviewer = (Interviewer)session.load(Interviewer.class, new Integer(id));
		if (interviewer != null) session.delete(interviewer);
		session.close();
		
	}

	@Override
	public Interviewer getInterviewerByEmail(String email) {
		log.info("start getInterviewerByEmail");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Interviewer interviewer = (Interviewer) session.createQuery("from Interviewer as Interviewers where Email= '"+email+"'").uniqueResult();
		log.debug("interviewer " + interviewer);
		session.close();
		log.info("end getInterviewerByEmail");
		return interviewer;
	}

	@Override
	public void update(Interviewer interviewer) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(interviewer);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void saveUUID(String uuid, int userId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserRegistration userRegistration = new UserRegistration();
		userRegistration.setIdAccount(userId);
		userRegistration.setHash(uuid);
		session.save(userRegistration);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void confirm(String uuid, String email) {
		Session session = sessionFactory.openSession(); 
		session.beginTransaction();
		UserRegistration accountRegistration = (UserRegistration) session.createQuery("from UserRegistration as UserRegistration where hash = '"+uuid+"'").uniqueResult();
		log.debug("accauntRegistration " + accountRegistration);
		int idAccount = accountRegistration.getIdAccount();
		User account = (User) session.createQuery("from User as Users where id = " + idAccount).uniqueResult();
		log.debug("account " + account);
		account.setEmail(email);
		log.debug("account " + account);
		session.getTransaction().commit(); //update email
    	session.close();
	}


	

}
