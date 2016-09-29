package com.edvantis.workopolis.dao.company;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edvantis.workopolis.model.Company;

public class CompanyDAOImpl implements CompanyDAO {
	
static Logger log = Logger.getLogger(CompanyDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Company getCompanyByName(String name) {
		log.info("start getCompanyByName");
		Session session = sessionFactory.openSession();
		Company company = (Company) session.createQuery("from Company as Company where name= '" + name + "'").uniqueResult();
		log.debug("company " + company);
		session.close();
		log.info("end getCompanyByName");
		return company;
	}

	@Override
	public List<Company> getCompanies() {
		Session session = sessionFactory.openSession();
		List<Company> companies = session.createQuery("from Company as Company").list();
		log.debug("companies " + companies);
		session.close();
		return companies;
	}

}
