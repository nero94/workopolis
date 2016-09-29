package com.edvantis.workopolis.dao.location;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edvantis.workopolis.model.location.Country;

@Repository("countryDAO")
public class CountryDAOImpl implements CountryDAO {
	
	static Logger log = Logger.getLogger(CountryDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Country getCountryById(int id){
		Session session = sessionFactory.openSession();
		Country country = (Country) session.get(Country.class, id);
		session.close();
		return country;
	}

	@Override
	public Country getCountryByName(String name) {
		log.info("start getCountryByName");
		Session session = sessionFactory.openSession();
		Country country = (Country) session.createQuery("from Country as Country where name= '" + name + "'").uniqueResult();
		log.debug("country " + country);
		session.close();
		log.info("end getCountryByName");
		return country;
	
	}
	
	@Override
	public List<Country> getCountries() {
		Session session = sessionFactory.openSession();
		List<Country> countries = session.createQuery("from Country as Country").list();
		log.debug("countries " + countries);
		session.close();
		return countries;
	}

}
