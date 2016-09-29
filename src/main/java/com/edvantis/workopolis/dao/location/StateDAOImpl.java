package com.edvantis.workopolis.dao.location;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edvantis.workopolis.model.location.State;

@Repository("stateDAO")
public class StateDAOImpl implements StateDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public State getStateById(int id){
		Session session = sessionFactory.openSession();
		State state = (State) session.get(State.class, id);
		session.close();
		return state;
	}

}
