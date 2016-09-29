package com.edvantis.workopolis.dao.location;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.model.location.Address;
import com.edvantis.workopolis.model.location.Country;

public class AddressDAOImpl implements AddressDAO {
	
	static Logger log = Logger.getLogger(AddressDAOImpl.class.getName());
	
	@Autowired  
	private SessionFactory sessionFactory;

	@Override
	public List<Address> getAddresses(Country country) {
		log.info("start getAddresses");
		int countryId = country.getId();
		log.debug("countryId " + countryId);
		Session session = sessionFactory.openSession();
		List<Address> addresses = session.createQuery("from Address as Address where countryId = '" + countryId+"'").list();
		log.debug("addresses " + addresses);
		session.close();
		log.info("end getAddresses");
		return addresses;
	}

}
