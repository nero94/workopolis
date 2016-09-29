package com.edvantis.workopolis.dao.manager;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.dao.candidate.CandidateDAOImpl;
import com.edvantis.workopolis.dao.user.UserDAO;
import com.edvantis.workopolis.dao.role.RoleDAO;
import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.Manager;
import com.edvantis.workopolis.model.user.User;


public class ManagerDAOImpl implements ManagerDAO {

	static Logger log = Logger.getLogger(ManagerDAOImpl.class.getName());
	

	@Autowired  
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;
	
	protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	
	
	@Override
	public Manager getManagerByEmail(String email){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Manager manager = (Manager) session.createQuery("from Manager as Managers where Email= '"+email+"'").uniqueResult();
		Hibernate.initialize(manager.getClients());
		Hibernate.initialize(manager.getSubordinates());
		
		session.getTransaction().commit();
		session.close();
		return manager;
	};
	
	@Override
	public void update(Manager manager) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(manager);
		session.getTransaction().commit();
		session.close();
	}

}
