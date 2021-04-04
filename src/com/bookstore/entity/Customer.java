package com.bookstore.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@NamedQueries({
	@NamedQuery(name = "Customer.listAll" , query = "SELECT c FROM Customer c ORDER BY  c.registerDate DESC"),
	@NamedQuery(name = "Customer.count" , query = "SELECT COUNT(*) FROM Customer c"),
	@NamedQuery(name = "Customer.findByEmail" , query = "SELECT c FROM Customer c WHERE c.email = :email"),
	@NamedQuery(name = "Customer.findEmailPass" , query = "SELECT c FROM Customer c WHERE c.email = :email AND password = :password")
})
public class Customer {
	// INSTANCE VARIABLE
	private Integer customerId;
	private String email;
	private String fullName;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String zipcode;
	private String password;
	private Date registerDate;
	
	private Set<Review> reviews;
	
	private Set<BookOrder> bookOrders;

	public Customer() {

	}

	public Customer(String email, String fullName, String address, String city, String country, String phone,
			String zipcode, String password, Date registerDate) {
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
	}
	
	
	
	

	public Customer(String fullName, String address, String city, String country, String phone, String zipcode,
			Date registerDate) {
		super();
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.zipcode = zipcode;
		this.registerDate = registerDate;
	}

	public Customer(Integer customerId, String email, String fullName, String address, String city, String country,
			String phone, String zipcode, String password, Date registerDate) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.fullName = fullName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
	}

	@Column(name = "customer_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fullname")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "register_date")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
	public Set<BookOrder> getBookOrders() {
		return bookOrders;
	}

	public void setBookOrders(Set<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}
}
