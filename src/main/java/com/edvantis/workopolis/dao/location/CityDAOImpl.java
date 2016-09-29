package com.edvantis.workopolis.dao.location;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edvantis.workopolis.model.location.City;

@Repository("cityDAO")
public class CityDAOImpl implements CityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public City getCityById(int id){
		Session session = sessionFactory.openSession();
		City city = (City) session.get(City.class, id);
		session.close();
		return city;
	}

}
