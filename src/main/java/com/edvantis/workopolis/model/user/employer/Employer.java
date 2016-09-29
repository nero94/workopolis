package com.edvantis.workopolis.model.user.employer;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.edvantis.workopolis.model.Company;
import com.edvantis.workopolis.model.user.User;
import com.edvantis.workopolis.model.vacancy.Vacancy;

@Entity
@Table(name = "Employers")
@PrimaryKeyJoinColumn(name="UserId")
public class Employer extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "companyId")	
	private Company company;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "EmployerId", nullable = true, updatable = true, insertable = true)
	private List<Vacancy> createdVacancies = new ArrayList<Vacancy>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "EmployerId",  nullable = true, updatable = true, insertable = true)
	private Set <Offer> createdOffers = new LinkedHashSet<Offer>();

	public void addOffer(Offer offer){
		createdOffers.add(offer);
	}
	
	public void addVacancy(Vacancy vacancy){
		createdVacancies.add(vacancy);
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Vacancy> getCreatedVacancies() {
		return createdVacancies;
	}

	public void setCreatedVacancies(List<Vacancy> createdVacancies) {
		this.createdVacancies = createdVacancies;
	}

	public Set<Offer> getCreatedOffers() {
		return createdOffers;
	}

	public void setCreatedOffers(Set<Offer> createdOffers) {
		this.createdOffers = createdOffers;
	}

	

	public Employer() {
		super();
	}

	public Employer(int id, String firstName, String middleName,
			String lastName, String password, String email, boolean IsActive, Date registrationDate) {
		super(id, firstName, middleName, lastName, password, email, IsActive, registrationDate);
	}

	public Employer(int id, String firstName, String middleName,
			String lastName, String password, String email, boolean IsActive, 
			Company company, List<Vacancy> createdVacancies,
			Set<Offer> createdOffers, Date registrationDate) {
		super(id, firstName, middleName, lastName, password, email, IsActive, registrationDate);
		this.company = company;
		this.createdVacancies = createdVacancies;
		this.createdOffers = createdOffers;
	}

	@Override
	public String toString() {
		return "Employer [company=" + company
				+ ", createdVacancies=" + createdVacancies + ", createdOffers="
				+ createdOffers + "]";
	}

	
	
	
	
}
