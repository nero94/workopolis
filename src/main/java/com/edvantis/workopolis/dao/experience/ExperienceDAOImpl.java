package com.edvantis.workopolis.dao.experience;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.model.Experiance;


public class ExperienceDAOImpl implements ExperienceDAO {

	@Autowired  
	private SessionFactory sessionFactory;
	
	@Override
	public void deleteExperience(int expeienceId) {
		Session session = this.sessionFactory.openSession();
		Experiance experiance = (Experiance)session.load(Experiance.class, new Integer(expeienceId));
		if (experiance != null) session.delete(experiance);
		session.close();
	}

}
