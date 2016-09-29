package com.edvantis.workopolis.dao.university;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.candidate.CandidateDAOImpl;
import com.edvantis.workopolis.model.education.University;

public class UniversityDAOImpl implements UniversityDAO {
	

	static Logger log = Logger.getLogger(CandidateDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;

	@Override
	public void addUniversity(University university) {
		Session session = this.sessionFactory.openSession();
		session.save(university);
		session.close();
	}

	@Override
	public void deleteUniversity(int id) {
		Session session = this.sessionFactory.openSession();
		University university = (University)session.load(University.class, new Integer(id));
		if (university != null) session.delete(university);
		session.close();
		
	}

}
