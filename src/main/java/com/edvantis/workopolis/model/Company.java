package com.edvantis.workopolis.model;

import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.edvantis.workopolis.model.location.Address;


@Entity
@Table(name = "Company")
public class Company {
	@Id
	@GeneratedValue
	@Column(name="CompanyId")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")	
	private Address address = new Address();
	
	@Column(name="ImgUrl")
	private String imgUrl;

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
	
	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	
	public Company() {
		super();
	}

	public Company(int id, String name, Address address, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address
				+ ", imgUrl=" + imgUrl + "]";
	}
	


	
}
