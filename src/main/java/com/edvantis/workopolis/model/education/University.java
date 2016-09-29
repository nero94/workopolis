package com.edvantis.workopolis.model.education;




import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edvantis.workopolis.model.location.Address;

@Entity
@Table(name = "University")
public class University {
	@Id
	@GeneratedValue
	@Column(name="UniversityId")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "University_Address")
	private Address address;
	
	@Column(name = "StartDate")
	private Date startDate;
	
	@Column(name = "FinishDate")
	private Date finishDate;

	public University() {
		super();
	}

	

	public University(int id, String name, Address address, Date startDate,
			Date finishDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getFinishDate() {
		return finishDate;
	}



	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}



	@Override
	public String toString() {
		return "University [id=" + id + ", name=" + name + ", address="
				+ address + ", startDate=" + startDate + ", finishDate="
				+ finishDate + "]";
	}

	
	
	
}