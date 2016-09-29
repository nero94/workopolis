package com.edvantis.workopolis.model.user;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.edvantis.workopolis.model.vacancy.Vacancy;


@Entity
@Table(name = "Recruiters")
@PrimaryKeyJoinColumn(name="UserId")
public class Recruiter extends User {

	
	private static final long serialVersionUID = 1L;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "RecruiterId", nullable = true, updatable = true, insertable = true)
	private Set<Vacancy> assignedVacancies;
	
	@Column(name = "Rating")
	private int rating;

	

	public Set<Vacancy> getAssignedVacancies() {
		return assignedVacancies;
	}

	public void setAssignedVacancies(Set<Vacancy> assignedVacancies) {
		this.assignedVacancies = assignedVacancies;
	}
	
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}


	public Recruiter() {
		super();
	}

	

	public Recruiter(int id, String firstName, String middleName,
			String lastName, String password, String email, boolean IsActive,Set<Vacancy> assignedVacancies,
			Set<Interviewer> interviewers, int rating, Date registrationDate) {
		super(id, firstName, middleName, lastName, password, email, IsActive, registrationDate);
		this.assignedVacancies = assignedVacancies;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Recruiter [assignedVacancies=" + assignedVacancies
				+ ", rating=" + rating + "]";
	}

	
}
