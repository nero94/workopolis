package com.edvantis.workopolis.dao.education;

import com.edvantis.workopolis.model.education.Education;

public interface EducationDAO {
	public void addEducation(Education education);
	
	public void deleteEducation(int id);
}
