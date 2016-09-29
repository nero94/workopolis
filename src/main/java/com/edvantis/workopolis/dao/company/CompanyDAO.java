package com.edvantis.workopolis.dao.company;

import java.util.List;

import com.edvantis.workopolis.model.Company;

public interface CompanyDAO {
	
	public Company getCompanyByName(String name);
	
	public List<Company> getCompanies();

}
