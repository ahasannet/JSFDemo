package com.shameem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="id")
    private int id;
	
	@Column(name="user_name", unique=true, nullable=false)
	private String userName;
	
	@Column(name="password", nullable=false)
	private String password;
	
	public User()
	{
		
	}
	
	public User (int id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
