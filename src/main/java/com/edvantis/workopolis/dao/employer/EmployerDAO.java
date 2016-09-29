package com.edvantis.workopolis.dao.employer;

import java.util.List;

import com.edvantis.workopolis.model.Company;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.user.employer.Employer;

public interface EmployerDAO {
	
	public String getPhotoNameByEmail(String email);

	 public void editEmployer(Employer employer);
	 
	 public Employer getEmployerByEmail(String email);
	 
	 
	 public void saveUUID(String uuid, int userId);
	 
	 public void confirm(String uuid, String email);
	 
	 public void addLogo(String filename, String email);
	 
	 public Company getCompanyByEmployer(Employer employer);
	 
	 public List<Employer> getAllEmployers();
	 
	 public Employer getEmployerById(int id);
	 	
}
