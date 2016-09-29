package com.edvantis.workopolis.dao.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.model.user.Administrator;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.User;

public class AdminDAOImpl implements AdminDAO {
	
	static Logger log = Logger.getLogger(AdminDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;

	@Override
	public List<User> getUsers() {
		log.info("start getUsers");
		Session session = sessionFactory.openSession();
		List<User> users = session.createQuery("from User as Users").list();
		log.debug("users " + users);
		session.close();
		return users;
	}

	@Override
	public Administrator getAdminByEmail(String email) {
		log.info("start getAdminByEmail");
		log.debug("email " + email);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Administrator admin = (Administrator) session.createQuery("from Administrator as Administrators where Email= '"+email+"'").uniqueResult();
		log.debug("admin " + admin);
		session.close();
		log.info("end getAdminByEmail");
		return admin;
	}

}
