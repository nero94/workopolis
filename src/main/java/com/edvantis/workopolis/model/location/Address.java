package com.edvantis.workopolis.model.location;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Address")
public class Address {
	@Id
	@GeneratedValue
	@Column(name="AddressId")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId", insertable = true, updatable=true)
	private Country country;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CityId", insertable = true, updatable=true)
	private City city;
	
	@Column(name = "Street")
	private String street;
	
	@Column(name = "Zip")
	private int zip;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "StateId", insertable = true, updatable=true)
	private State state;
	
	public Address() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Address(int id, Country country, City city, String street, int zip,
			State state) {
		super();
		this.id = id;
		this.country = country;
		this.city = city;
		this.street = street;
		this.zip = zip;
		this.state = state;
	}

	@Override
	public String toString() {
		return (city!=null?city+ ", ":"") + (state!=null?state+ ", ":"") + (country!=null?country:"");
	}

	
	
}
