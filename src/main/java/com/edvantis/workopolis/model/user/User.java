package com.edvantis.workopolis.model.user;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Table(name="Users")
@Access(value = AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="UserId")
	protected int id;
	
	@Column(name="FirstName")
	protected String firstName;
	
	@Column(name="MiddleName", updatable=true, insertable=true)
	protected String middleName;
	
	@Column(name="LastName")
	protected String lastName;
	
	@Column(name="Password")
	protected String password;
	
	@Column(name="Email")
	protected String email;
	
	@Column(name="IsActive")
	protected boolean IsActive;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RoleId", updatable = false, insertable = true)
	protected Role role;
	
	@Column(name="RegistrationDate")
	protected Date registrationDate;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getIsActive() {
		return IsActive;
	}
	public void setIsActive(boolean IsActive) {
		this.IsActive = IsActive;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public User() {
		super();
	}
	public User(int id, String firstName, String middleName, String lastName,
			String password, String email, boolean IsActive, Date registrationDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.IsActive = IsActive;
		this.registrationDate = registrationDate;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", password="
				+ password + ", email=" + email + ", IsActive=" + IsActive
				+ ", role=" + role + ", registrationDate=" + registrationDate
				+ "]";
	}
	
	
	


}
