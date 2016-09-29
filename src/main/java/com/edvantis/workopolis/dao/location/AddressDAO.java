package com.edvantis.workopolis.dao.location;

import java.util.List;

import com.edvantis.workopolis.model.location.Address;
import com.edvantis.workopolis.model.location.Country;

public interface AddressDAO {
	
	public List<Address> getAddresses(Country country);

}
