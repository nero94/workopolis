package com.edvantis.workopolis.dao.manager;

import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.Manager;

public interface ManagerDAO {

	public Manager getManagerByEmail(String email);
	
	public void update(Manager manager);
}
