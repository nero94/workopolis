package com.edvantis.workopolis.dao.skill;

import java.util.List;

import com.edvantis.workopolis.model.skill.TechnicalSkill;

public interface TechnicalSkillDAO {
	
	 public List<TechnicalSkill> getTechnicalSkills();
	 
	 public TechnicalSkill getTechnicalSkillById(int id);
	 
	 public void addTechnicalSkill(TechnicalSkill skill);

	 public void deleteTechnicalSkill(int id);
}
