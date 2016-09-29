package com.edvantis.workopolis.model.interview;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.edvantis.workopolis.model.user.Interviewer;
import com.edvantis.workopolis.model.user.Recruiter;
import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Vacancy;

@Entity
@Table(name = "Interviews")
public class Interview {
	
	@Id
	@GeneratedValue
	@Column(name = "InterviewId")
	private int id;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Interviewer_FK")
	private Interviewer interviewer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Recruiter_FK")
	private Recruiter recruiter;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Candidate_FK", updatable = false, insertable = true)
	private Candidate candidate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name =  "vacancyId", updatable = true, insertable = true, nullable = true)
	private Vacancy vacancy;
	
	@Column(name = "Date")
	private Date date;
	
	@Column(name = "Comment")
	private String comment;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CommunicationType")
	private CommunicationType communicationType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "InterviewType")
	private InterviewType interviewType;
	
	@OneToOne(cascade=CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "resultId", nullable = true, updatable = true, insertable = true)
	private InterviewResult result = new InterviewResult();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Interviewer getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(Interviewer interviewer) {
		this.interviewer = interviewer;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public InterviewType getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}

	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}
	

	public InterviewResult getResult() {
		return result;
	}

	public void setResult(InterviewResult result) {
		this.result = result;
	}

	
	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public Interview() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Interview(int id, Interviewer interviewer, Recruiter recruiter,
			Candidate candidate, Vacancy vacancy, Date date, String comment,
			CommunicationType communicationType, InterviewType interviewType,
			InterviewResult result) {
		super();
		this.id = id;
		this.interviewer = interviewer;
		this.recruiter = recruiter;
		this.candidate = candidate;
		this.vacancy = vacancy;
		this.date = date;
		this.comment = comment;
		this.communicationType = communicationType;
		this.interviewType = interviewType;
		this.result = result;
	}

//	@Override
//	public String toString() {
//		return "Interview [id=" + id + ", interviewer=" + interviewer
//				+ ", recruiter=" + recruiter + ", candidate=" + candidate
//				+ ", vacancy=" + vacancy + ", date=" + date + ", comment="
//				+ comment + ", communicationType=" + communicationType
//				+ ", interviewType=" + interviewType + ", result=" + result
//				+ "]";
//	}

	

	

	
	
	
	
	
	
}
