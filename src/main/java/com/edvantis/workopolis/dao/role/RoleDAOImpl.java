package com.edvantis.workopolis.dao.role;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.model.user.Role;

public class RoleDAOImpl implements RoleDAO {
	
static Logger log = Logger.getLogger(RoleDAOImpl.class.getName());
	
	@Autowired  
	SessionFactory sessionFactory;
	
	@Override
	public int getRoleIdByName(String name) {
		log.info("start getRoleIdByName");
		Session session = sessionFactory.openSession();
		Role role = (Role) session.createQuery("from Role as Roles where Name= '"+name+"'").uniqueResult();
		log.debug("role " + role);
		int roleId = role.getId();
		log.debug("roleId " + roleId);
		session.close();
		log.info("end getRoleIdByName");
		return roleId;
	}

	@Override
	public Role getRoleByName(String name) {
		log.info("start getRoleIdByName");
		Session session = sessionFactory.openSession();
		Role role = (Role) session.createQuery("from Role as Roles where Name= '"+name+"'").uniqueResult();
		log.debug("role " + role);
		return role;
	}

}
