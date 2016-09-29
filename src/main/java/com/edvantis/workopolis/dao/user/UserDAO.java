package com.edvantis.workopolis.dao.user;

import java.util.List;
import java.util.UUID;

import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.user.employer.Employer;

public interface UserDAO {
	
	 public List<User> getUsers();
	 
	 public User getUser(int  id);
	 
	 public User getUser(String email);
	 
	 public void save(User user);
	 
	 public void removeUser(int id);
	 
	 public void confirm(String uuid);
	 
	 public void editUser(User user);

	 public void register(User account, UUID uuid);
	 
	 public void registerByAdmin(Employer account, UUID uuid);
	 
	 public void saveUUID(String uuid, int userId);
	 
	 public void confirm(String uuid, String email);
	 
	 public void update(User user);
	 
	 public void changePassword(String uuid, String newPassword);
}
