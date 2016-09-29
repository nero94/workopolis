package com.edvantis.workopolis.model.skill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "CommunicationSkills")
public class CommunicationSkill {
	@Id
	@GeneratedValue
	@Column(name = "CommunicationSkillId")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Language")
	private Language name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "Importance")
	private Importance importance;
	

	@Enumerated(EnumType.STRING)
	@Column(name = "CommunicationSkillLevel")
	private CommunicationSkillLevel level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Language getName() {
		return name;
	}

	public void setName(Language name) {
		this.name = name;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}
	
	public CommunicationSkillLevel getLevel() {
		return level;
	}

	
	public void setLevel(CommunicationSkillLevel level) {
		this.level = level;
	}

	public CommunicationSkill() {
		super();
	}

	public CommunicationSkill(int id, Language name, Importance importance,
			CommunicationSkillLevel level) {
		super();
		this.id = id;
		this.name = name;
		this.importance = importance;
		this.level = level;
	}

	@Override
	public String toString() {
		return "CommunicationSkill [id=" + id + ", name=" + name
				+ ", importance=" + importance + ", level=" + level + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((importance == null) ? 0 : importance.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		CommunicationSkill other = (CommunicationSkill) obj;
		if (importance != other.importance)
			return false;
		if (level != other.level)
			return false;
		if (name != other.name)
			return false;
		return true;
	}

		
}
