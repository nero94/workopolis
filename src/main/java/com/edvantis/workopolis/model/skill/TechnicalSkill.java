package com.edvantis.workopolis.model.skill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TechnicalSkills")
public class TechnicalSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TechnicalSkillId")
	private int id;

	@Column(name = "Name")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "Level")
	private TechnicalSkillLevel level;

	@Enumerated(EnumType.STRING)
	@Column(name = "Category")
	private TechSkillCategory category;

	@Enumerated(EnumType.STRING)
	@Column(name = "Importance")
	private Importance importance;

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TechnicalSkillLevel getLevel() {
		return level;
	}

	public void setLevel(TechnicalSkillLevel level) {
		this.level = level;
	}

	public TechSkillCategory getCategory() {
		return category;
	}

	public void setCategory(TechSkillCategory category) {
		this.category = category;
	}

	public TechnicalSkill() {
		super();
	}

	public TechnicalSkill(int id, String name, TechnicalSkillLevel level,
			TechSkillCategory category, Importance importance) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.category = category;
		this.importance = importance;
	}

	@Override
	public String toString() {
		return "{id: " + id + ", name: " + name + ", level: "
				+ level + ", category: " + category + ", importance: "
				+ importance + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		TechnicalSkill other = (TechnicalSkill) obj;
		if (category != other.category)
			return false;
		if (importance != other.importance)
			return false;
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
