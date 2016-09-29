package com.edvantis.workopolis.model.user.employer;

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
import javax.persistence.Table;

import com.edvantis.workopolis.model.user.candidate.Candidate;
import com.edvantis.workopolis.model.vacancy.Vacancy;

@Entity
@Table(name = "Offers")
public class Offer {
	@Id
	@GeneratedValue
	@Column(name="OfferId", nullable = false)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "State")
	private OfferState state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "candidateId")	
	private Candidate candidate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vacancyId")	
	private Vacancy vacancy;
	
	@Column(name = "OfferDate")
	private Date offerDate;
	
	@Column(name = "OfferComment")
	private String offerComment;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public OfferState getState() {
		return state;
	}

	public void setState(OfferState state) {
		this.state = state;
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

	public Date getOfferDate() {
		return offerDate;
	}

	public void setOfferDate(Date offerDate) {
		this.offerDate = offerDate;
	}
	
	

	public String getOfferComment() {
		return offerComment;
	}

	public void setOfferComment(String offerComment) {
		this.offerComment = offerComment;
	}

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Offer(int id, OfferState state, Candidate candidate,
			Vacancy vacancy, Date offerDate, String offerComment) {
		super();
		this.id = id;
		this.state = state;
		this.candidate = candidate;
		this.vacancy = vacancy;
		this.offerDate = offerDate;
		this.offerComment = offerComment;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", state=" + state + ", candidate="
				+ candidate + ", vacancy=" + vacancy + ", offerDate="
				+ offerDate + ", offerComment=" + offerComment + "]";
	}

	
	
}
