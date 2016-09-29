package com.edvantis.workopolis.dao.education;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.candidate.CandidateDAOImpl;
import com.edvantis.workopolis.model.education.Education;

public class EducationDAOImpl implements EducationDAO {

	static Logger log = Logger.getLogger(CandidateDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;
		
	@Override
	public void addEducation(Education education) {
		Session session = this.sessionFactory.openSession();
		session.save(education);
		session.close();
	}

	@Override
	public void deleteEducation(int id) {
		Session session = this.sessionFactory.openSession();
		Education education = (Education)session.load(Education.class, new Integer(id));
		if (education != null) session.delete(education);
		session.close();
	}

}
