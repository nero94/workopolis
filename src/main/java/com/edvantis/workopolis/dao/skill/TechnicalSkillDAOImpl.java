package com.edvantis.workopolis.dao.skill;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edvantis.workopolis.model.skill.TechnicalSkill;

@Repository
public class TechnicalSkillDAOImpl implements TechnicalSkillDAO{
	
	@Autowired  
	private SessionFactory sessionFactory;
	
	@Override
	public List<TechnicalSkill> getTechnicalSkills(){
		Session session = sessionFactory.openSession();
		List<TechnicalSkill> skills = session.createQuery("from TechnicalSkill as TechnicalSkills").list();
		session.close();
		return skills;
	}
	 
	@Override
	public TechnicalSkill getTechnicalSkillById(int id) {
		Session session = sessionFactory.openSession();
		TechnicalSkill technicalSkill = (TechnicalSkill) session.get(TechnicalSkill.class, id);
		return technicalSkill;
	}	 
	
	@Override
	public void addTechnicalSkill(TechnicalSkill skill) {
		Session session = this.sessionFactory.openSession();
		session.save(skill);
		session.close();
	}
	

	@Override
	public void deleteTechnicalSkill(int id) {
		Session session = this.sessionFactory.openSession();
		TechnicalSkill technicalSkill = (TechnicalSkill)session.load(TechnicalSkill.class, new Integer(id));
		if (null  != technicalSkill){
			session.delete(technicalSkill);
		}
		session.close();
	}
	
}
