package com.edvantis.workopolis.model.education;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.persistence.Table;




@Entity
@Table(name = "Education")
public class Education {
	@Id
	@GeneratedValue
	@Column(name="EducationId")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Education_University")
	private University university;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EducationField")
	private EducationField educationField;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "EducationDegree")
	private EducationDegree educationDegree;
	

	public Education() {
		super();
	}

	public Education(int id, University university,
			EducationField educationField, EducationDegree educationDegree) {
		this.id = id;
		this.university = university;
		this.educationField = educationField;
		this.educationDegree = educationDegree;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
	public EducationField getEducationField() {
		return educationField;
	}

	public void setEducationField(EducationField educationField) {
		this.educationField = educationField;
	}
	
	public EducationDegree getEducationDegree() {
		return educationDegree;
	}

	public void setEducationDegree(EducationDegree educationDegree) {
		this.educationDegree = educationDegree;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", university=" + university
				+ ", educationField=" + educationField + ", educationDegree="
				+ educationDegree + "]";
	}




	

	
	
	
	
	
	
}
