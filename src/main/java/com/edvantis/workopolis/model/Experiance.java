package com.edvantis.workopolis.model;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;





@Entity
@Table(name="Experiance")
public class Experiance {
	
	@Id
	@GeneratedValue
	@Column(name="ExperianceId")
	private int id;
	
	
	@JoinColumn(name = "Company")	
	private String companyName;
	
	@Column(name="Position")
	private String position;	


	@Column(name="Responsibilities")
	private String responsibilities;
	
	@Column(name="StartDate")
	private Date startDate;
	
	@Column(name="FinishDate")
	private Date finishdate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompany(String company) {
		this.companyName = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}

	
	
	
	public Experiance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experiance(int id, String companyName, String position,
			String responsibilities, Date startDate, Date finishdate) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.position = position;
		this.responsibilities = responsibilities;
		this.startDate = startDate;
		this.finishdate = finishdate;
	}

	@Override
	public String toString() {
		return "Experiance [id=" + id + ", company=" + companyName + ", position="
				+ position + ", responsibilities=" + responsibilities
				+ ", startDate=" + startDate + ", finishdate=" + finishdate
				+ "]";
	}

}
