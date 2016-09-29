package com.edvantis.workopolis.model.user;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.edvantis.workopolis.model.user.employer.Employer;



@Entity
@Table(name = "Managers")
@PrimaryKeyJoinColumn(name="UserId")
public class Manager extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="Skype")
	private String skypeUrl;

	public String getSkypeUrl() {
		return skypeUrl;
	}

	public void setSkypeUrl(String skypeUrl) {
		this.skypeUrl = skypeUrl;
	}
	
	@Column(name = "Telnumber")
	private Integer telNumber;

	public Integer getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(Integer telNumber) {
		this.telNumber = telNumber;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "ManagerId", updatable = false, insertable = true)
	private Set<Recruiter> subordinates;
	
	@OneToMany
	@JoinColumn(name = "ManagerId", updatable = false, insertable = true)
	private Set<Employer> clients;

	public Set<Recruiter> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Recruiter> subordinates) {
		this.subordinates = subordinates;
	}

	public Set<Employer> getClients() {
		return clients;
	}

	public void setClients(Set<Employer> clients) {
		this.clients = clients;
	}


	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Manager(String skypeUrl, Integer telNumber, Set<Recruiter> subordinates,
			Set<Employer> clients) {
		super();
		this.skypeUrl = skypeUrl;
		this.telNumber = telNumber;
		this.subordinates = subordinates;
		this.clients = clients;
	}

	

	@Override
	public String toString() {
		return "Manager [subordinates=" + subordinates + ", clients=" + clients
				+ "]";
	}


	
	
	
}
