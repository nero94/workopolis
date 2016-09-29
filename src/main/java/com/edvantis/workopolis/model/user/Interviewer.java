package com.edvantis.workopolis.model.user;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.edvantis.workopolis.model.interview.Interview;
import com.edvantis.workopolis.model.interview.InterviewType;

@Entity
@Table(name = "Interviewers")
@PrimaryKeyJoinColumn(name="UserId")
public class Interviewer extends User {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "interviewer", fetch  =FetchType.EAGER)
	private Set<Interview> scheduledInterviews;
	
	@Column(name = "KnowledgeField", insertable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<InterviewType> knowledgeField = new HashSet<InterviewType>();

	public Set<Interview> getScheduledInterviews() {
		return scheduledInterviews;
	}

	@Column(name = "Skype")	
	private String skype;
	
	@Column(name = "TelNum")
	private Integer telNumber;
	
	public void setScheduledInterviews(Set<Interview> scheduledInterviews) {
		this.scheduledInterviews = scheduledInterviews;
	}
	
	public Set<InterviewType> getKnowledgeField() {
		return knowledgeField;
	}

	public void setKnowledgeField(Set<InterviewType> knowledgeField) {
		this.knowledgeField = knowledgeField;
	}

	
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	
	
	public Integer getTelNumber() {
		return telNumber;
	}
	public void setTelNumber(Integer telNumber) {
		this.telNumber = telNumber;
	}
	public Interviewer() {
		super();
	}

	

	public Interviewer(int id, String firstName, String middleName,
			String lastName, String password, String email, boolean IsActive, Date registrationDate) {
		super(id, firstName, middleName, lastName, password, email, IsActive, registrationDate);
	}
	
	
	
	
	public Interviewer(Set<Interview> scheduledInterviews,
			Set<InterviewType> knowledgeField, String skype, Integer telNumber) {
		super();
		this.scheduledInterviews = scheduledInterviews;
		this.knowledgeField = knowledgeField;
		this.skype = skype;
		this.telNumber = telNumber;
	}
	@Override
	public String toString() {
		return "Interviewer [scheduledInterviews=" + scheduledInterviews
				+ ", knowledgeField=" + knowledgeField + ", skype=" + skype
				+ ", getRole()=" + getRole() + ", getId()=" + getId()
				+ ", getFirstName()=" + getFirstName() + ", getMiddleName()="
				+ getMiddleName() + ", getLastName()=" + getLastName()
				+ ", getPassword()=" + getPassword() + ", getEmail()="
				+ getEmail() + ", getIsActive()=" + getIsActive()
				+ ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
	
	
}
