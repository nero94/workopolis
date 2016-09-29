package com.edvantis.workopolis.dao.role;

import com.edvantis.workopolis.model.user.Role;

public interface RoleDAO {
	
	public int getRoleIdByName(String name);
	
	public Role getRoleByName(String name);

}
