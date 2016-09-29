package com.edvantis.workopolis.MongoDB;


import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.edvantis.workopolis.model.location.Address;
import com.edvantis.workopolis.model.skill.TechnicalSkill;
import com.edvantis.workopolis.model.vacancy.Status;

@Document(collection = "vacancies")
public class Vacancy {
	
	@Id
	private String id;
	
	private String title;
	
	private String location;
	
	private Direction direction;
	
	private Status status;
	
	private Integer salary;
	
	private String description;
	
	private String postDate;
	
	private Set <TechnicalSkill> technicalSkills = new LinkedHashSet<TechnicalSkill>();

	public Vacancy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vacancy(String id, String title, String location,
			Direction direction, Status status, Integer salary,
			String description, Set<TechnicalSkill> technicalSkills) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.direction = direction;
		this.status = status;
		this.salary = salary;
		this.description = description;
		this.technicalSkills = technicalSkills;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<TechnicalSkill> getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(Set<TechnicalSkill> technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "Vacancy [id=" + id + ", title=" + title + ", location="
				+ location + ", direction=" + direction + ", status=" + status
				+ ", salary=" + salary + ", description=" + description
				+ ", technicalSkills=" + technicalSkills + "]";
	}

	public void setTechnicalSkill(TechnicalSkill technicalSkill) {
		// TODO Auto-generated method stub
		this.technicalSkills.add(technicalSkill);
	}

	
	
}
