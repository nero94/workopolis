package com.edvantis.workopolis.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name = "Name")
	private String name;

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

	public Country() {
		super();
	}
	
	public Country(int id, String name) {
		this.id = id;
		this.name = name;
	}


	public Country(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	
	
}
