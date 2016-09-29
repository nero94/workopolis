package com.edvantis.workopolis.dao.university;

import com.edvantis.workopolis.model.education.University;

public interface UniversityDAO {
	
	public void addUniversity(University university);
	
	public void deleteUniversity(int id);
}
