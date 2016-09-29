package com.edvantis.workopolis.model.skill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edvantis.workopolis.model.vacancy.Vacancy;

@Entity
@Table(name="PersonalSkills")
public class PersonalSkill {
	@Id
	@GeneratedValue
	@Column(name = "PersonalSkillId")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PersonalSkillName")
	private PersonalSkillName personalSkillName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PersonalSkillLevel")
	private PersonalSkillLevel personalSkillLevel;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
	public PersonalSkillName getPersonalSkillName() {
		return personalSkillName;
	}

	public void setPersonalSkillName(PersonalSkillName personalSkillName) {
		this.personalSkillName = personalSkillName;
	}

	
	public PersonalSkillLevel getPersonalSkillLevel() {
		return personalSkillLevel;
	}

	public void setPersonalSkillLevel(PersonalSkillLevel personalSkillLevel) {
		this.personalSkillLevel = personalSkillLevel;
	}

	public PersonalSkill() {
		super();
		
	}

	public PersonalSkill(int id, PersonalSkillName personalSkillName,
			PersonalSkillLevel personalSkillLevel) {
		super();
		this.id = id;
		this.personalSkillName = personalSkillName;
		this.personalSkillLevel = personalSkillLevel;
	}

	@Override
	public String toString() {
		return "PersonalSkill [id=" + id + ", personalSkillName="
				+ personalSkillName + ", personalSkillLevel="
				+ personalSkillLevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime
				* result
				+ ((personalSkillLevel == null) ? 0 : personalSkillLevel
						.hashCode());
		result = prime
				* result
				+ ((personalSkillName == null) ? 0 : personalSkillName
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalSkill other = (PersonalSkill) obj;
		if (personalSkillLevel != other.personalSkillLevel)
			return false;
		if (personalSkillName != other.personalSkillName)
			return false;
		return true;
	}
	
	

}
