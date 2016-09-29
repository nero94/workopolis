package com.edvantis.workopolis.model.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "Administratorss")
@PrimaryKeyJoinColumn(name="UserId")
public class Administrator extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Administrator(int id, String firstName, String middleName,
			String lastName, String password, String email, boolean IsActive, Date registrationDate) {
		super(id, firstName, middleName, lastName, password, email, IsActive, registrationDate);
	}

	public Administrator() {
		super();
	}
	
	
}
