package com.edvantis.workopolis.model.interview;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InterviewResult")
public class InterviewResult {
	
	@Id
	@GeneratedValue
	@Column(name = "InterviewResultId")
	private int id;
	
	@Column(name = "IdentifiedSkills")
	private String identifiedSkills; 
	
	@Column(name = "Comment")
	private String interviewerComment;

	public String getIdentifiedSkills() {
		return identifiedSkills;
	}

	public void setIdentifiedSkills(String identifiedSkills) {
		this.identifiedSkills = identifiedSkills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getInterviewerComment() {
		return interviewerComment;
	}

	public void setInterviewerComment(String interviewerComment) {
		this.interviewerComment = interviewerComment;
	}

	public InterviewResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public InterviewResult(int id, String identifiedSkills,
			String interviewerComment) {
		super();
		this.id = id;
		this.identifiedSkills = identifiedSkills;
		this.interviewerComment = interviewerComment;
	}

	@Override
	public String toString() {
		return "InterviewResult [id=" + id + ", identifiedSkills="
				+ identifiedSkills + ", interviewerComment="
				+ interviewerComment + "]";
	}
	
	
}
