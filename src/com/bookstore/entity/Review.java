package com.bookstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "review")
@NamedQueries({
	@NamedQuery(name = "Review.listAll" , query = "SELECT r FROM Review r ORDER BY r.reviewTime DESC"),
	@NamedQuery(name = "Review.count" , query = "SELECT COUNT(r) FROM Review r "),
	@NamedQuery(name = "Review.findByCustomerAndBook" , query = "SELECT r FROM Review r WHERE r.book.bookId = :bookId AND r.customer.customerId = :customerId")
})
public class Review {
	//INSTANCE VARIABLE;
	private Integer reviewId;
	private Book book;
	private Customer customer;
	private int rating;
	private String headline;
	private String comment;
	private Date reviewTime;
	private String star;


	// CONSTRUCTORS;
	public Review() {

	}

	public Review(Book book, Customer customer, int rating, String headline, String comment, Date reviewTime) {
		this.book = book;
		this.customer = customer;
		this.rating = rating; 
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}
	
	

	public Review(Integer reviewId, String headline, String comment, Date reviewTime) {
		super();
		this.reviewId = reviewId;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}

	// SETTERS AND GETTERS;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@ManyToOne
	@JoinColumn(name = "book_id" , nullable = false)
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id" , nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "review_time")
	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	@Transient
	public String getStar() {
		String resualt = "";
		int numberOfStarts = (int) rating;
		
		for(int i = 1 ; i <= numberOfStarts ; i++) {
			resualt += "on,";
		}
		
		int next = numberOfStarts + 1;
		
		for(int i = next ; i <= 5 ; i++) {
			resualt += "off,";
		}
		return resualt;
	}

}
