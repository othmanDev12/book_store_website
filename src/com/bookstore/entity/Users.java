package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")

@NamedQueries({
	@NamedQuery(name = "Users.findAll" , query = "SELECT u FROM Users u GROUP BY u.fullName"),
	@NamedQuery(name = "Users.findEmail" , query = "SELECT u FROM Users u WHERE u.email = :email"),
	@NamedQuery(name = "Users.count" , query = "SELECT COUNT(u) FROM Users u"),
	@NamedQuery(name = "Users.findEmailPass" , query = "SELECT u FROM Users u WHERE u.email = :email AND password = :password")
})
public class Users {
	// INSTANCE VARIABLE;
	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	// CONSTRUCTORS;
	public Users() {

	}
	
	public Users(Integer userId ,String email, String password, String fullName) {
		this(email , password , fullName);
		this.userId = userId;
	}

	public Users(String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
		
	}

	// GETTERS AND SETTERS;
	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
