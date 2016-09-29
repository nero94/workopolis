package com.edvantis.workopolis.model;


public class CommunicationSkill {

	private int id;
	

	private Language name;
	

	private Importance importance;
	

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
		return "-\tLanguage: " + name
				+ ", importance: " + importance + ", level: " + level + "\n\n";
	}


		
}
