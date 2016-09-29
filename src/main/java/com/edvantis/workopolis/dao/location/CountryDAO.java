package com.edvantis.workopolis.dao.location;

import java.util.List;

import com.edvantis.workopolis.model.location.Country;

public interface CountryDAO {

	public Country getCountryById(int id);
	
	public Country getCountryByName(String name);
	
	public List<Country> getCountries();
	
}
