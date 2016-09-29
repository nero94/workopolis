package com.edvantis.workopolis.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserRegistration")
public class UserRegistration implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="Id")
	private int id;
	@Column(name="Hash")
	private String hash;
	@Column(name="IdAccount")
	private int idAccount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public UserRegistration() {
		super();
	}
	
	public UserRegistration(int id, String hash, int idAccount) {
		super();
		this.id = id;
		this.hash = hash;
		this.idAccount = idAccount;
	}
	
	@Override
	public String toString() {
		return "AccauntRegistration [id=" + id + ", hash=" + hash
				+ ", idAccount=" + idAccount + "]";
	}
	
	

	
}
