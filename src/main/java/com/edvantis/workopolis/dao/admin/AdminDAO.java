package com.edvantis.workopolis.dao.admin;

import java.util.List;

import com.edvantis.workopolis.model.user.Administrator;
import com.edvantis.workopolis.model.user.User;

public interface AdminDAO {
	public List<User> getUsers();
	
	public Administrator getAdminByEmail(String email);

}
